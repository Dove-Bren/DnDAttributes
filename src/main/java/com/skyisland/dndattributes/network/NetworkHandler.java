package com.skyisland.dndattributes.network;

import com.skyisland.dndattributes.network.message.AttribSyncMessage;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

	private static SimpleNetworkWrapper syncChannel;
	
	private static int discriminator = 10;
	
	private static final String CHANNEL_SYNC_NAME = "dndattr_syncchannel";
	
	
	public static SimpleNetworkWrapper getSyncChannel() {
		getInstance();
		return syncChannel;
	}
	
	private static NetworkHandler instance;
	
	public static NetworkHandler getInstance() {
		if (instance == null)
			instance = new NetworkHandler();
		
		return instance;
	}
	
	public NetworkHandler() {
		
		syncChannel = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL_SYNC_NAME);
		
		syncChannel.registerMessage(AttribSyncMessage.Handler.class, AttribSyncMessage.class, discriminator++, Side.CLIENT);
	}
	
}
