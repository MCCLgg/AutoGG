package com.podcrash.mod.autogg;

import com.podcrash.mod.autogg.handles.AutoGGHandler;
import com.podcrash.mod.autogg.server.Server;
import com.podcrash.mod.autogg.server.ServerManager;
import com.podcrash.mod.autogg.server.servers.Hypixel;
import com.podcrash.mod.autogg.server.servers.Mineplex;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("autogg")
public class AutoGG {
    private static final Logger LOGGER = LogManager.getLogger();
    /** This is the instance of your mod as created by Forge. It will never be null. */
    
    public static AutoGG instance;
    private ServerManager servers;
    
    public AutoGG( ){
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        instance = this;
    }
    
    private void setup(final FMLCommonSetupEvent event){
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    
    private void doClientStuff(final FMLClientSetupEvent event){
        // do something that can only be done on the client
        AutoGG.instance.registerServers(new Hypixel(), new Mineplex()/*, new LocalHost()*/);
        MinecraftForge.EVENT_BUS.register(new AutoGGHandler());
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }
    
    
    public ServerManager getServerManager( ){
        return servers;
    }
    
    public void registerServers(Server... servers){
        this.servers = new ServerManager(servers);
    }
    
}
