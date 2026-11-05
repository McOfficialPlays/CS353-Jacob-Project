package project.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStorage implements DataStorageAPI {
	@Override
	public IntegerData readInput(InputSource source) {
		if (source == null) {
		    System.err.println("Input source cannot be null");
		    return null;
		}
		List<Integer> values = new ArrayList<Integer>();
		try {
			File inputFile = new File(source.getIdentifier());
			String path = source.getIdentifier();
			if(path == null || path.isBlank()) {
				return null;
			}
			try(Scanner s = new Scanner(inputFile)){
				s.useDelimiter(",");
				while(s.hasNext()) {
					String token = s.next().trim();
					if(!token.isEmpty()) {
						try {
	                        values.add(Integer.parseInt(token));
	                    } catch (NumberFormatException e) {
	                        System.err.println("Skipping invalid integer: \"" + token + "\"");
	                    }
					}
				}
			}catch(FileNotFoundException e) {
				System.out.println("File not found.");
				return null;
			}
			return new IntegerData(){
					@Override
					public List<Integer> getValues(){
						return values;
					}
			};
		}catch(Exception e) {
	        System.err.println("Failed to read input: " + e.getMessage());
	        return null;
		}
	}
	
	@Override
    public boolean writeOutput(OutputDestination destination, OutputData output) {
        // Validate parameters
        if (destination == null || output == null) {
            System.err.println("Destination or output data cannot be null");
            return false;
        }
        try {
            String path = destination.getPath();
            // Validate output file path
            if (path == null || path.isBlank()) {
                System.err.println("Invalid output file path");
                return false;
            }

            List<String> values = output.getValues();

            // Validate output data
            if (values == null || values.contains(null)) {
                System.err.println("Invalid output values");
                return false;
            }

            File outputFile = new File(path);

            try (FileWriter fw = new FileWriter(outputFile);
                 PrintWriter pw = new PrintWriter(fw)) {

            	pw.println(output.getValues());

                // Detect PrintWriter errors
                if (pw.checkError()) {
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            System.err.println(
                "Failed to write output: " + e.getMessage()
            );
            return false;
        }
    }
}
