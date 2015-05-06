package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GrassTile extends Tile{

	public GrassTile(float x, float y, TextureRegion texture) {
		super(x, y, texture);
	}

	@Override
	public int getType() {
		return 1;
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
