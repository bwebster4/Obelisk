package com.obelisk.world.entities.skills;

public class Skill {
	public static final int passive = 0, oneTime = 1, duration = 2;
	private int type;
	private int rank;
	private String name;
	
	private int cooldown = 0;
	
	
	public Skill(int type, String name){
		rank = 0;
		this.type = type;
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	public int getType(){
		return type;
	}
	public int getRank(){
		return rank;
	}
	
	public void addRank(){
		rank++;
	}
	public Skill copy(){
		return new Skill(type, name);
	}
	
	public void use(){
		
	}
}


