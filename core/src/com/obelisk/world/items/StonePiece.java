
package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;

public class StonePiece extends Item {

	boolean canFire = false;
	boolean canStack = true, isAmmo = false, canBuild = true;
	int id = 1, stackSize = 1;
	String name = "Stone";
	
	public StonePiece(TextureRegion texture, boolean inWorld, float x, float y, int stackSize) {
		super(texture, inWorld, x, y);
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
	public int getId() {
		return id;
	}

	@Override
	public Item copy() {
		return new StonePiece(texture, true, x, y, stackSize);
	}

	public boolean canStack() {
		return canStack;
	}
	
	
	public boolean isAmmo() {
		return isAmmo;
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

	@Override
	public float getRange() {
		// TODO Auto-generated method stub
		return 0;
	}
}
