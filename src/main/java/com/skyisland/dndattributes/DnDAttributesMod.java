package com.skyisland.dndattributes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.skyisland.dndattributes.config.ModConfig;
import com.skyisland.dndattributes.listener.PlayerListener;
import com.skyisland.dndattributes.network.NetworkHandler;
import com.skyisland.dndattributes.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DnDAttributesMod.MODID, version = DnDAttributesMod.VERSION)
public class DnDAttributesMod
{
	
	@Instance(value = DnDAttributesMod.MODID) //Tell Forge what instance to use.
    public static DnDAttributesMod instance;
	
    @SidedProxy(clientSide="com.skyisland.dndattributes.proxy.ClientProxy", serverSide="com.skyisland.dndattributes.proxy.CommonProxy")
    public static CommonProxy proxy;
	
    public static final String MODID = "dndattributes";
    public static final String VERSION = "1.0";
    public static Logger logger = LogManager.getLogger(MODID);
    
    public static PlayerListener playerListener;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	instance = this;
    	new ModConfig(new Configuration(event.getSuggestedConfigurationFile()));
    	playerListener = new PlayerListener();
    	NetworkHandler.getInstance();
    	proxy.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        proxy.init();
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {
    	proxy.postInit();
    }
}
