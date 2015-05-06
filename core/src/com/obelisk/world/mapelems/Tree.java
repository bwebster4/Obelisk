package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Tree extends MapElem {

	Vector3 pos;
	int invDis = 15;
	
	public Tree(float x, float y, TextureRegion texture) {
		super(x, y, texture);
		
		pos = new Vector3(x, y, 0);
		sprite.setSize(3f, 3f);
	}
	
	public void render(SpriteBatch batch, Vector3 playerPos){
		float distance = pos.dst(playerPos);
		if (distance < invDis){
			sprite.setAlpha(.5f);
		}else{
			sprite.setAlpha(1f);
		}
		super.render(batch);
	}

}
