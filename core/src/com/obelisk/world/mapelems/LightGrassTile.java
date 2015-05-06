package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LightGrassTile extends Tile{

	public LightGrassTile(float x, float y, TextureRegion texture) {
		super(x, y, texture);
	}

	@Override
	public int getType() {
		return 2;
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public float getMoveCost() {
		return 1;
	}

}
