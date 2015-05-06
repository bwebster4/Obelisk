<<<<<<< HEAD
package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;

public abstract class Ammunition extends Item {
 
	int capacity = 20, ammo = 0;
	boolean canFire = false, canStack = true, isAmmo = true, canBuild = false;
	int id = 250;
	
	public Ammunition(TextureRegion texture, boolean inWorld, float x, float y, Character entity, int ammo) {
		super(texture, inWorld, x, y, entity);
		
		this.ammo = ammo;
	}

	@Override
	public void update() {
		if (ammo == 0 && !isDisposable){
			entity.removeItem(this);
			isDisposable = true;
			inWorld = false;
		}
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

	@Override
	public int getAmmo() {
		return ammo;
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
=======
package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;

public abstract class Ammunition extends Item {
 
	int capacity = 20, ammo = 0;
	boolean canFire = false, canStack = true, isAmmo = true, canBuild = false;
	int id = 250;
	
	public Ammunition(TextureRegion texture, boolean inWorld, float x, float y, Character entity, int ammo) {
		super(texture, inWorld, x, y, entity);
		
		this.ammo = ammo;
	}

	@Override
	public void update() {
		if (ammo == 0 && !isDisposable){
			entity.removeItem(this);
			isDisposable = true;
			inWorld = false;
		}
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

	@Override
	public int getAmmo() {
		return ammo;
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
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
