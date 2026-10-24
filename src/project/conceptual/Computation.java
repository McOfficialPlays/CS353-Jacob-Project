package project.conceptual;

import java.util.ArrayList;
import java.util.List;

public class Computation implements ComputationAPI {
	@Override
    public ComputationResult compute(ComputationInput input) {
		int getInput = input.getValue();
		ArrayList<Integer> ans = new ArrayList<>();
		//allows for 1 to be an answer
        factor(getInput, ans);
    	return new ComputationResult() {
			@Override
			public List<Integer> getPrimeFactors(){
				return ans;
			}

			@Override
			public int getInput() {
				// TODO Auto-generated method stub
				return input.getValue();
			}
    	};
    }
	
	private ArrayList<Integer> factor(Integer input, ArrayList<Integer> ans){
		if(input == 1) {
			ans.add(1);
		}
        for(int i = 2; i <= input; i++) {
        	while(input % i == 0 && input > 0) {
        		ans.add(i);
        		input = input / i;
        	}
        }
		return ans;
	}
}