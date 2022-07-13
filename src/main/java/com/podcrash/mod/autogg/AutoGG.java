package com.podcrash.mod.autogg;

import com.podcrash.mod.autogg.commands.TestCommand;
import com.podcrash.mod.autogg.handles.AutoGGHandler;
import com.podcrash.mod.autogg.server.Server;
import com.podcrash.mod.autogg.server.ServerManager;
import com.podcrash.mod.autogg.server.servers.Hypixel;
import com.podcrash.mod.autogg.server.servers.Mineplex;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = "autogg", name = "AutoGG", version = "1.1-SNAPSHOT")
public class AutoGG {
    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance("autogg")
    public static AutoGG instance;
    private ServerManager servers;
    
    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event){
    
    }
    
    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        if (event.getSide().isClient()){
            registerServers(new Hypixel(), new Mineplex()/*, new LocalHost()*/);
            MinecraftForge.EVENT_BUS.register(new AutoGGHandler());
        }
    }
    
    @Mod.EventHandler
    public void postinit(FMLServerStartingEvent event){
        if (event.getSide().isServer()){
            event.registerServerCommand(new TestCommand());
        }
    }
    
    
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event){
    
    }
    
    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event){
    }
    
    public ServerManager getServerManager( ){
        return servers;
    }
    
    public void registerServers(Server... servers){
        this.servers = new ServerManager(servers);
    }
    
}
