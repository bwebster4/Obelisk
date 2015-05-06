package com.obelisk.world.entities;

import com.badlogic.gdx.utils.ObjectMap;

public class ProfessionChart {

	Character character;
	
	String[] wildNames = {"Frontiersman", "Woodsman", "Warrior", "Settler", "Druid"};
	String[] cityNames = {"Apprentice", "Soldier", "Musketeer", };
	ObjectMap<String, Integer> professions = new ObjectMap<String, Integer>();
	
	public ProfessionChart(Character character){
		this.character = character;
		
		Frontiersman frontiersman = new Frontiersman();
		
	}
	
	public void addLevel(String profession){
		int newLevel = professions.get(profession) + 1;
		professions.put(profession, newLevel);
	}
	
	private class Frontiersman{
		boolean started = false;
		
	}
}
