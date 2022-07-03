package com.podcrash.mod.autogg.handles;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.podcrash.mod.autogg.AutoGG;
import com.podcrash.mod.autogg.server.Server;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class AutoGGHandler {
    private final String[] primaryGGStrings = {"gg"/*, "GG", "gf", "Good Game", "Good Fight", "Good Round! :D"*/};
    private volatile Server server;
    private long lastGG = 0;
    //private final String[] secondaryGGStrings = {"Have a good day!", "<3", "AutoGG By Sk1er!", "gf", "Good Fight", "Good Round", ":D", "Well played!", "wp"};
    
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event){
        if (!(event.entity instanceof EntityPlayerSP)) return;
    
        if (event.entity == Minecraft.getMinecraft().thePlayer){
            new Thread(( ) -> {
                for ( Server s : AutoGG.instance.getServerManager().getServers() ){
                    try {
                        if (s.matchServer()){
                            server = s;
                            return;
                        }
                    } catch (Throwable ignored) {
                    }
                }
                
                server = null;
            }).start();
        }
    }
    
    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event){
        if (event.type == 2) return;
    
        String stripped = ChatFormatting.stripFormatting(event.message.getUnformattedText());
        if (server != null){
            new Thread(( ) -> server.getTriggers().forEach((trigger -> {
                switch(trigger.getType()){
                    case ANTI_GG:
                    case ANTI_KARMA:
                        return;
                    case NORMAL:
                        if (trigger.triggers(stripped)){
                            invokeGG();
                            return;
                        }
            
                    case CASUAL:
                        //if (AutoGG.instance.getAutoGGConfig().isCasualAutoGGEnabled()) {
                        if (trigger.triggers(stripped)){
                            invokeGG();
                        }
                        //}
                }
            }))).start();
            
            // Multithreading.runAsync();
        }
    }
    
    private void invokeGG( ){
        if (server != null){
            String prefix = server.getMessagePrefix();
            
            if (System.currentTimeMillis() - lastGG < 10_000) return;
            lastGG = System.currentTimeMillis();
            
            String msg = primaryGGStrings[new Random().nextInt(primaryGGStrings.length)];
            int delay = 1; //In seconds
            
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run( ){
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(prefix.isEmpty() ? msg : prefix + " " + msg);
                }
            };
            
            timer.schedule(task, TimeUnit.SECONDS.toMillis(delay));
            
        }
    }
}
