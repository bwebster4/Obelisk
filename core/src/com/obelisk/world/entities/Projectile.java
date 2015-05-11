package com.obelisk.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.obelisk.world.Map;
import com.obelisk.world.WorldMain;
import com.obelisk.world.items.ItemManager;

public abstract class Projectile extends ActiveEntity{

	Map map;
	Vector3 move, tempmove;
	int life = 300;
	boolean alive = true;
	float speed;
	float angle;
	float damage;
	
	String faction;
	
	public Projectile(float x, float y, EntityManager entitymanager, ItemManager itemmanager, float angle, String faction, float damage) {
		super(x, y, entitymanager);
		
		this.damage = damage;
		this.angle = angle;
		this.faction = faction;
	}
	public void show(TextureRegion textureRegion){
		body = new Sprite(textureRegion);
		body.setPosition(pos.x, pos.y);
		body.setSize(.25f, .1f);
		body.setOrigin(height / 2, width / 2);
		body.setRotation(angle);
		move = new Vector3(getSpeed() * MathUtils.cos(MathUtils.degreesToRadians*angle), getSpeed() * MathUtils.sin(MathUtils.degreesToRadians*angle), 0);
		tempmove = new Vector3(0, 0, 0);
	}
	public void render(SpriteBatch batch){
		tempmove.set(move);
		tempmove.scl(Gdx.graphics.getDeltaTime());
		pos.add(tempmove);
		if (pos.x < 0 || pos.y < 0 || pos.x > Map.WORLD_SIZE || pos.y > Map.WORLD_SIZE)
			alive = false;
		life--;
		if (life == 0)
			alive = false;
		if (alive == false)
			WorldMain.entitymanager.removeProjectile(this);
		else{
		body.setPosition(pos.x, pos.y);
		body.draw(batch);
		}
	}
	public boolean getAlive(){
		return alive;
	}
	void updatesize() {
		
	}
	public abstract float getDamage();
	public abstract float getSpeed();
	public abstract Projectile getProjectile();


	public Sprite getSprite() {
		return body;
	}
	@Override
	public String getFaction() {
		return faction;
	}
	
	public void projectileCollision(Projectile projectile){
	}
	
}
