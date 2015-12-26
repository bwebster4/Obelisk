package com.obelisk.world.mapelems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MapElem {

	public int x, y;
	TextureRegion texture;
	
	public static int tile = 0, block = 1, shader = 2;
	
	private MapElemType type;
	public Sprite sprite;
	
	int life;
	int brightness, highestlight = 1;
	
	public MapElem(MapElemType type, int x, int y, TextureRegion texture){
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.type = type;
		life = type.getHealth();
		sprite = new Sprite(texture);
		sprite.setSize(1.1f, 1.1f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(x, y);
	}
	public void render(SpriteBatch batch){
		sprite.draw(batch);
	}
	public void render(SpriteBatch batch, int ambientlight){
		if(getSuperType() == shader){
			if (ambientlight > highestlight)
				brightness = ambientlight;
			else
				brightness = highestlight;
	
			highestlight = 0;
			
			sprite.setColor(0, 0, 0, 1 - ((float) brightness / 20f));
		}
		sprite.draw(batch);
}
	
	public void doDamage(int damage){
		if(getSuperType() == block)
			life -= damage;
		else
			Gdx.app.log("Wrong MapElem Type", "A " + type.getName() + " was attempted to be damaged");
	}
	public void addLight(int light){
		if (light > highestlight)
			highestlight = light;
	}
	
	
	public int getSuperType(){
		return type.getSuperType();
	}
	public MapElemType getType(){
		return type;
	}
	public int getLife(){
		return life;
	}

}
