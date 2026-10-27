package project.checkpointtests;

import project.conceptual.Computation;
import project.network.ComputeEngine;
import project.network.JobRequest;
import project.network.ResultDelimiters;
import project.process.DataStorage;
import project.process.FileInputSource;
import project.process.FileOutputDestination;
import project.process.InputSource;
import project.process.OutputDestination;

public class ManualTestingFramework {
    
    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        // TODO 1:
        // Instantiate a real (ie, class definition lives in the src/ folder) implementation 
        // of all 3 APIs
    	Computation computation = new Computation();
    	DataStorage dataStorage = new DataStorage();
    	ComputeEngine computeEngine = new ComputeEngine(dataStorage, computation);
        //
        // TODO 2:
        // Run a computation with an input file of <root project dir>/manualTestInput.txt
        // and an output of <root project dir>/manualTestOutput.txt, with a delimiter of ',' 
        //
    	InputSource inputSource = new FileInputSource(INPUT);
        OutputDestination outputDestination = new FileOutputDestination(OUTPUT);
        ResultDelimiters delimiters = new ResultDelimiters() {

            @Override
            public String getInputResultDelimiter() {
                return ":";
            }

            @Override
            public String getResultDelimiter() {
                return ";";
            }
        };
        JobRequest request = new JobRequest() {

            @Override
            public InputSource getInputSource() {
                return inputSource;
            }

            @Override
            public OutputDestination getOutputDestination() {
                return outputDestination;
            }

            @Override
            public ResultDelimiters getDelimiters() {
                return delimiters;
            }
        };

        // Execute computation
        computeEngine.submitJob(request);
        //
        // Helpful hint: the working directory of this program is <root project dir>,
        // so you can refer to the files just using the INPUT/OUTPUT constants. You do not 
        // need to (and should not) actually create those files in your repo
    }
}
