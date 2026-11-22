package project.network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import project.conceptual.ComputationAPI;
import project.conceptual.ComputationInput;
import project.conceptual.ComputationResult;
import project.process.DataStorageAPI;

public class MultiThreadedComputeEngine extends AbstractComputeEngine implements ComputeEngineAPI, AutoCloseable {
    private final ExecutorService threadPool = Executors.newFixedThreadPool(8);
    public MultiThreadedComputeEngine(DataStorageAPI dataStorage, ComputationAPI computation) {
        super(dataStorage, computation);
    }

    @Override
    public boolean submitJob(JobRequest job) {
        try {
            List<Integer> integers = readInput(job);
            if (integers == null) {
                return false;
            }
            ResultDelimiters delimiters = getDelimiters(job);
            if (delimiters == null) {
                return false;
            }
            List<Future<ComputationResult>> futures = new ArrayList<>();

            // Submit computations to threads
            for (Integer number : integers) {
                ComputationInput input = new ComputationInput() {
                    @Override
                    public int getValue() {
                        return number;
                    }
                };
                Future<ComputationResult> future = threadPool.submit(() -> computation.compute(input));
                futures.add(future);
            }
            // Collect results in input order
            List<String> results = new ArrayList<>();

            for (Future<ComputationResult> future : futures) {
                ComputationResult result = future.get();
                String formatted = formatResult(result, delimiters);
                if (formatted == null) {
                    return false;
                }
                results.add(formatted);
            }
            return writeResults(job, results);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            System.err.println(
                "MultiThreadedComputeEngine failed: "
                + e.getMessage());
            return false;
        }
    }

    @Override
    public void close() {
        threadPool.shutdown();
    }
}