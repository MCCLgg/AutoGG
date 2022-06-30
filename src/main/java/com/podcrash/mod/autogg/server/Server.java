package com.podcrash.mod.autogg.server;

import com.podcrash.mod.autogg.trigger.Trigger;

import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class Server {
    private final String name;
    private final String messagePrefix;
    private final ArrayList<Trigger> triggers;
    protected final Pattern ipPattern;
    
    public Server(String name, String regex, String messagePrefix, ArrayList<Trigger> triggers){
        this.name = name;
        this.messagePrefix = messagePrefix;
        this.triggers = triggers;
        ipPattern = Pattern.compile(regex);
    }
    
    
    public String getName( ){
        return name;
    }
    
    public ArrayList<Trigger> getTriggers( ){
        return triggers;
    }
    
    public abstract boolean matchServer();
    
    public String getMessagePrefix( ){
        return messagePrefix;
    }
}
