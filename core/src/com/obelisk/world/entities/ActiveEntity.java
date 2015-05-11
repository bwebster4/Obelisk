package com.obelisk.world.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.world.pathfinding.Node;
import com.obelisk.world.physics.Collisions;

public abstract class ActiveEntity {

	public Vector3 pos, vel, itempos, des_vel, next_pos, tar_pos, acc, ahead, ahead2, avoid;
	public float width, height, rotation;
	EntityManager entitymanager;
	
	Sprite body;
	
	public ActiveEntity(float x, float y, EntityManager entitymanager){
		pos = new Vector3(x, y, 0);
		this.entitymanager = entitymanager;
	}
	abstract public void render(SpriteBatch batch);
	abstract void updatesize();
	public abstract float getRotation();
	public abstract Sprite getSprite();
	public abstract String getFaction();	
	
	public Vector3 getPos(){
		return pos.cpy().add((body.getOriginX() / 2) * MathUtils.cosDeg(rotation + 90), (body.getOriginY() / 2) * MathUtils.sinDeg(rotation + 90), 0);
	}
	public Array<Node> findPath(Vector3 start_pos, Vector3 end_pos){
		Array<Node> path = entitymanager.findPath(start_pos, end_pos);
		return path;
	}
}
