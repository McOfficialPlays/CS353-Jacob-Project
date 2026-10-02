package project.network;

import project.annotations.NetworkAPIPrototype;
import project.process.InputSource;
import project.process.OutputDestination;
// Prototype client for the network API.

public class ComputeEngineAPIPrototype {
    // Demonstrates how a client can use the network API.
     
    @NetworkAPIPrototype
    public void prototype(ComputeEngineAPI api) {
    	InputSource inputSource = new InputSource() {
			@Override
			public String getIdentifier() {
				// TODO Auto-generated method stub
				return "PrototypeID";
			}};
		OutputDestination outputDestination = new OutputDestination() {};
		ResultDelimiters delimiters = ResultDelimiters.defaults();
        JobRequest request = new JobRequest() {

			@Override
			public InputSource getInputSource() {
				// TODO Auto-generated method stub
				return inputSource;
			}

			@Override
			public OutputDestination getOutputDestination() {
				// TODO Auto-generated method stub
				return outputDestination;
			}

			@Override
			public ResultDelimiters getDelimiters() {
				// TODO Auto-generated method stub
				return delimiters;
			}};
			
        api.submitJob(request);
    }
}
