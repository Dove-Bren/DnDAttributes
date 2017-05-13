package com.skyisland.dndattributes.proxy;

import com.skyisland.dndattributes.DnDAttributesMod;
import com.skyisland.dndattributes.capability.AttributeProvider;
import com.skyisland.dndattributes.capability.CapabilityHandler;
import com.skyisland.dndattributes.capability.DnDAttributeStorage;
import com.skyisland.dndattributes.capability.DnDAttributes;
import com.skyisland.dndattributes.capability.IDnDAttributes;
import com.skyisland.dndattributes.network.NetworkHandler;
import com.skyisland.dndattributes.network.message.AttribSyncMessage;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy  {
		
	/**
	 * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
	 */
	public void preInit()
	{
		CapabilityManager.INSTANCE.register(IDnDAttributes.class, new DnDAttributeStorage(), DnDAttributes.class);
	}
	  
	public void init() {
		System.out.println("Network Proxy initializing.");
		new CapabilityHandler();
		//NetworkRegistry.INSTANCE.registerGuiHandler(DnDAttributesMod.instance, new GuiHandler());
	}
	
	/**
	 * Do your mod setup. Build whatever data structures you care about. Register recipes,
	 * send FMLInterModComms messages to other mods.
	 */
	public void load()
	{
		
	}
	
	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit()
	{
		
	}
	
	/**
	 * Attempts to grab an entity's attributes and sync them to the client.
	 * Ideally called only when there's a change. If everything works, causes a full
	 * drop-and-take of attributes. In otherwords, clients attributes are reset to what they
	 * are on the player passed in
	 * @param player
	 */
	public void sendClientSync(EntityPlayerMP player) {
		IDnDAttributes att = player.getCapability(AttributeProvider.CAPABILITY, null);
		
		if (att == null) {
			DnDAttributesMod.logger.warn("Unable to fetch dnd attributes for player: " + player.getName());
			return;
		}
		
		NetworkHandler.getSyncChannel().sendTo(new AttribSyncMessage(
				att.getStrength(),
				att.getConstitution(),
				att.getDexterity(),
				att.getIntelligence(),
				att.getWisdom(),
				att.getCharisma()
				), player);
	}
}
