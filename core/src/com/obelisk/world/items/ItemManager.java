package com.obelisk.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ItemManager {
	
	public Array<Item> worlditems = new Array<Item>();

	Item item;
	
	Texture itemtextures;
	TextureRegion lightkinetictexture, kineticammo,
					pickaxetexture, 
					kineticpistoltexture, kineticSMG,
					stonetexture;
	
	public ItemManager(){
		itemtextures = new Texture("res/Items.png");
		lightkinetictexture = new TextureRegion(itemtextures, 0, 64, 64, 32);
		pickaxetexture = new TextureRegion(itemtextures, 0, 0, 64, 64);
		kineticpistoltexture = new TextureRegion(itemtextures, 64, 0, 64, 64);
		kineticSMG = new TextureRegion(itemtextures, 192, 0, 64, 64);
		stonetexture = new TextureRegion(itemtextures, 64, 64, 64, 64);
		kineticammo = new TextureRegion(itemtextures, 128, 0, 64, 64);
	}
	public TextureRegion getTexture(String string){
		if (string == "lightkinetic")
			return lightkinetictexture;
		else if (string == "pickaxe")
			return pickaxetexture;
		else if (string == "kineticpistol")
			return kineticpistoltexture;
		else if (string == "kineticSMG")
			return kineticSMG;
		else if (string == "stone")
			return stonetexture;
		else if (string == "kineticammo")
			return kineticammo;
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
