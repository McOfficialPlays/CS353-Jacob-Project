package smoketests;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import project.conceptual.ComputationAPI;
import project.network.ComputeEngine;
import project.network.ComputeEngineAPI;
import project.network.JobRequest;
import project.process.DataStorageAPI;

public class TestComputeEngineAPI {
	@Test
	void testSubmitJob() {
	    DataStorageAPI dataStorage = mock(DataStorageAPI.class);
	    ComputationAPI computation = mock(ComputationAPI.class);
	    JobRequest request = mock(JobRequest.class);

	    ComputeEngineAPI computeEngine =
	            new ComputeEngine(dataStorage, computation);

	    computeEngine.submitJob(request);
	}
}
