package com.obelisk.world.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.obelisk.world.entities.Character;

public abstract class Item {
	
	public boolean inWorld;
	boolean canFire, isDisposable = false, canStack;
	int id, stackSize, equipSlot;
	public Sprite sprite;
	float x, y;
	TextureRegion texture;
	
	TextButton button;
	
	int blockdamage;
	public float range;
		
	public Item(TextureRegion texture, boolean inWorld, float x, float y){
		this.texture = texture;
		sprite = new Sprite(texture);
		sprite.setSize(.5f, .5f);
		this.inWorld = inWorld;
		
		if (inWorld){
			this.x = x;
			this.y = y;
		}
	}
	public abstract void update();
	public void render(SpriteBatch batch, boolean isActive){
		if (inWorld){
			sprite.setPosition(x, y);
			sprite.draw(batch);
		}else if (isActive){
			sprite.setPosition(x, y);
			sprite.draw(batch);
		}
	}
	public void setPos(Vector3 pos){
		x = pos.x;
		y = pos.y;
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
	
	public void setButton(TextButton button){
		this.button = button;
	}
	public TextButton getButton(){
		return button;
	}
	
	public abstract void use();
	
	public abstract int getId();
	public abstract String getName();
	public abstract float getRange();
	public abstract TextureRegion getTexture();

	public int getEquipSlot(){
		return -1;
	}

	public abstract void setAmmo(int ammo);
	public abstract Item copy();
	public abstract boolean canStack();
	public abstract boolean isAmmo();
	
}
