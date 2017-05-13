package com.skyisland.dndattributes.listener;


import net.minecraftforge.common.MinecraftForge;

public class PlayerListener {

	public PlayerListener() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
//	@SubscribeEvent
//	public void onPlayerLogin(PlayerLoggedInEvent event) {
//		if (event.player instanceof EntityPlayerMP) {
//			
//			//check admin list
//			if (!D20Mod.adminRegistry.isAdmin(event.player))
//				return;
//			
//			D20Mod.logger.info("Sending admin-token to " + event.player.getName());
//			D20Mod.proxy.sendAdminToken((EntityPlayerMP) event.player);
//		}
//	}
	
}
