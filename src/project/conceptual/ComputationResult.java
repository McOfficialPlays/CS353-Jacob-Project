package project.conceptual;

import java.util.List;

public interface ComputationResult {
    //Returns the input integer associated with this result.
    int getInput();
    // Returns the prime factors of the input in ascending order.
    List<Integer> getPrimeFactors();
}