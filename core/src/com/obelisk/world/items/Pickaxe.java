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
	public int getBlockDamage() {
		return blockdamage;
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}

	@Override
	public boolean canFire() {
		return canFire;
	}

	@Override
	public boolean fire(float x, float y) {
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMiningSpeed() {
		return miningspeed;
	}

	@Override
	public void flipTexture(boolean x, boolean y) {
		if (sprite.isFlipX() != x)
			sprite.flip(x, y);
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
	public int getAmmo() {
		// TODO Auto-generated method stub
		return 0;
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
	public int getStackSize() {
		return 1;
	}
	
	@Override
	public int getEquipSlot(){
		return equipSlot;
	}
}
