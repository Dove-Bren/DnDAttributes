package com.skyisland.dndattributes.proxy;

import com.skyisland.dndattributes.client.gui.OverlayHandler;

import net.minecraft.entity.player.EntityPlayerMP;

public class ClientProxy extends CommonProxy {
	
	/**
	   * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
	   */
	@Override
	public void preInit() {
		super.preInit();
	}
	
	@Override
	public void init() {
		super.init();
	}

	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes,
	 * send FMLInterModComms messages to other mods.
	 */
	public void load() {
		// register my Recipies
	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit() {
		new OverlayHandler();
	}
	
	@Override
	public void sendClientSync(EntityPlayerMP player) {
		; //do nothing. Client's dont send this packet
	}
}
