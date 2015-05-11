package com.obelisk.world.entities;

import com.badlogic.gdx.Gdx;
import com.obelisk.world.items.ItemManager;

public class LightKinetic extends Projectile {

	float speed = 30f;
	
	public LightKinetic(float x, float y, EntityManager entitymanager, ItemManager itemmanager, float angle, String faction, float damage) {
		super(x, y, entitymanager, itemmanager, angle, faction, damage);
		
		show(itemmanager.getTexture("lightkinetic"));
	}

	@Override
	public float getDamage() {
		return damage;
	}

	@Override
	public float getRotation() {
		return angle;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public Projectile getProjectile() {
		return this;
	}
}
