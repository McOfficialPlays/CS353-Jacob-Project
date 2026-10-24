package smoketests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import project.process.DataStorage;
import project.process.DataStorageAPI;
import project.process.InputSource;
import project.process.IntegerData;


public class TestDataStorageAPI {
	 @Test
	    void testReadInput() throws Exception {

	        // Create a temporary input file
	        Path file = Files.createTempFile("input", ".txt");
	        try {
	            Files.writeString(file, "1, 10, 25");

	            // Mock the input source
	            InputSource source = mock(InputSource.class);

	            when(source.getIdentifier()).thenReturn(file.toString());
	            // Instantiate actual implementation
	            DataStorageAPI data = new DataStorage();
	            // Read input
	            IntegerData result = data.readInput(source);
	            Assertions.assertNotNull(result);
	            Assertions.assertEquals(
	                Arrays.asList(1, 10, 25),
	                result.getValues()
	            );
	        } finally {
	            Files.deleteIfExists(file);
	        }
	 	}
	
}
