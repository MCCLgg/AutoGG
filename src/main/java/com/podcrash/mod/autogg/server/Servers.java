package com.podcrash.mod.autogg.server;

import com.podcrash.mod.autogg.trigger.Trigger;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Collections;

public class Servers {
    
    private static ArrayList<Trigger> getHypixelTriggers( ){
        final ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(0, "^ +1st Killer - ?\\[?\\w*\\+*\\]? \\w+ - \\d+(?: Kills?)?$"));
        triggers.add(new Trigger(0, "^ *1st (?:Place ?)?(?:-|:)? ?\\[?\\w*\\+*\\]? \\w+(?: : \\d+| - \\d+(?: Points?)?| - \\d+(?: x .)?| \\(\\w+ .{1,6}\\) - \\d+ Kills?|: \\d+:\\d+| - \\d+ (?:Zombie )?(?:Kills?|Blocks? Destroyed)| - \\[LINK\\])?$"));
        triggers.add(new Trigger(0, "^ +Winn(?:er #1 \\(\\d+ Kills\\): \\w+ \\(\\w+\\)|er(?::| - )(?:Hiders|Seekers|Defenders|Attackers|PLAYERS?|MURDERERS?|Red|Blue|RED|BLU|\\w+)(?: Team)?|ers?: ?\\[?\\w*\\+*\\]? \\w+(?:, ?\\[?\\w*\\+*\\]? \\w+)?|ing Team ?[\\:-] (?:Animals|Hunters|Red|Green|Blue|Yellow|RED|BLU|Survivors|Vampires))$"));
        triggers.add(new Trigger(0, "^ +Alpha Infected: \\w+ \\(\\d+ infections?\\)$"));
        triggers.add(new Trigger(0, "^ +Murderer: \\w+ \\(\\d+ Kills?\\)$"));
        triggers.add(new Trigger(0, "^ +You survived \\d+ rounds!$"));
        triggers.add(new Trigger(0, "^ +(?:UHC|SkyWars|Bridge|Sumo|Classic|OP|MegaWalls|Bow|NoDebuff|Blitz|Combo|Bow Spleef) (?:Duel|Doubles|3v3|4v4|Teams|Deathmatch|2v2v2v2|3v3v3v3)? ?- \\d+:\\d+$"));
        triggers.add(new Trigger(0, "^ +They captured all wools!$"));
        triggers.add(new Trigger(0, "^ +Game over!$"));
        triggers.add(new Trigger(0, "^ +[\\d\\.]+k?/[\\d\\.]+k? \\w+$"));
        triggers.add(new Trigger(0, "^ +(?:Criminal|Cop)s won the game!$"));
        triggers.add(new Trigger(0, "^ +\\[?\\w*\\+*\\]? \\w+ - \\d+ Final Kills$"));
        triggers.add(new Trigger(0, "^ +Zombies - \\d*:?\\d+:\\d+ \\(Round \\d+\\)$"));
        triggers.add(new Trigger(0, "^ +. YOUR STATISTICS .$"));
        triggers.add(new Trigger(0, "^ {36}Winner(s?)$"));
        triggers.add(new Trigger(0, "^ {21}Bridge CTF [a-zA-Z]+ - \\d\\d:\\d\\d$"));
        triggers.add(new Trigger(1, "^MINOR EVENT! .+ in .+ ended$"));
        triggers.add(new Trigger(1, "^DRAGON EGG OVER! Earned [\\d,]+XP [\\d,]g clicking the egg \\d+ times$"));
        triggers.add(new Trigger(1, "^GIANT CAKE! Event ended! Cake's gone!$"));
        triggers.add(new Trigger(1, "^PIT EVENT ENDED: .+ \\[INFO\\]$"));
        //triggers.add(new Trigger(2,"^(?:.* )?(?:\\[.+] )?\\w{1,16}(?: .+)?: (?:❤|${antigg_strings})$" ));
        triggers.add(new Trigger(3, "^\\+(?<karma>\\d)+ Karma!$"));
    
        return triggers;
    }
    
    private static ArrayList<Trigger> getMineplexTriggers( ){
    
        final ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(0, "^ .* won the game!$"));
        triggers.add(new Trigger(0, "^ 1st Place \\w{1,16}(?:, \\w{1,16})*$"));
        //triggers.add(new Trigger(2,"^\\d+ [A-Z ]+ (?:. )?\\w{1,16} (?:${antigg_strings})$" ));
    
        return triggers;
    }
    
    private static ArrayList<Trigger> getLocalHostTriggers( ){
        final Trigger localTrigger = new Trigger(0, "^ +1st Killer - \\[\\w+\\+\\] \\w+ - \\d+$");
        
        return new ArrayList<>(Collections.singletonList(localTrigger));
    }
    
    public static final class Hypixel extends Server {
        public Hypixel( ){
            super(
                    "Hypixel Server",
                    "Hypixel BungeeCord \\(.+\\) <- .+",
                    "/ac",
                    getHypixelTriggers()
            );
        }
        
        @Override
        public boolean matchServer( ){
            return super.ipPattern.matcher(Minecraft.getMinecraft().thePlayer.getClientBrand()).matches();
        }
    }
    
    public static final class Mineplex extends Server {
        public Mineplex( ){
            super(
                    "Mineplex Server",
                    "^(?:(?:us|eu)\\.)?mineplex\\.com$",
                    "",
                    getMineplexTriggers()
            );
        }
        
        @Override
        public boolean matchServer( ){
            return super.ipPattern.matcher(Minecraft.getMinecraft().getCurrentServerData().serverIP).matches();
        }
    }
    
    public static final class LocalHost extends Server {
        public LocalHost( ){
            super(
                    "LocalHost",
                    "localhost",
                    "",
                    getLocalHostTriggers()
            );
        }
        
        @Override
        public boolean matchServer( ){
            return super.ipPattern.matcher(Minecraft.getMinecraft().getCurrentServerData().serverIP).matches();
        }
    }
}
