package com.obelisk.world.items;

public enum ItemType {

	Pickaxe("Pickaxe", 1f, "1d4", ItemManager.tool, ItemManager.mining),
	Sword("Sword", 1f, "1d6", ItemManager.weapon, ItemManager.melee),
	Warhammer("Warhammer", 1f, "1d10", ItemManager.weapon, ItemManager.melee),
	StonePiece("Stone", 1f, "1d3", ItemManager.object);
	
	private float range;
	private String damage, name;
	public int superType, subType, equipSlot;
	
	ItemType(String name, float range, String damage, int superType){
		this.name = name;
		this.range = range;
		this.damage = damage;
		this.superType = superType;
		switch(superType){
		case ItemManager.weapon: case ItemManager.tool: case ItemManager.object:
			equipSlot = 0;
			break;
		case ItemManager.armor:
			equipSlot = 2;
			break;
		case ItemManager.container:
			equipSlot = -1;
		}
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
