package com.obelisk.world.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {
	
	float x, y;
	int area = 0;
	Texture texture;
	Sprite sprite, shader;
	int brightness, highestlight = 1;
	
	public Tile(float x, float y, Texture texture){
		this.x = x;
		this.y = y;
		this.texture = texture;
		
		brightness = 1;
		
		sprite = new Sprite(texture);
		sprite.setSize(1f, 1f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(x, y);
		
		shader = new Sprite(texture);
		shader.setSize(1f, 1f * sprite.getHeight() / sprite.getWidth());
		shader.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		shader.setPosition(x, y);
		shader.setColor(0, 0, 0, .05f);
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public int getArea(){
		return area;
	}
	public void setArea(int area){
		this.area = area;
	}
	public Sprite getSprite(){
		return sprite;
	}
	public abstract boolean isWalkable();
	public abstract int getType();
	public abstract float getMoveCost();
	
	
	public void render(SpriteBatch batch, int ambientlight){
		if (ambientlight > highestlight)
			brightness = ambientlight;
		else
			brightness = highestlight;

		highestlight = 0;
		
		shader.setColor(0, 0, 0, 1 - ((float) brightness / 20f));
		
		sprite.draw(batch);
	}
	public void drawShader(SpriteBatch batch){
		shader.draw(batch);
	}
	public void addLight(int light){
		if (light > highestlight)
			highestlight = light;
	}
	
}
