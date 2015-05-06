package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Shader extends MapElem{

	int brightness, highestlight = 1;
	
	public Shader(float x, float y, TextureRegion texture) {
		super(x, y, texture);
		
		sprite.setColor(0, 0, 0, .05f);

		brightness = 1;
		
	}
	
	public void render(SpriteBatch batch, int ambientlight){
		if (ambientlight > highestlight)
			brightness = ambientlight;
		else
			brightness = highestlight;

		highestlight = 0;
		
		sprite.setColor(0, 0, 0, 1 - ((float) brightness / 20f));
		super.render(batch);
	}
	public void addLight(int light){
		if (light > highestlight)
			highestlight = light;
	}

}
