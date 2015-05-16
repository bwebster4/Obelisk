package com.obelisk.world.items;

public enum ItemType {

	Pickaxe("pickaxe", 1f, "1d4", ItemManager.tool, ItemManager.mining),
	Sword("sword", 1f, "1d6", ItemManager.weapon, ItemManager.melee),
	StonePiece("stone", 1f, "1d3", ItemManager.object);
	
	private float range;
	private String damage, name;
	public int superType, subType;
	
	ItemType(String name, float range, String damage, int superType){
		this.name = name;
		this.range = range;
		this.damage = damage;
		this.superType = superType;
		subType = -1;
	}
	ItemType(String name, float range, String damage, int superType, int subType){
		this.name = name;
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
	public String getName(){
		return name;
	}
}
