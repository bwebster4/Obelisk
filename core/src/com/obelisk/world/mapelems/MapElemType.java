package com.obelisk.world.mapelems;

import com.obelisk.world.items.ItemType;

public enum MapElemType {

	Grass("Grass", true),
	RockyGrass("RockyGrass", true),
	Stone("Stone", 100, ItemType.StonePiece),
	Tree("Tree", 25, ItemType.Wood),
	Shader();
	
	private int superType;
	
	private String name;
	private boolean isWalkable;
	private int health;
	private ItemType dropItem;
	
	MapElemType(String name, boolean isWalkable){
		this.name = name;
		this.health = 1000;
		this.isWalkable = isWalkable;
		
		this.superType = MapElem.tile;
	}
	
	MapElemType(String name, int health, ItemType dropItem){
		this.name = name;
		isWalkable = false;
		this.health = health;
		this.dropItem = dropItem;
		this.superType = MapElem.block;
	}
	
	MapElemType(){
		this.name = "Shader";
		isWalkable = true;
		this.health = 1000;
		this.superType = MapElem.shader;
	}
	
	public String getName(){
		return name;
	}
	public int getSuperType(){
		return superType;
	}
	public boolean isWalkable(){
		return isWalkable;
	}
	public int getHealth(){
		return health;
	}
	public ItemType getDropItem(){
		return dropItem;
	}
}
