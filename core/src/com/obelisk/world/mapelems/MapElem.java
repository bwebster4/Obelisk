package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class MapElem {

	public int x, y;
	TextureRegion texture;
	
	public Sprite sprite;
	
	public MapElem(float x, float y, TextureRegion texture){
		this.x = (int) x;
		this.y = (int) y;
		this.texture = texture;
		
		sprite = new Sprite(texture);
		sprite.setSize(1.1f, 1.1f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(x, y);
	}
	public void render(SpriteBatch batch){
		sprite.draw(batch);
	}

}
