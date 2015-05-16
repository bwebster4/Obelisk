package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;

public class Pickaxe extends Item {

	boolean canFire = false, canStack = false, isAmmo = true, canBuild = false;
	
	String name = "Pickaxe";
	
	int blockdamage = 25;
	public float range = 2f;
	int miningspeed = 5;
	
	int id = 100, equipSlot = 0;
	
	public Pickaxe(TextureRegion texture, boolean inWorld, float x, float y) {
		super(texture, inWorld, x, y);
	}

	@Override
	public float getRange() {
		return range;
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public int getId() {
		return id;
	}

	@Override
	public Item copy() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean canStack() {
		return canStack;
	}
	public boolean isAmmo() {
		// TODO Auto-generated method stub
		return isAmmo;
	}

	@Override
	public void setAmmo(int ammo) {
		// TODO Auto-generated method stub
		
	}
	public boolean canBuild() {
		return canBuild;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getEquipSlot(){
		return equipSlot;
	}
}
