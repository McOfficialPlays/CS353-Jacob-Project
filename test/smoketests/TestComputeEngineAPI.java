package smoketests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import project.conceptual.ComputationAPI;
import project.conceptual.ComputationInput;
import project.conceptual.ComputationResult;
import project.network.ComputeEngine;
import project.network.ComputeEngineAPI;
import project.network.JobRequest;
import project.network.ResultDelimiters;
import project.process.DataStorageAPI;
import project.process.InputSource;
import project.process.IntegerData;
import project.process.OutputData;
import project.process.OutputDestination;

public class TestComputeEngineAPI {

    @Test
    void testSubmitJob() {

        // Mock dependencies
        DataStorageAPI dataStorage = mock(DataStorageAPI.class);
        ComputationAPI computation = mock(ComputationAPI.class);
        JobRequest request = mock(JobRequest.class);

        InputSource source = mock(InputSource.class);
        OutputDestination destination = mock(OutputDestination.class);
        IntegerData inputData = mock(IntegerData.class);
        ComputationResult result = mock(ComputationResult.class);

        // Configure job request
        when(request.getInputSource()).thenReturn(source);
        when(request.getOutputDestination()).thenReturn(destination);
        when(request.getDelimiters())
            .thenReturn(ResultDelimiters.defaults());

        // Configure data storage
        when(dataStorage.readInput(source)).thenReturn(inputData);
        when(inputData.getValues()).thenReturn(Arrays.asList(10));

        // Configure computation
        when(result.getInput()).thenReturn(10);
        when(result.getPrimeFactors())
            .thenReturn(Arrays.asList(2, 5));

        when(computation.compute(any(ComputationInput.class)))
            .thenReturn(result);

        // Instantiate real ComputeEngine
        ComputeEngineAPI computeEngine =
            new ComputeEngine(dataStorage, computation);

        // Submit job
        computeEngine.submitJob(request);

        // Verify coordination
        verify(dataStorage).readInput(source);

        verify(computation).compute(any(ComputationInput.class));

        verify(dataStorage).writeOutput(
            eq(destination),
            any(OutputData.class)
        );
    }
    @Test
    void testNullJobRequest() {

        DataStorageAPI dataStorage = mock(DataStorageAPI.class);
        ComputationAPI computation = mock(ComputationAPI.class);

        ComputeEngineAPI computeEngine =
                new ComputeEngine(dataStorage, computation);

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> computeEngine.submitJob(null)
        );
    }
}