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
	Character entity;
		
	public Item(TextureRegion texture, boolean inWorld, float x, float y, Character entity){
		this.texture = texture;
		sprite = new Sprite(texture);
		sprite.setSize(.5f, .5f);
		this.inWorld = inWorld;
		this.entity = entity;
		
		if (inWorld){
			this.x = x;
			this.y = y;
		}
	}
	public abstract void update();
	public void render(SpriteBatch batch, boolean isactive){
		if (inWorld){
			sprite.setPosition(x, y);
			sprite.draw(batch);
		}else if (isactive){
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
		entity = null;
		inWorld = true;
		this.x = x;
		this.y = y;
	}
	public void pickedUp(Character entity){
		inWorld = false;
		this.entity = entity;
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
	public abstract int getAmmo();
	public abstract float getRange();
	public abstract int getBlockDamage();
	public abstract int getMiningSpeed();
	public abstract TextureRegion getTexture();
	public int getStackSize(){
			return 1;
	}
	public int getEquipSlot(){
		return -1;
	}
	
	public abstract void flipTexture(boolean x, boolean y);
	public abstract boolean canFire();
	public abstract boolean canBuild();
	public abstract boolean fire(float x, float y);

	public abstract void setAmmo(int ammo);
	public abstract Item copy();
	public abstract boolean canStack();
	public abstract boolean isAmmo();
	
}
