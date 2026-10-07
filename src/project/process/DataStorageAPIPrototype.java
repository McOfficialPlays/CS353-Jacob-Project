package project.process;

import java.util.List;

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
			}
		};
        OutputDestination outputDestination = new OutputDestination() {
        	//Prototype output destination.
        };
        OutputData output = new OutputData() {

			@Override
			public List<String> getValues() {
				// TODO Auto-generated method stub
				return null;
			}
        	//Prototype output data.
        };
        api.readInput(inputSource);
        api.writeOutput(outputDestination, output);
    }
}

