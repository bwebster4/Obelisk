package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.obelisk.world.entities.Character;

public class Item {
	
	private boolean inWorld, isVisible;
	boolean isDisposable = false;
	public Sprite sprite;
	float x, y, rotation;
	ItemType type;
	TextureRegion texture;
	
	TextButton button;

	private float range;
		
	public Item(ItemType type, TextureRegion texture, boolean inWorld, float x, float y){

		this.inWorld = inWorld;
		isVisible = inWorld;
		
		this.type = type;
		this.texture = texture;
		sprite = new Sprite(texture);
		sprite.setSize(.5f, .5f * sprite.getHeight() / sprite.getWidth());
		sprite.setOriginCenter();
		
		if (inWorld){
			this.x = x;
			this.y = y;
		}
	}

	public void renderFromCharacter(SpriteBatch batch){
		if(isVisible){
			sprite.setPosition(x, y);
			sprite.setRotation(rotation - 90);
			sprite.draw(batch);
		}
	}
	public void render(SpriteBatch batch){
		if (inWorld){
			sprite.setPosition(x, y);
			sprite.draw(batch);
		}
	}
	
	public void setPos(Vector3 pos, float rotation){
		x = pos.x;
		y = pos.y;
		this.rotation = rotation;
	}
	public void dropped(float x, float y){
		sprite.setSize(.5f, .5f);
		inWorld = true;
		this.x = x;
		this.y = y;
	}
	public void pickedUp(){
		inWorld = false;
	}
	
	public boolean isDisposable(){
		return isDisposable;
	}
	public boolean isVisible(){
		return isVisible;
	}
	public void setVisible(boolean visible){
		isVisible = visible;
	}
	public void setButton(TextButton button){
		this.button = button;
	}
	public TextButton getButton(){
		return button;
	}
	public String getName(){
		return type.getName();
	}
	public float getRange(){
		return type.getRange();
	}
	public String getDamage(){
		return type.getDamage();
	}
	public int getEquipSlot(){
		return type.equipSlot;
	}

	public Item copy(){
		return new Item(type, texture, inWorld, x, y);
	}

	
}
