package project.conceptual;

import project.annotations.ConceptualAPIPrototype;

/**
 * Prototype client for the computation API.
 */
public class ComputationAPIPrototype {
    //Demonstrates how the job handler can use the computation API.
     
    @ConceptualAPIPrototype
    public void prototype(ComputationAPI api) {
        int input = 84;
        api.compute(input);
    }
}
