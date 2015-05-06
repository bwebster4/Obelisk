package com.obelisk.world.pathfinding;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.obelisk.world.Map;

public class Astar {

	Map map;
	
	PriorityQueue<Node> open = new PriorityQueue<Node>(50, new NodeComparator());
	Array<Node> closed = new Array<Node>();
	Node[][] nodes;
	
	public Astar(Map map, Node[][] nodes){
		this.map = map;
		this.nodes = nodes;
	}

	
	public Array<Node> findPath(Vector3 startpos, Vector3 endpos){
		
		Array<Node> path = new Array<Node>();
		
		int startx = (int) startpos.x;
		int starty = (int) startpos.y;
		int endx = (int) endpos.x;
		int endy = (int) endpos.y;
		
		Node startnode = nodes[startx][starty];
		Node endnode = nodes[endx][endy];
		
		if (!map.getWalkable(endx, endy) || map.getTile(endx, endy).getArea() != map.getTile(startx, starty).getArea()){
			path.add(startnode);
			System.out.println("Node unreachable, areas: " + map.getTile(startx, starty).getArea() + " " + map.getTile(endx, endy).getArea());
			return path;
		}
		
		open.clear();
		closed.clear();
		
		open.add(startnode);
		startnode.setF(0);
		startnode.setParent(null);
		
		int iteration = 0;
		while (!open.isEmpty() && !open.peek().equals(endnode)){
			Node current = open.poll();
			closed.add(current);
			for (int x = current.getX() - 1; x < current.getX() + 2; x++){
				for (int y = current.getY() - 1; y < current.getY() + 2; y++){
					if (x == current.getX() && y == current.getY());else if (map.getWalkable(x, y)){
						Node neighbor = nodes[x][y];
						if (neighbor != null){			
							float cost;
							if (x != current.getX() && y != current.getY())
								cost = (float) (current.getG() + Math.sqrt(2) * map.getTile(neighbor.getX(), neighbor.getY()).getMoveCost());
							else
								cost = current.getG() + map.getTile(neighbor.getX(), neighbor.getY()).getMoveCost();
							
							if (open.contains(neighbor) && cost < neighbor.getG()){
								open.remove(neighbor);
								closed.add(neighbor);
							}else if (closed.contains(neighbor, false) && cost < neighbor.getG()){
								closed.removeValue(neighbor, false);
								open.add(neighbor);
							}else if (!open.contains(neighbor) && !closed.contains(neighbor, true)){
								neighbor.setG(cost);
								neighbor.setParent(current);
								neighbor.setF(neighbor.getG() + getHeuristic(current.getPos(), endnode.getPos()));
								open.add(neighbor);
							}
							iteration++;
						}
					}
				}
			}
		}
		
		Node node = open.poll();
		path = buildPath(node);
		open.clear();
//		System.out.println(iteration + " " + path.size);
		return path;		
	}
	
	public Array<Node> buildPath(Node end){
		
		Array<Node> path = new Array<Node>();
		path.add(end);
		int pathnodes = 1;
		Node next = end.getParent();
		while (next != null){
			pathnodes++;
			path.add(next);
			next = path.peek().getParent();
		}
		
//		System.out.println("PathNodes: " + pathnodes);
		return path;
		
	}
	public float getHeuristic(Vector3 a, Vector3 b){
		float dx = Math.abs(a.x - b.x);
		float dy = Math.abs(a.y - b.y);
		
		float D = map.getTile(a.x, a.y).getMoveCost();
		float D2 = (float) (Math.sqrt(2) * D);
		
		return (D * (dx + dy) + (D2 - 2f * D) * Math.min(dx, dy));
		
	}
	
	
	private class NodeComparator implements Comparator<Node>{

		@Override
		public int compare(Node a, Node b) {
			return Math.round(100 * (a.getF() - b.getF()));
		}
		
	}
}

