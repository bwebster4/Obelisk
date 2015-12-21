package com.obelisk.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
			
	
	TextureAtlas itemtextures;
	TextureRegion 	warhammerTexture,
					pickaxetexture, 
					swordTexture,
					stonetexture;
	
	public ItemManager(){
		itemtextures = new TextureAtlas("res/Items.txt");
		pickaxetexture = new TextureRegion(itemtextures.findRegion("Pickaxe"));
		stonetexture = new TextureRegion(itemtextures.findRegion("StonePieces"));
		swordTexture = new TextureRegion(itemtextures.findRegion("Sword"));
		warhammerTexture = new TextureRegion(itemtextures.findRegion("Warhammer"));
	}
	public TextureRegion getTexture(String string){
		if (string == ItemType.Pickaxe.getName())
			return pickaxetexture;
		else if (string == ItemType.Sword.getName())
			return swordTexture;
		else if (string == ItemType.StonePiece.getName())
			return stonetexture;
		else if (string == ItemType.Warhammer.getName())
			return warhammerTexture;
		else return null;
	}
	
	public Item getNewItem(ItemType type, boolean inWorld, float x, float y){
		Item item = new Item(type, getTexture(type.getName()), inWorld, x, y);
		worlditems.add(item);
		return item;
	}
	public void removeItem(Item item){
		worlditems.removeValue(item, true);
	}
	public void render(SpriteBatch batch){
		for (int i = 0; i < worlditems.size; i++){
			item = worlditems.get(i);
			item.render(batch);
			if (item.isDisposable())
				worlditems.removeValue(item, true);
		}
	}
}
