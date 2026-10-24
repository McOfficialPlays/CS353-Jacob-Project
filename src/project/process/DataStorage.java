package project.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStorage implements DataStorageAPI {
	@Override
	public IntegerData readInput(InputSource source) {
		List<Integer> values = new ArrayList<Integer>();
		File inputFile = new File(source.getIdentifier());
		try(Scanner s = new Scanner(inputFile)){
			while(s.hasNextLine()) {
				//current format each line has an integer on it that is the number you want to factor
				String line = s.nextLine().trim();
				if(!line.isEmpty()) {
					values.add(Integer.parseInt(line));
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
		try(FileWriter fw = new FileWriter(outputFile, true); PrintWriter pw = new PrintWriter(fw)){
			pw.println((output.getValues().toString()));
		}catch(IOException e) {
			System.out.println("File not found.");
			
		}
		
	}
}