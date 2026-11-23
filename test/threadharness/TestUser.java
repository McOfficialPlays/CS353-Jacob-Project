package threadharness;

import java.io.File;

import project.network.ComputeEngineAPI;
import project.network.JobRequest;
import project.network.ResultDelimiters;
import project.process.InputSource;
import project.process.OutputDestination;


public class TestUser {
	
	// TODO 3: change the type of this variable to the name you're using for your
	// @NetworkAPI interface; also update the parameter passed to the constructor
	private final ComputeEngineAPI 	coordinator;

	public TestUser(ComputeEngineAPI coordinator) {
		this.coordinator = coordinator;
	}

	public void run(String outputPath) {
		char delimiter = ';';
		String inputPath = "test" + File.separatorChar + "testInputFile.test";
		
		// TODO 4: Call the appropriate method(s) on the coordinator to get it to 
		// run the compute job specified by inputPath, outputPath, and delimiter
		JobRequest job =  new JobRequest() {

			@Override
			public InputSource getInputSource() {
				// TODO Auto-generated method stub
				return new InputSource() {

					@Override
					public String getIdentifier() {
						// TODO Auto-generated method stub
						return inputPath;
					}
					
				};
			}

			@Override
			public OutputDestination getOutputDestination() {
				// TODO Auto-generated method stub
				return new OutputDestination() {

					@Override
					public String getPath() {
						// TODO Auto-generated method stub
						return outputPath;
					}
					
				};
			}

			@Override
			public ResultDelimiters getDelimiters() {
				// TODO Auto-generated method stub
				return new ResultDelimiters(){

					@Override
					public String getInputResultDelimiter() {
						// TODO Auto-generated method stub
						return ":";
					}

					@Override
					public String getResultDelimiter() {
						// TODO Auto-generated method stub
						return String.valueOf(delimiter);
					}
					
				};
			}
		};
		coordinator.submitJob(job);
	}

}
