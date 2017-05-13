package com.skyisland.dndattributes.capability;

/**
 * Default implementation of the attributes interface
 * @author Skyler
 *
 */
public class DnDAttributes implements IDnDAttributes {
	
	private int strength;
	private int constitution;
	private int dexterity;
	private int intelligence;
	private int wisdom;
	private int charisma;
	
	@Override
	public int getStrength() {
		return strength;
	}
	
	@Override
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	@Override
	public int getConstitution() {
		return constitution;
	}
	
	@Override
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}
	
	@Override
	public int getDexterity() {
		return dexterity;
	}
	
	@Override
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	
	@Override
	public int getIntelligence() {
		return intelligence;
	}
	
	@Override
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	
	@Override
	public int getWisdom() {
		return wisdom;
	}
	
	@Override
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}
	
	@Override
	public int getCharisma() {
		return charisma;
	}
	
	@Override
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	
	
}
