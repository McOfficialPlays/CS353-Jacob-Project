package project.network;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface ComputeEngineAPI {
    void submitJob(JobRequest request);


}
