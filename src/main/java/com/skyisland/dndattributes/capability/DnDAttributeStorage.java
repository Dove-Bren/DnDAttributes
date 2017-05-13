package com.skyisland.dndattributes.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class DnDAttributeStorage implements IStorage<IDnDAttributes> {

	private static final String NBT_STRENGTH = "strength";
	private static final String NBT_CONSTITUTION = "constitution";
	private static final String NBT_DEXTERITY = "dexterity";
	private static final String NBT_INTELLIGENCE = "intelligence";
	private static final String NBT_WISDOM = "wisdom";
	private static final String NBT_CHARISMA = "charisma";
	
	@Override
	public NBTBase writeNBT(Capability<IDnDAttributes> capability, IDnDAttributes instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		
		nbt.setInteger(NBT_STRENGTH, instance.getStrength());
		nbt.setInteger(NBT_CONSTITUTION, instance.getConstitution());
		nbt.setInteger(NBT_DEXTERITY, instance.getDexterity());
		nbt.setInteger(NBT_INTELLIGENCE, instance.getIntelligence());
		nbt.setInteger(NBT_WISDOM, instance.getWisdom());
		nbt.setInteger(NBT_CHARISMA, instance.getCharisma());
		
		return nbt;
	}

	@Override
	public void readNBT(Capability<IDnDAttributes> capability, IDnDAttributes instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound tag = (NBTTagCompound) nbt;
		instance.setStrength(tag.getInteger(NBT_STRENGTH));
		instance.setConstitution(tag.getInteger(NBT_CONSTITUTION));
		instance.setDexterity(tag.getInteger(NBT_DEXTERITY));
		instance.setIntelligence(tag.getInteger(NBT_INTELLIGENCE));
		instance.setWisdom(tag.getInteger(NBT_WISDOM));
		instance.setCharisma(tag.getInteger(NBT_CHARISMA));
	}

}
