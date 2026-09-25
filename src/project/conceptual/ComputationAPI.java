package project.conceptual;

import project.annotations.ConceptualAPI;


/**
 * Conceptual API between the job handler and the computation component.
 */
@ConceptualAPI
public interface ComputationAPI {
    //Performs the project's computation for one positive integer.
    ComputationResult compute(int input);
}
