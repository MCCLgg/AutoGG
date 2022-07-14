package com.podcrash.mod.autogg;

import com.mojang.logging.LogUtils;
import com.podcrash.mod.autogg.handles.AutoGGHandler;
import com.podcrash.mod.autogg.server.Server;
import com.podcrash.mod.autogg.server.ServerManager;
import com.podcrash.mod.autogg.server.servers.Hypixel;
import com.podcrash.mod.autogg.server.servers.Mineplex;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("autogg")
public class AutoGG {
    /** This is the instance of your mod as created by Forge. It will never be null. */
    
    public static AutoGG instance;
    private ServerManager servers;
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public AutoGG( ){
        // Register the doClientStuff method for modloading
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(new AutoGGHandler());
    }
    
    private void doClientStuff(final FMLClientSetupEvent event){
        AutoGG.instance.registerServers(new Hypixel(), new Mineplex()/*, new LocalHost()*/);
    }
    
    
    public ServerManager getServerManager( ){
        return servers;
    }
    
    public void registerServers(Server... servers){
        this.servers = new ServerManager(servers);
    }
    
}
