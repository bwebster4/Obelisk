package com.obelisk.world.tiles;

import com.badlogic.gdx.graphics.Texture;

public class GrassTile extends Tile{

	public GrassTile(float x, float y, Texture texture) {
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
