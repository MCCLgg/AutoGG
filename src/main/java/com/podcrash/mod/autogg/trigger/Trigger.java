package com.podcrash.mod.autogg.trigger;

import java.util.regex.Pattern;

public final class Trigger {
    
    
    private final String pattern;
    private final TriggerType type;
    private final Pattern regex;
    
    public Trigger(TriggerType type, String pattern){
        this.type = type;
        this.pattern = pattern;
        regex = Pattern.compile(pattern);
    }
    
    public TriggerType getType( ){
        return type;
    }
    
    public String getPattern( ){
        return pattern;
    }
    
    public boolean triggers(String data){
        return regex.matcher(data).matches();
    }
}
