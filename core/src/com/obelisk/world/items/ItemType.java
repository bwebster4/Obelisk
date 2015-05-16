package com.obelisk.world.items;

public enum ItemType {

	Pickaxe(1f, "1d3", ItemManager.tool, ItemManager.mining),
	Sword(1f, "1d6", ItemManager.weapon, ItemManager.melee);
	
	private float range;
	private String damage;
	private int superType, subType;
	
	ItemType(float range, String damage, int superType){
		this.range = range;
		this.damage = damage;
		this.superType = superType;
	}
	ItemType(float range, String damage, int superType, int subType){
		this.range = range;
		this.damage = damage;
		this.superType = superType;
		this.subType = subType;
	}
	
	public float getRange(){
		return range;
	}
	public String getDamage(){
		return damage;
	}
}
