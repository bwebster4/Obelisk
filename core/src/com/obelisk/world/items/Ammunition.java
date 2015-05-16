package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;

public abstract class Ammunition extends Item {
 
	int capacity = 20, ammo = 0;
	boolean canFire = false, canStack = true, isAmmo = true, canBuild = false;
	int id = 250;
	
	public Ammunition(TextureRegion texture, boolean inWorld, float x, float y, int ammo) {
		super(texture, inWorld, x, y);
		
		this.ammo = ammo;
	}

	@Override
	public void update() {
		if (ammo == 0 && !isDisposable){

			isDisposable = true;
			inWorld = false;
		}
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}

	@Override
	public float getRange() {
		return 2f;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Item copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canStack() {
		return canStack;
	}

	@Override
	public boolean isAmmo() {
		// TODO Auto-generated method stub
		return isAmmo;
	}

	public void setAmmo(int ammo){
		this.ammo = ammo;
	}
	public boolean canBuild() {
		return canBuild;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

}
