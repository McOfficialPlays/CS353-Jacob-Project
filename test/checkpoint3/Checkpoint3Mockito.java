package checkpoint3;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import project.conceptual.ComputationAPI;
import project.network.ComputeEngine;
import project.network.ComputeEngineAPI;
import project.network.JobRequest;
import project.process.DataStorage;
import project.process.DataStorageAPI;
import project.process.InputSource;
import project.process.IntegerData;
import project.process.OutputData;
import project.process.OutputDestination;

public class Checkpoint3Mockito {
	@Test
	void testSubmitJob() {
	    DataStorageAPI dataStorage = mock(DataStorageAPI.class);
	    ComputationAPI computation = mock(ComputationAPI.class);
	    JobRequest request = mock(JobRequest.class);

	    ComputeEngineAPI computeEngine =
	            new ComputeEngine(dataStorage, computation);

	    computeEngine.submitJob(request);
	}
	@Test
	void testWriteOutputStorage() {
		OutputDestination outputDestination = mock(OutputDestination.class);
		OutputData outputData = mock(OutputData.class);
		
		DataStorageAPI data = new DataStorage();
		data.writeOutput(outputDestination, outputData);
		
	}
	@Test
	void testReadInput() {
		InputSource source = mock(InputSource.class);
		DataStorageAPI data = new DataStorage();
		IntegerData result = data.readInput(source);
		Assertions.assertNotNull(result);
		
	}
}
