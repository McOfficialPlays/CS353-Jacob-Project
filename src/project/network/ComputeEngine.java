package project.network;

import java.util.ArrayList;
import java.util.List;

import project.conceptual.ComputationAPI;
import project.conceptual.ComputationInput;
import project.conceptual.ComputationResult;
import project.process.DataStorageAPI;
import project.process.IntegerData;
import project.process.OutputData;

public class ComputeEngine implements ComputeEngineAPI{
	private DataStorageAPI dataStorage;
    private ComputationAPI computation;

    public ComputeEngine(DataStorageAPI dataStorage, ComputationAPI computation) {
        this.dataStorage = dataStorage;
        this.computation = computation;
    }
	@Override
	public void submitJob(JobRequest job) {
		if(job == null){
			throw new IllegalArgumentException("Job request cannot be null");
		}
		//Read The Integer Data
		IntegerData inputData = dataStorage.readInput(job.getInputSource());
		if(inputData == null){
			throw new IllegalArgumentException("Failed To read Input data");
		}
		List<Integer> integers = inputData.getValues();
		List<String> results = new ArrayList<>();
		
		//2. determine delimiters
		ResultDelimiters delimiters = job.getDelimiters();
		if(delimiters == null) {
			delimiters = ResultDelimiters.defaults();
		}
		//3. compute prime numbers for each integer
		for(Integer number : integers) {
			ComputationInput input = new ComputationInput() {
				@Override
				public int getValue(){
					return number;
				}
			};
		
			ComputationResult result = computation.compute(input);
			//4. Format result
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

        // 5. Wrap output
        OutputData output = new OutputData() {
            @Override
            public List<String> getValues() {
                return results;
            }
        };

        // 6. Write results
        dataStorage.writeOutput(job.getOutputDestination(), output);
       
    }
}