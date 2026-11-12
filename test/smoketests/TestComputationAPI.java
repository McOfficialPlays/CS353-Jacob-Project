package smoketests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import project.conceptual.Computation;
import project.conceptual.ComputationAPI;
import project.conceptual.ComputationInput;
import project.conceptual.ComputationResult;

public class TestComputationAPI {
	@Test
	void testComputationResult() {
		ComputationInput input = mock(ComputationInput.class);
		//add to not have a failure since this didnt have a value
		when(input.getValue()).thenReturn(10);
		ComputationAPI computation = new Computation();
		ComputationResult result = computation.compute(input);
		Assertions.assertNotNull(result);
	}

	@Test
	public void testInvalidInput() {
	
	    ComputationAPI computation = new Computation();
	
	    ComputationInput input = new ComputationInput() {
	        @Override
	        public int getValue() {
	            return -5;
	        }
	    };
	
	    Assertions.assertThrows(IllegalArgumentException.class, () -> computation.compute(input));
	}

}
