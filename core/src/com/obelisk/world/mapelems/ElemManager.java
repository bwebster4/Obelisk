package com.obelisk.world.mapelems;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.obelisk.world.items.ItemType;

public class ElemManager {

	TextureAtlas textureAtlas;
	ArrayMap<String, TextureRegion> textures;
	
	public ElemManager(){
		textureAtlas = new TextureAtlas("res/landscape.txt");
		textures = new ArrayMap<String, TextureRegion>();
		textures.insert(0, "Grass", textureAtlas.findRegion("slice29_29"));
		textures.insert(textures.size, "RockyGrass", textureAtlas.findRegion("slice54_54"));
		textures.insert(textures.size, "Stone", textureAtlas.findRegion("slice41_41"));
		textures.insert(textures.size, "Tree", textureAtlas.findRegion("slice389_"));
	}
	
	public TextureRegion getTexture(String name){
		return textures.get(name);
	}
	
	public MapElem getNewElem(MapElemType type, int x, int y){
		MapElem elem = new MapElem(type, x, y, textures.get(type.getName()));
		return elem;
	}
	
}
