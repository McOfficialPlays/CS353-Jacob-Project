package project.network;

import project.process.InputSource;
import project.process.OutputDestination;

public interface JobRequest {
    InputSource getInputSource();
    OutputDestination getOutputDestination();
    ResultDelimiters getDelimiters();
}