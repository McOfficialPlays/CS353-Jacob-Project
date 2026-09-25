package project.process;

import project.annotations.ProcessAPI;

/**
 * Process-level API used by the compute engine to read and write job data.
 */
@ProcessAPI
public interface DataStorageAPI {
    //Reads integer input from the requested source.
    IntegerData readInput(InputSource source);
    //Writes computation results to the requested destination.
    void writeOutput(OutputDestination destination, OutputData output);
}

