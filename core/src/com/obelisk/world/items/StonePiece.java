package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;

public class StonePiece extends Item {

	boolean canFire = false;
	boolean canStack = true, isAmmo = false, canBuild = true;
	int id = 1, stackSize = 1;
	String name = "Stone";
	
	public StonePiece(TextureRegion texture, boolean inWorld, float x, float y, Character entity, int stackSize) {
		super(texture, inWorld, x, y, entity);
		this.stackSize = stackSize;
	}

	@Override
	public void update() {
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}

	@Override
	public void flipTexture(boolean x, boolean y) {
	}

	@Override
	public float getRange() {
		return 2f;
	}

	@Override
	public int getBlockDamage() {
		return 0;
	}

	@Override
	public boolean canFire() {
		return canFire;
	}

	@Override
	public int getMiningSpeed() {
		return 0;
	}

	@Override
	public boolean fire(float x, float y) {
		return false;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Item copy() {
		return new StonePiece(texture, true, x, y, entity, stackSize);
	}

	public boolean canStack() {
		return canStack;
	}
	
	@Override
	public int getStackSize(){
		return stackSize;
	}
	
	public boolean isAmmo() {
		return isAmmo;
	}

	@Override
	public int getAmmo() {
		return 0;
	}

	@Override
	public void setAmmo(int ammo) {
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
		
	}
}
