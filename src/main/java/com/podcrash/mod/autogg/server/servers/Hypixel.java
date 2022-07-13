package com.podcrash.mod.autogg.server.servers;

import com.podcrash.mod.autogg.server.Server;
import com.podcrash.mod.autogg.trigger.Trigger;
import com.podcrash.mod.autogg.trigger.TriggerType;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public final class Hypixel extends Server {
    public Hypixel( ){
        super(
                "Hypixel Server",
                "Hypixel BungeeCord \\(.+\\) <- .+",
                "/ac",
                getHypixelTriggers()
        );
    }
    
    private static ArrayList<Trigger> getHypixelTriggers( ){
        final ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +1st Killer - ?\\[?\\w*\\+*\\]? \\w+ - \\d+(?: Kills?)?$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ *1st (?:Place ?)?(?:-|:)? ?\\[?\\w*\\+*\\]? \\w+(?: : \\d+| - \\d+(?: Points?)?| - \\d+(?: x .)?| \\(\\w+ .{1,6}\\) - \\d+ Kills?|: \\d+:\\d+| - \\d+ (?:Zombie )?(?:Kills?|Blocks? Destroyed)| - \\[LINK\\])?$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +Winn(?:er #1 \\(\\d+ Kills\\): \\w+ \\(\\w+\\)|er(?::| - )(?:Hiders|Seekers|Defenders|Attackers|PLAYERS?|MURDERERS?|Red|Blue|RED|BLU|\\w+)(?: Team)?|ers?: ?\\[?\\w*\\+*\\]? \\w+(?:, ?\\[?\\w*\\+*\\]? \\w+)?|ing Team ?[\\:-] (?:Animals|Hunters|Red|Green|Blue|Yellow|RED|BLU|Survivors|Vampires))$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +Alpha Infected: \\w+ \\(\\d+ infections?\\)$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +Murderer: \\w+ \\(\\d+ Kills?\\)$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +You survived \\d+ rounds!$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +(?:UHC|SkyWars|Bridge|Sumo|Classic|OP|MegaWalls|Bow|NoDebuff|Blitz|Combo|Bow Spleef) (?:Duel|Doubles|3v3|4v4|Teams|Deathmatch|2v2v2v2|3v3v3v3)? ?- \\d+:\\d+$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +They captured all wools!$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +Game over!$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +[\\d\\.]+k?/[\\d\\.]+k? \\w+$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +(?:Criminal|Cop)s won the game!$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +\\[?\\w*\\+*\\]? \\w+ - \\d+ Final Kills$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +Zombies - \\d*:?\\d+:\\d+ \\(Round \\d+\\)$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ +. YOUR STATISTICS .$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ {36}Winner(s?)$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^ {21}Bridge CTF [a-zA-Z]+ - \\d\\d:\\d\\d$"));
        triggers.add(new Trigger(TriggerType.CASUAL, "^MINOR EVENT! .+ in .+ ended$"));
        triggers.add(new Trigger(TriggerType.CASUAL, "^DRAGON EGG OVER! Earned [\\d,]+XP [\\d,]g clicking the egg \\d+ times$"));
        triggers.add(new Trigger(TriggerType.CASUAL, "^GIANT CAKE! Event ended! Cake's gone!$"));
        triggers.add(new Trigger(TriggerType.CASUAL, "^PIT EVENT ENDED: .+ \\[INFO\\]$"));
        //triggers.add(new Trigger(2,"^(?:.* )?(?:\\[.+] )?\\w{1,16}(?: .+)?: (?:❤|${antigg_strings})$" ));
        triggers.add(new Trigger(TriggerType.ANTI_KARMA, "^\\+(?<karma>\\d)+ Karma!$"));
        triggers.add(new Trigger(TriggerType.NORMAL, "^You died use your compass to spectate players!$"));
        return triggers;
    }
    
    @Override
    public boolean matchServer( ){
        return super.ipPattern.matcher(Minecraft.getMinecraft().thePlayer.getClientBrand()).matches();
    }
    
}
