package com.skyisland.dndattributes.network.message;

import com.skyisland.dndattributes.DnDAttributesMod;
import com.skyisland.dndattributes.capability.AttributeProvider;
import com.skyisland.dndattributes.capability.IDnDAttributes;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Client player's attribtes are being refreshed from server
 * @author Skyler
 *
 */
public class AttribSyncMessage implements IMessage {

	public static class Handler implements IMessageHandler<AttribSyncMessage, IMessage> {

		@Override
		public IMessage onMessage(AttribSyncMessage message, MessageContext ctx) {
			//update local attributes
			
			DnDAttributesMod.logger.info("Debug: Recieved sync message from server");
			EntityPlayer sp = Minecraft.getMinecraft().thePlayer;
			IDnDAttributes att = sp.getCapability(AttributeProvider.CAPABILITY, null);
			
			if (att == null) {
				DnDAttributesMod.logger.warn("Server is pushing into DnDAttributes, but they don't exist on our player!");
				return null;
			}
			
			att.setStrength(message.tag.getInteger(NBT_STRENGTH));
			att.setConstitution(message.tag.getInteger(NBT_CONSTITUTION));
			att.setDexterity(message.tag.getInteger(NBT_DEXTERITY));
			att.setIntelligence(message.tag.getInteger(NBT_INTELLIGENCE));
			att.setWisdom(message.tag.getInteger(NBT_WISDOM));
			att.setCharisma(message.tag.getInteger(NBT_CHARISMA));

			return null;
		}
		
	}
	
	private static final String NBT_STRENGTH = "strength";
	private static final String NBT_CONSTITUTION = "constitution";
	private static final String NBT_DEXTERITY = "dexterity";
	private static final String NBT_INTELLIGENCE = "intelligence";
	private static final String NBT_WISDOM = "wisdom";
	private static final String NBT_CHARISMA = "charisma";
	
	protected NBTTagCompound tag;
	
	
	public AttribSyncMessage() {
		tag = new NBTTagCompound();
	}
	
	public AttribSyncMessage(int strength,
							 int constitution,
							 int dexterity,
							 int intelligence,
							 int wisdom,
							 int charisma) {
		this();
		tag.setInteger(NBT_STRENGTH, strength);
		tag.setInteger(NBT_CONSTITUTION, constitution);
		tag.setInteger(NBT_DEXTERITY, dexterity);
		tag.setInteger(NBT_INTELLIGENCE, intelligence);
		tag.setInteger(NBT_WISDOM, wisdom);
		tag.setInteger(NBT_CHARISMA, charisma);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		tag = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, tag);
	}

}
