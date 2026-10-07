package smoketests;

import static org.mockito.Mockito.mock;

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
		ComputationAPI computation = new Computation();
		ComputationResult result = computation.compute(input);
		Assertions.assertNotNull(result);
	}
}
