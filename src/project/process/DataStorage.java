package project.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStorage implements DataStorageAPI {
	@Override
	public IntegerData readInput(InputSource source) {
		List<Integer> values = new ArrayList<Integer>();
		File inputFile = new File(source.getIdentifier());
		try(Scanner s = new Scanner(inputFile)){
			s.useDelimiter(",");
			while(s.hasNext()) {
				//current format each line has an integer on it that is the number you want to factor
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
			
	}
	
	@Override
	public void writeOutput(OutputDestination destination, OutputData output) {
		// TODO Auto-generated method stub
		File outputFile = new File(destination.getPath());

		try (FileWriter fw = new FileWriter(outputFile);
		         PrintWriter pw = new PrintWriter(fw)) {

		        pw.println(output.getValues());

		    } catch (IOException e) {
		        throw new RuntimeException("Failed to write output file.", e);
		    }
		
	}
}