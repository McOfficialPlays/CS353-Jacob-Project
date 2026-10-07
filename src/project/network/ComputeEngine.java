package project.network;

import project.conceptual.ComputationAPI;
import project.process.DataStorageAPI;

public class ComputeEngine implements ComputeEngineAPI{
	private DataStorageAPI dataStorage;
    private ComputationAPI computation;

    public ComputeEngine(DataStorageAPI dataStorage, ComputationAPI computation) {
        this.dataStorage = dataStorage;
        this.computation = computation;
    }
	@Override
	public void submitJob(JobRequest job) {
		
	}
	

}

