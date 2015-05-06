<<<<<<< HEAD
package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;

public class KineticSMG extends Item {
	public KineticSMG(TextureRegion texture, boolean inWorld, float x, float y, ItemManager itemmanager,
			Character entity) {
		super(texture, inWorld, x, y, entity);
		
		this.itemmanager = itemmanager;
	}
	float range = 10f;
	int blockdamage = 0;
	ItemManager itemmanager;
	boolean canFire = true, canStack = false, isAmmo = true, canBuild = false;
	int rof = 10;
	int rofcount = 0;
	float inaccuracy = 25f;
	float damage = 20;
	
	int id = 201;
	
	public void update(){
		if (rofcount > 0)
			rofcount--;
	}

	@Override
	public TextureRegion getTexture() {
		return super.texture;
	}

	public boolean fire(float x, float y){
//		if (rofcount == 0){
//			//crea
//				rofcount = rof;	
//				return true;
//		}else 
		return false;
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
	public boolean canFire() {
		return canFire;
	}
	@Override
	public int getMiningSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void flipTexture(boolean x, boolean y) {
		if (sprite.isFlipX() == x)
			sprite.flip(x, y);
	}
	@Override
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
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return null;
	}
}
=======
package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;

public class KineticSMG extends Item {
	public KineticSMG(TextureRegion texture, boolean inWorld, float x, float y, ItemManager itemmanager,
			Character entity) {
		super(texture, inWorld, x, y, entity);
		
		this.itemmanager = itemmanager;
	}
	float range = 10f;
	int blockdamage = 0;
	ItemManager itemmanager;
	boolean canFire = true, canStack = false, isAmmo = true, canBuild = false;
	int rof = 10;
	int rofcount = 0;
	float inaccuracy = 25f;
	float damage = 20;
	
	int id = 201;
	
	public void update(){
		if (rofcount > 0)
			rofcount--;
	}

	@Override
	public TextureRegion getTexture() {
		return super.texture;
	}

	public boolean fire(float x, float y){
//		if (rofcount == 0){
//			//crea
//				rofcount = rof;	
//				return true;
//		}else 
		return false;
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
	public boolean canFire() {
		return canFire;
	}
	@Override
	public int getMiningSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void flipTexture(boolean x, boolean y) {
		if (sprite.isFlipX() == x)
			sprite.flip(x, y);
	}
	@Override
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
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return null;
	}
}
>>>>>>> ea8d177d0598fce92b5a9bc7afe990a287bf430a
