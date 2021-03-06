package com.skyisland.dndattributes.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AttributeProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IDnDAttributes.class)
	public static Capability<IDnDAttributes> CAPABILITY = null;
	
	private IDnDAttributes instance = CAPABILITY.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CAPABILITY)
			return CAPABILITY.<T> cast(this.instance);
		
		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return CAPABILITY.getStorage().writeNBT(CAPABILITY, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		CAPABILITY.getStorage().readNBT(CAPABILITY, instance, null, nbt);
	}

}
