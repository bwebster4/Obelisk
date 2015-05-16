package com.obelisk.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ItemManager {
	
	public Array<Item> worlditems = new Array<Item>();

	Item item;
	
	// Item Types
	public static final int object = 0, weapon = 1, tool = 2, armor = 3, container  = 4; //Supertypes
	// SubTypes
	public static final int melee = 0, range = 1, // weapon
			mining = 0, farming = 1, woodcutting = 2; // tool
			
	
	Texture itemtextures;
	TextureRegion 
					pickaxetexture, 

					stonetexture;
	
	public ItemManager(){
		itemtextures = new Texture("res/Items.png");
		pickaxetexture = new TextureRegion(itemtextures, 0, 0, 64, 64);
		stonetexture = new TextureRegion(itemtextures, 64, 64, 64, 64);
	}
	public TextureRegion getTexture(String string){
		if (string == "sword")
			return pickaxetexture;
		else if (string == "pickaxe")
			return pickaxetexture;
		else if (string == "stone")
			return stonetexture;
		else return null;
	}
	
	public void addItem(Item item){
		worlditems.add(item);
	}
	public void removeItem(Item item){
		worlditems.removeValue(item, true);
	}
	public void render(SpriteBatch batch){
		for (int i = 0; i < worlditems.size; i++){
			item = worlditems.get(i);
			item.render(batch, false);
			if (item.isDisposable())
				worlditems.removeValue(item, true);
		}
	}
}
