package project.conceptual;

import java.util.ArrayList;
import java.util.List;

public class Computation implements ComputationAPI {
	@Override
    public ComputationResult compute(ComputationInput input) {
		//validate input object
		if(input == null) {
			throw new IllegalArgumentException("Input not valid");
		}
		int number = input.getValue();
		//validate integer value
        if(number <= 0) {
			throw new IllegalArgumentException("Input must be greater than zero");
		}
        ArrayList<Integer> ans = new ArrayList<>();
        factor(number, ans);
    	return new ComputationResult() {
			@Override
			public List<Integer> getPrimeFactors(){
				return ans;
			}

			@Override
			public int getInput() {
				// TODO Auto-generated method stub
				return number;
			}
    	};
    }
	
	private ArrayList<Integer> factor(Integer input, ArrayList<Integer> ans){
		//allows for 1 to be an answer
		if(input == 1) {
			ans.add(1);
		}
		//factoring logic
        for(int i = 2; i <= input; i++) {
        	while(input % i == 0 && input > 0) {
        		ans.add(i);
        		input = input / i;
        	}
        }
		return ans;
	}
}