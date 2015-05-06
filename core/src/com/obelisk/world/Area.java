package com.obelisk.world;

import com.badlogic.gdx.utils.Array;
import com.obelisk.world.pathfinding.Node;

public class Area {

	Map map;
	Array<Node> area;
	Node[][] nodes;
	int id;
	
	public Area (int id, Node[][] nodes, Map map){
		this.nodes = nodes;
		this.map = map;
		this.id = id;
	}
	
	public void addNode(float x, float y){
		map.getTile(x, y).setArea(id);
		area.add(nodes[(int) x][(int) y]);
	}
	
	public int getID(){
		return id;
	}
	public Array<Node> getArea(){
		return area;
	}
	public void setArea(Array<Node> area){
		this.area = area;
	}
}
