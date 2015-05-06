package com.obelisk.world.pathfinding;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.obelisk.world.mapelems.Block;
import com.obelisk.world.mapelems.Tile;

public class Node{

	float f, g;
	Node parent;
	Block cloneblock;
	Tile clonetile;
	boolean isBlock;
	Texture texture;
	
	public Node(Block clone){
		this.cloneblock = clone;
		isBlock = true;
	}
	
	public Node(Tile clone){
		this.clonetile = clone;
		isBlock = false;
	}

	public void setF(float f){
		this.f = f;
	}
	public float getF(){
		return f;
	}
	public void setG(float g){
		this.g= g;
	}
	public float getG(){
		return g;
	}
	public void setParent(Node parent){
		this.parent = parent;
	}
	public Node getParent(){
		return parent;
	}
	public int getX(){
		if (isBlock)
			return cloneblock.getX();
		else 
			return (int) clonetile.getX();
	}
	public int getY(){
		if (isBlock)
			return cloneblock.getY();
		else 
			return (int) clonetile.getY();
	}
	public Vector3 getPos(){
		return new Vector3(getX(), getY(), 0);
	}
	public void setTexture(Texture texture){
		this.texture = texture;
	}
	public Texture getTexture(){
		return texture;
	}
}

