package project.network;

import project.annotations.NetworkAPIPrototype;
// Prototype client for the network API.

public class ComputeEngineAPIPrototype {
    // Demonstrates how a client can use the network API.
     
    @NetworkAPIPrototype
    public void prototype(ComputeEngineAPI api) {
        JobRequest request = null;
        api.submitJob(request);
    }
}
