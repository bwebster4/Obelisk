package com.obelisk.world.items;

public enum ItemType {

	Pickaxe("Pickaxe", 1f, "1d4", ItemManager.tool, ItemManager.mining, .45f),
	Sword("Sword", 1f, "1d6", ItemManager.weapon, ItemManager.melee, .25f),
	Warhammer("Warhammer", 1f, "1d10", ItemManager.weapon, ItemManager.melee, .5f),
	StonePiece("Stone", "1d3", ItemManager.object, .5f),
	Wood("Wood", "1d2", ItemManager.object, .5f);
	
	private float range, size;
	private String damage, name;
	public int superType, subType, equipSlot;
	
	ItemType(String name,String damage, int superType, float size){
		this.name = name;
		this.range = 0;
		this.damage = damage;
		this.superType = superType;
		this.size = size;
		switch(superType){
		case ItemManager.object:
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
	ItemType(String name, float range, String damage, int superType, int subType, float size){
		this.name = name;
		this.range = range;
		this.damage = damage;
		this.superType = superType;
		this.subType = subType;
		this.size = size;
		equipSlot = 0;
		
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
	public float getSize(){
		return size;
	}
}
