package project.process;

import project.annotations.ProcessAPIPrototype;

/**
 * Prototype client for the process API.
 */
public class DataStorageAPIPrototype {
   //Demonstrates how the compute engine can use data storage.
     
    @ProcessAPIPrototype
    public void prototype(DataStorageAPI api) {
        InputSource inputSource = new InputSource() {

			@Override
			public String getIdentifier() {
				// TODO Auto-generated method stub
				return "PrototypeID";
			}};
        OutputDestination outputDestination = new OutputDestination() {
        	//Prototype output destination.
        };
        OutputData output = new OutputData() {
        	//Prototype output data.
        };
        api.readInput(inputSource);
        api.writeOutput(outputDestination, output);
    }
}

