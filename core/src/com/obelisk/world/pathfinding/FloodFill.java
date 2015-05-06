package com.obelisk.world.pathfinding;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.world.Map;

public class FloodFill {

	Map map;
	
	PriorityQueue<Node> open = new PriorityQueue<Node>(50, new NodeComparator());
//	Array<Node> open = new Array<Node>();
	Array<Node> closed = new Array<Node>();
	Node[][] nodes;

	public FloodFill(Map map, Node[][] nodes){
		this.map = map;
		this.nodes = nodes;
	}

	
	public Array<Node> FloodWalk4(Vector3 startpos){
		
		int startx = (int) startpos.x;
		int starty = (int) startpos.y;
		
		open.clear();
		closed.clear();
		
		open.add(nodes[startx][starty]);
		
		int iteration = 0;
		
		while(!open.isEmpty()){
			Node current = open.poll();
			closed.add(current);
			iteration++;
			if(map.getTile(current.getX() - 1, current.getY()) != null && map.getWalkable(current.getX() - 1, current.getY()) && !closed.contains(nodes[current.getX() - 1][current.getY()], true)){
				open.add(nodes[current.getX() - 1][current.getY()]);
			}
			if(map.getTile(current.getX() + 1, current.getY()) != null &&map.getWalkable(current.getX() + 1, current.getY()) && !closed.contains(nodes[current.getX() + 1][current.getY()], true)){
				open.add(nodes[current.getX() + 1][current.getY()]);
			}
			if(map.getTile(current.getX(), current.getY() - 1) != null &&map.getWalkable(current.getX(), current.getY() - 1) && !closed.contains(nodes[current.getX()][current.getY() - 1], true)){
				open.add(nodes[current.getX()][current.getY() - 1]);
			}
			if(map.getTile(current.getX(), current.getY() + 1) != null &&map.getWalkable(current.getX(), current.getY() + 1) && !closed.contains(nodes[current.getX()][current.getY()+ 1], true)){
				open.add(nodes[current.getX()][current.getY() + 1]);
			}
			//System.out.println("Iterations: " + iteration);
		}
		
		return closed;
		
	}
	public Array<Node> FloodLtd4(Vector3 startpos, int range){
		
		Node neighbor;
		
		int startx = (int) startpos.x;
		int starty = (int) startpos.y;
		
		open.clear();
		closed.clear();
		
		open.add(nodes[startx][starty]);
		open.peek().setF(0);
		int iteration = 0;
		
		while(!open.isEmpty()){
			Node current = open.poll();
			if (current.getF() < range){
				closed.add(current);
				iteration++;
				if(map.getTile(current.getX() - 1, current.getY()) != null && map.getBlock(current.getX() - 1, current.getY()) == null && !closed.contains(nodes[current.getX() - 1][current.getY()], true)){
					neighbor = nodes[current.getX() - 1][current.getY()];
					neighbor.setParent(current);
					neighbor.setF(current.getF() + 2);
					open.add(neighbor);
				}
				if(map.getTile(current.getX() + 1, current.getY()) != null &&map.getBlock(current.getX() + 1, current.getY()) == null && !closed.contains(nodes[current.getX() + 1][current.getY()], true)){
					neighbor = nodes[current.getX() + 1][current.getY()];
					neighbor.setParent(current);
					neighbor.setF(current.getF() + 2);
					open.add(neighbor);
				}
				if(map.getTile(current.getX(), current.getY() - 1) != null &&map.getBlock(current.getX(), current.getY() - 1) == null && !closed.contains(nodes[current.getX()][current.getY() - 1], true)){
					neighbor = nodes[current.getX()][current.getY() - 1];
					neighbor.setParent(current);
					neighbor.setF(current.getF() + 2);
					open.add(neighbor);
				}
				if(map.getTile(current.getX(), current.getY() + 1) != null &&map.getBlock(current.getX(), current.getY() + 1) == null && !closed.contains(nodes[current.getX()][current.getY()+ 1], true)){
					neighbor = nodes[current.getX()][current.getY() + 1];
					neighbor.setParent(current);
					neighbor.setF(current.getF() + 2);
					open.add(neighbor);
				}
			}
		}
		
		return closed;
	}
	
	
	
	private class NodeComparator implements Comparator<Node>{

		@Override
		public int compare(Node a, Node b) {
			return (int)( a.getF() - b.getF());
		}
		
	}
}
