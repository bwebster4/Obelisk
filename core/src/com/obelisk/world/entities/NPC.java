package com.obelisk.world.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.obelisk.InputHandler;
import com.obelisk.world.items.ItemManager;

public class NPC extends Character{
	
	InputHandler input;
	
	float range;
	int miningcounter;
	
	String faction = "Obelisk";

	public NPC(float x, float y, ItemManager itemmanager, EntityManager entitymanager) {
		super(x, y, itemmanager, entitymanager);

	}

	public void show(TextureRegion t, int profession){
		super.show(profession);
//
//		tf = new TextureRegion(t, 0, 0, 32, 64);
//		tb = new TextureRegion(t, 0, 64, 32, 64);
//		tr = new TextureRegion(t, 0, 128, 32, 64);
//		tl = new TextureRegion(t, 0, 192, 32, 64);

		setupTextures(t);
		
		target = entitymanager.player;
		superstate = hunting;
		
		size = medium;
		body = new Sprite(t_still);
		body.setSize(size, size * body.getHeight() / body.getWidth());
		body.setOrigin(body.getX() + body.getWidth() / 2, body.getY() + body.getHeight() / 2);
	}
	
	@Override
	void updatesize() {
		width = body.getWidth();
		height = body.getHeight();
	}
	public void render(SpriteBatch batch){
		if (currentHealth <= 0){
			destroy();
			entitymanager.loadedentities.removeValue(this, false);
		}

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

		updateSprites();
		renderItems(batch);
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
