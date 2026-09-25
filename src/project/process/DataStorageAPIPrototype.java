package project.process;

import project.annotations.ProcessAPIPrototype;

/**
 * Prototype client for the process API.
 */
public class DataStorageAPIPrototype {
   //Demonstrates how the compute engine can use data storage.
     
    @ProcessAPIPrototype
    public void prototype(DataStorageAPI api) {
        InputSource inputSource = null;
        OutputDestination outputDestination = null;
        OutputData output = null;
        api.readInput(inputSource);
        api.writeOutput(outputDestination, output);
    }
}

