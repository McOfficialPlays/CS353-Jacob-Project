package checkpoint3;

import java.util.List;

import project.process.OutputDestination;

public class InMemoryOutput implements OutputDestination {
	private List<String> output;
	public InMemoryOutput(List<String> output) {
		this.output = output;
	}
	public List<String> getOutput(){
		return output;
	}
	 public void setOutput(List<String> output) {
	        this.output = output;
	    }
}
