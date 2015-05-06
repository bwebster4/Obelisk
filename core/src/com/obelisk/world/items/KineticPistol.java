package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.world.entities.Character;


public class KineticPistol extends Item{

	float range = 10f;
	int blockdamage = 0;
	ItemManager itemmanager;
	boolean canFire = true, canStack = false, isAmmo = true, canBuild = false;
	int rof = 45;
	int rofcount = 0;
	float inaccuracy = 20f;
	float damage = 25;
	
	int id = 200;
	
	public KineticPistol(TextureRegion texture, boolean inWorld, float x, float y, ItemManager itemmanager, Character entity) {
		super(texture, inWorld, x, y, entity);
		
		this.itemmanager = itemmanager;
	}
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
//				WorldMain.entitymanager.addProjectile(new LightKinetic(x, y, itemmanager, entity.getRotation() + (MathUtils.random(-inaccuracy, inaccuracy) + entity.getExperienceLvl("pistol")) / entity.getExperienceLvl("pistol"), entity.getFaction(), damage));
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
		// TODO Auto-generated method stub
		return null;
	}
}
