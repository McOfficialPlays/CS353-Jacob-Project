package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import project.conceptual.Computation;
import project.conceptual.ComputationAPI;
import project.conceptual.ComputationInput;
import project.conceptual.ComputationResult;
import project.network.ComputeEngine;
import project.network.ComputeEngineAPI;
import project.network.JobRequest;
import project.network.ResultDelimiters;
import project.process.DataStorage;
import project.process.DataStorageAPI;
import project.process.InputSource;
import project.process.OutputDestination;

public class ComputeEngineIntegrationTest {
	@Test
	public void integrationTest() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 10, 25));
		List<String> output = new ArrayList<>();
		
		//Instantiate in memory test cases
		InMemoryOutput outputDestination = new InMemoryOutput(output);
		InMemoryDataStore dataStorage = new InMemoryDataStore();
		InMemoryInputSource inputSource = new InMemoryInputSource(input);
		
		//Instantiate all APIs
		ComputationAPI computation = new Computation();
		ComputeEngineAPI compute = new ComputeEngine(dataStorage, computation);
		DataStorageAPI data = new DataStorage();
		//Setting Delimiters to default
		ResultDelimiters delimiters = ResultDelimiters.defaults();
		//Job Request
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
			}
		};
		compute.submitJob(request);
		//expected prime factorization
		List<String> expected = Arrays.asList("1:1","10:2;5","25:5;5");

		assertEquals(expected, outputDestination.getOutput());
	
	}
	@Test
	public void testExceptions() {
		List<Integer> input = new ArrayList<>(Arrays.asList(10, 25));
		List<String> output = new ArrayList<>();
		
		//Instantiate in memory test cases
		InMemoryOutput outputDestination = new InMemoryOutput(output);
		InMemoryInputSource inputSource = new InMemoryInputSource(input);
		
		//Instantiate all APIs
		DataStorageAPI dataStorage = new InMemoryDataStore();
		ComputationAPI computation = new Computation() {
			@Override
			public ComputationResult compute(ComputationInput input) {
				throw new RuntimeException("Simulated computation failure");
			}
		};
		ComputeEngineAPI compute = new ComputeEngine(dataStorage, computation);
		
		//Setting Delimiters to default
		ResultDelimiters delimiters = ResultDelimiters.defaults();
		//Job Request
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
			}
		};
		boolean result = compute.submitJob(request);
		//expected prime factorization
		
		assertFalse(result);
		
		assertTrue(outputDestination.getOutput().isEmpty());
	
	}
	
}
