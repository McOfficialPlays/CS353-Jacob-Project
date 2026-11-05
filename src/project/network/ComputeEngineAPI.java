package project.network;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface ComputeEngineAPI {
    boolean submitJob(JobRequest request);
}
