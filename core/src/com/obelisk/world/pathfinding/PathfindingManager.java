package com.obelisk.world.pathfinding;

import com.badlogic.gdx.Gdx;
import com.obelisk.world.Map;

public class PathfindingManager {

	Map map;
	Astar astar;
	FloodFill floodfill;
	
	
	Node[][] nodes = new Node[Map.WORLD_SIZE * Map.CHUNK_SIZE][Map.WORLD_SIZE * Map.CHUNK_SIZE];
	
	public PathfindingManager(){
	}
	
	public void show(Map map){
		this.map = map;
		
		astar = new Astar(map, nodes);
		floodfill = new FloodFill(map, nodes);
		
		for (int x = 0; x < Map.WORLD_SIZE; x++){
			for (int y = 0; y < Map.WORLD_SIZE; y++){
				if (map.getBlock(x, y) != null)
					nodes[x][y] = new Node(map.getBlock(x, y));
				else
					nodes[x][y] = new Node (map.getTile(x, y));
			}
		}	
	}
	
	public Node[][] getNodes(){
		return nodes;
	}
	public Astar getAstar(){
		Gdx.app.log("PathfindingManager", "astar = " + astar);
		return astar;
	}
	public FloodFill getFloodFill(){
		return floodfill;
	}
}
