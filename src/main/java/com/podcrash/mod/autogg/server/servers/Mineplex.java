package com.podcrash.mod.autogg.server.servers;

import com.podcrash.mod.autogg.server.Server;
import com.podcrash.mod.autogg.trigger.Trigger;
import com.podcrash.mod.autogg.trigger.TriggerType;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public final class Mineplex extends Server {
    public Mineplex( ){
        super(
                "Mineplex Server",
                "^(?:(?:us|eu)\\.)?mineplex\\.com$",
                "",
                getMineplexTriggers()
        );
    }
    
    private static ArrayList<Trigger> getMineplexTriggers( ){
        
        final ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(TriggerType.NORMAL, "^ .* won the game!$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ 1st Place \\w{1,16}(?:, \\w{1,16})*$"));
        //triggers.add(new Trigger(2,"^\\d+ [A-Z ]+ (?:. )?\\w{1,16} (?:${antigg_strings})$" ));
        
        return triggers;
    }
    
    @Override
    public boolean matchServer( ){
        if (Minecraft.getInstance().getCurrentServerData() == null) return false;
        return super.ipPattern.matcher(Minecraft.getInstance().getCurrentServerData().serverIP).matches();
    }
}