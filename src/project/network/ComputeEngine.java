package project.network;

import java.util.ArrayList;
import java.util.List;

import project.conceptual.ComputationAPI;
import project.conceptual.ComputationInput;
import project.conceptual.ComputationResult;
import project.process.DataStorageAPI;


public class ComputeEngine extends AbstractComputeEngine implements ComputeEngineAPI {
    public ComputeEngine(DataStorageAPI dataStorage, ComputationAPI computation) {
       super(dataStorage, computation);
    }

    @Override
    public boolean submitJob(JobRequest job) {
        // Validate job request
        if (job == null) {
            return false;
        }
        try {
            // Validate input and output sources (now in abstract)
            //read and check integers
            List<Integer> integers = readInput(job);
            if (integers == null) {
                return false;
            }
            // Determine delimiters
            ResultDelimiters delimiters = getDelimiters(job);
            if (delimiters == null) {
                return false;
            }
            List<String> results = new ArrayList<>();
            // Compute prime factors for each integer
            for (Integer number : integers) {
                // Validate number
                if (number == null || number <= 0) {
                    return false;
                }
                ComputationInput input = new ComputationInput() {
                    @Override
                    public int getValue() {
                        return number;
                    }
                };
                ComputationResult result = computation.compute(input);
                if (result == null || result.getPrimeFactors() == null) {
                    return false;
                }

                // Format result
                String formatted = formatResult(result, delimiters);
                results.add(formatted);
                if (delimiters == null) {
                    return false;
                }
            }
            return writeResults(job,results);

        } catch (Exception e) {
            // Prevent exceptions from escaping the API boundary
            System.err.println("ComputeEngine failed: " + e.getMessage());
            return false;
        }
    }
}