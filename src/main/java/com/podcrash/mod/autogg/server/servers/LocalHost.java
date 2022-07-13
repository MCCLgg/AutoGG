package com.podcrash.mod.autogg.server.servers;

import com.podcrash.mod.autogg.server.Server;
import com.podcrash.mod.autogg.trigger.Trigger;
import com.podcrash.mod.autogg.trigger.TriggerType;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Collections;

public final class LocalHost extends Server {
    public LocalHost( ){
        super(
                "LocalHost",
                "localhost",
                "",
                getLocalHostTriggers()
        );
    }
    
    private static ArrayList<Trigger> getLocalHostTriggers( ){
        final Trigger localTrigger = new Trigger(TriggerType.NORMAL, "^ +1st Killer - \\[\\w+\\+\\] \\w+ - \\d+$");
        return new ArrayList<>(Collections.singletonList(localTrigger));
    }
    
    @Override
    public boolean matchServer( ){
        if (Minecraft.getInstance().getCurrentServerData() == null) return false;
        return super.ipPattern.matcher(Minecraft.getInstance().getCurrentServerData().serverIP).matches();
    }
}