package integration;

import java.util.List;

import project.process.InputSource;

public class InMemoryInputSource implements InputSource {

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "in-memory";
	}
	List<Integer> input;
	public InMemoryInputSource(List<Integer> input) {
		this.input = input;
		
	}
	public List<Integer> getInput() {
        return input;
    }
	
}
