package project.network;

import java.util.ArrayList;
import java.util.List;

import project.conceptual.ComputationAPI;
import project.conceptual.ComputationInput;
import project.conceptual.ComputationResult;
import project.process.DataStorageAPI;
import project.process.IntegerData;
import project.process.OutputData;

public class ComputeEngine implements ComputeEngineAPI {
	
    private DataStorageAPI dataStorage;
    private ComputationAPI computation;

    public ComputeEngine(DataStorageAPI dataStorage, ComputationAPI computation) {
        // Validate constructor parameters
        if (dataStorage == null || computation == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }
        this.dataStorage = dataStorage;
        this.computation = computation;
    }

    @Override
    public boolean submitJob(JobRequest job) {
        // Validate job request
        if (job == null) {
            return false;
        }
        try {
            // Validate input and output sources
            if (job.getInputSource() == null || job.getOutputDestination() == null) {
                return false;
            }
            // Read and check integer data
            IntegerData inputData = dataStorage.readInput(job.getInputSource());
            if (inputData == null) {
                return false;
            }
            //read and check integers
            List<Integer> integers = inputData.getValues();
            if (integers == null) {
                return false;
            }
            
            List<String> results = new ArrayList<>();
            // Determine delimiters
            ResultDelimiters delimiters = job.getDelimiters();
            if (delimiters == null) {
            	delimiters = ResultDelimiters.defaults();
            }
            if (delimiters.getInputResultDelimiter() == null || delimiters.getResultDelimiter() == null) {
                return false;
            }

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
                StringBuilder formatted = new StringBuilder();

                formatted.append(result.getInput());
                formatted.append(delimiters.getInputResultDelimiter());

                List<Integer> factors = result.getPrimeFactors();

                for (int i = 0; i < factors.size(); i++) {
                    if (i > 0) {
                        formatted.append(delimiters.getResultDelimiter());
                    }
                    formatted.append(factors.get(i));
                }
                results.add(formatted.toString());
            }

            // Wrap output
            OutputData output = new OutputData() {
                @Override
                public List<String> getValues() {
                    return results;
                }
            };

            // Write results
            boolean success = dataStorage.writeOutput(job.getOutputDestination(), output);
            return success;

        } catch (Exception e) {
            // Prevent exceptions from escaping the API boundary
            System.err.println("ComputeEngine failed: " + e.getMessage());
            return false;
        }
    }
}
