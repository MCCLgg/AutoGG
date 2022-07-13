package com.podcrash.mod.autogg.handles;

import com.podcrash.mod.autogg.AutoGG;
import com.podcrash.mod.autogg.server.Server;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.ChatType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class AutoGGHandler {
    private final String[] primaryGGStrings = {"gg", "GG"/*, "gf"*/};
    private volatile Server server;
    private long lastGG = 0;
    
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event){
        if (!(event.getEntity() instanceof LocalPlayer)) return;
        if (event.getEntity() == Minecraft.getInstance().player){
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
        if (event.getType() == ChatType.GAME_INFO) return;
        String stripped = event.getMessage().getString();
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
                        if (trigger.triggers(stripped)){
                            invokeGG();
                        }
                }
            }))).start();
        }
    }
    
    private void invokeGG( ){
        if (server != null){
            if (Minecraft.getInstance().player == null) return;
            String prefix = server.getMessagePrefix();
            
            if (System.currentTimeMillis() - lastGG < 10_000) return;
            lastGG = System.currentTimeMillis();
            
            String msg = primaryGGStrings[new Random().nextInt(primaryGGStrings.length)];
            int delay = 300; //In milliseconds
            
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run( ){
                    Minecraft.getInstance().player.chat(prefix.isEmpty() ? msg : prefix + " " + msg);
                }
            };
            
            timer.schedule(task, TimeUnit.MILLISECONDS.toMillis(delay));
            
        }
    }
}
