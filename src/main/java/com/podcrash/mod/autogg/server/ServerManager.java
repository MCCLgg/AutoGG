package com.podcrash.mod.autogg.server;

public class ServerManager {
    private final Server[] servers;
    
    public ServerManager(Server... servers){
        this.servers = servers;
    }
    
    public Server[] getServers( ){
        return servers;
    }
}
