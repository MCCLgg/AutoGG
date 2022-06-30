package com.podcrash.mod.autogg.trigger;

import java.util.regex.Pattern;

public class Trigger {
    private final int type;

    private final String pattern;

    private TriggerType triggerType;
    
    private final Pattern regex;

    public Trigger(int type, String pattern) {
        this.type = type;
        this.pattern = pattern;
        regex  = Pattern.compile(pattern);
    }

    public TriggerType getType() {
        if (triggerType == null) triggerType = TriggerType.getByType(type);
        return triggerType;
    }

    public String getPattern() {
        return pattern;
    }
    
    public boolean triggers(String data){
        return regex.matcher(data).matches();
    }
}
