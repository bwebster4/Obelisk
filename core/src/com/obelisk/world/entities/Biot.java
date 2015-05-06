package com.obelisk.world.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.obelisk.InputHandler;
import com.obelisk.world.items.ItemManager;
import com.obelisk.world.physics.Collisions;

public class Biot extends Character{
	
	InputHandler input;
	public static float MAX_SPEED = 2f, MAX_FORCE = 100f, MASS = 50f;
	float turnspeed = 3f;
	int health = 100;
	boolean colliding = false;
	int pistol_experience_lvl = 1;
	
	float range;
	int miningcounter;
	
	String faction = "BRI";

	public Biot(float x, float y, ItemManager itemmanager, EntityManager entitymanager, Collisions collisions) {
		super(x, y, itemmanager, entitymanager, collisions);

	}

	public void show(TextureRegion t){
		vel = new Vector3();
		des_vel = new Vector3();
		itempos = new Vector3(pos);
		ahead = new Vector3();
		ahead2 = new Vector3();
		avoid = new Vector3();
		range = 4f;
//
//		tf = new TextureRegion(t, 0, 0, 32, 64);
//		tb = new TextureRegion(t, 0, 64, 32, 64);
//		tr = new TextureRegion(t, 0, 128, 32, 64);
//		tl = new TextureRegion(t, 0, 192, 32, 64);

		target = entitymanager.player;
		superstate = hunting;
		
		body = new Sprite(t_still);
		body.setSize(.45f, .45f * body.getHeight() / body.getWidth());
		body.setOrigin(body.getX() + body.getWidth() / 2, body.getY() + body.getHeight() / 2);
	}
	
	@Override
	void updatesize() {
		width = body.getWidth();
		height = body.getHeight();
	}
	public void render(SpriteBatch batch){
		if (health <= 0){
			destroy();
			entitymanager.loadedentities.removeValue(this, false);
		}

		if (equipped.get(0) != null)
			equipped.get(0).update();
		updatesize();
		
		switch(superstate){
		case idle:
			break;
		case moving:
			break;
		case hunting:
			hunt();
			break;
		}

		updatesprite();
		body.draw(batch);
		
		/*if (rotation >= 0 && rotation <= 45){
			body.setRegion(t_armed);
			head.setRegion(thead_right);
			itempos.x += .5f;
			if (activeitem != null)
				activeitem.flipTexture(true, false);
		}else if (rotation > 45 && rotation <= 135){
			body.setRegion(t_default);
			head.setRegion(thead_back);
			itempos.x += .2f;
			if (activeitem != null)
				activeitem.flipTexture(false, false);
		}else if (rotation > 135 && rotation <= 225){
			body.setRegion(tchest_left);
			head.setRegion(thead_left);
			//itempos.x += .5f;
			if (activeitem != null)
				activeitem.flipTexture(false, false);
		}else if (rotation > 225 && rotation <= 315){
			body.setRegion(t_default);
			head.setRegion(thead_front);
			itempos.x += .2f;
			if (activeitem != null)
				activeitem.flipTexture(true, false);
		}else if (rotation > 315){
			body.setRegion(t_armed);
			head.setRegion(thead_right);
			itempos.x += .5f;
			if (activeitem != null)
				activeitem.flipTexture(true, false);
		}*/
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public Sprite getSprite() {
		return body;
	}

	@Override
	public String getFaction() {
		return faction;
	}

}
