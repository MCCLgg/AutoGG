package com.podcrash.mod.autogg;

import com.podcrash.mod.autogg.handles.AutoGGHandler;
import com.podcrash.mod.autogg.server.Server;
import com.podcrash.mod.autogg.server.ServerManager;
import com.podcrash.mod.autogg.server.servers.Hypixel;
import com.podcrash.mod.autogg.server.servers.Mineplex;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("autogg")
public class AutoGG {
    /** This is the instance of your mod as created by Forge. It will never be null. */
    
    public static AutoGG instance;
    private ServerManager servers;
    
    public AutoGG( ){
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        instance = this;
    }
    
    private void doClientStuff(final FMLClientSetupEvent event){
        // do something that can only be done on the client
        AutoGG.instance.registerServers(new Hypixel(), new Mineplex()/*, new LocalHost()*/);
        MinecraftForge.EVENT_BUS.register(new AutoGGHandler());
    }
    
    
    public ServerManager getServerManager( ){
        return servers;
    }
    
    public void registerServers(Server... servers){
        this.servers = new ServerManager(servers);
    }
    
}
