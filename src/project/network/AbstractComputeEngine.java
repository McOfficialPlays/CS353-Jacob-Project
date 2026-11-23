
package project.network;

import java.util.List;

import project.conceptual.ComputationAPI;
import project.conceptual.ComputationResult;
import project.process.DataStorageAPI;
import project.process.IntegerData;
import project.process.OutputData;

public abstract class AbstractComputeEngine {

    protected final DataStorageAPI dataStorage;
    protected final ComputationAPI computation;

    public AbstractComputeEngine(DataStorageAPI dataStorage, ComputationAPI computation) {
        if(dataStorage == null || computation == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }
        this.dataStorage = dataStorage;
        this.computation = computation;
    }
    protected List<Integer> readInput(JobRequest job){
    	if(job == null || job.getInputSource() == null || job.getOutputDestination() == null) {
    		return null;
    	}
    	IntegerData inputData = dataStorage.readInput(job.getInputSource());
        if (inputData == null) {
            return null;
        }
        //read and check integers
        List<Integer> integers = inputData.getValues();
        if (integers == null) {
            return null;
        }
        return integers;
    }
    protected ResultDelimiters getDelimiters(JobRequest job) {
    	 ResultDelimiters delimiters = job.getDelimiters();
         if (delimiters == null) {
         	delimiters = ResultDelimiters.defaults();
         }
         if (delimiters.getInputResultDelimiter() == null || delimiters.getResultDelimiter() == null) {
             return null;
         }
         return delimiters;
    }
    protected String formatResult(ComputationResult result, ResultDelimiters delimiters) {
    	if(result == null || result.getPrimeFactors()== null) {
    		return null;
    	}
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
        return formatted.toString();
    }
    protected boolean writeResults(JobRequest job, List<String> results) {
    	OutputData output = new OutputData() {
            @Override
            public List<String> getValues() {
                return results;
            }
        };

        // Write results
        return dataStorage.writeOutput(job.getOutputDestination(), output);
    }
    
}
