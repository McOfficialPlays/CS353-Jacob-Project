package project.conceptual;

import project.annotations.ConceptualAPIPrototype;

/**
 * Prototype client for the computation API.
 */
public class ComputationAPIPrototype {
    //Demonstrates how the job handler can use the computation API.
     
    @ConceptualAPIPrototype
    public void prototype(ComputationAPI api) {
    	ComputationInput input = new ComputationInput() {
    	    @Override
    	    public int getValue() {
    	        return 84;
    	    }
    	};

        api.compute(input);
    }
}
