package integration;

import java.util.List;

import project.process.DataStorageAPI;
import project.process.InputSource;
import project.process.IntegerData;
import project.process.OutputData;
import project.process.OutputDestination;

public class InMemoryDataStore implements DataStorageAPI {

	@Override
	
	public IntegerData readInput(InputSource source) {
	    InMemoryInputSource inputSource = (InMemoryInputSource) source;

	    List<Integer> values = inputSource.getInput();

	    return new IntegerData() {
	        @Override
	        public List<Integer> getValues() {
	            return values;
	        }
	    };
	}

	@Override
	public void writeOutput(OutputDestination destination, OutputData output) {
		// TODO Auto-generated method stub
		InMemoryOutput outputDestination =  (InMemoryOutput)destination;
		List<String> out = output.getValues();
		outputDestination.setOutput(out);
	}

}
