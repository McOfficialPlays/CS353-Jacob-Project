package smoketests;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import project.process.DataStorage;
import project.process.DataStorageAPI;
import project.process.InputSource;
import project.process.IntegerData;


public class TestDataStorageAPI {
	@Test
	void testReadInput() {
		InputSource source = mock(InputSource.class);
		DataStorageAPI data = new DataStorage();
		IntegerData result = data.readInput(source);
		Assertions.assertNotNull(result);
		
	}
}
