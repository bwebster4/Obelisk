package com.obelisk.world.entities;

import com.badlogic.gdx.utils.Array;

public class ProfessionChart {

	private Character character;
	private Array<Profession> professions = new Array<Profession>();
	static final int warrior = 0, woodsman = 1, settler = 2, chief = 3, druid = 4, sorcerer = 5;
	static final int soldier = 6, musketeer = 7, blacksmith = 8, architect = 9, aristocrat = 10, 
			enchanter = 11, warlock = 12;
	static final int numProfessions = 13;
	static String[] professionNames = { "Warrior", "Woodsman", "Settler", "Chief", "Druid", "Sorcerer", 
		"Soldier", "Musketeer", "Blacksmith", "Architect", "Aristocrat", "Enchanter", "Warlock"};
	
	public ProfessionChart(Character character){
		this.character = character;
		
		for(int i = 0; i < numProfessions; i++){
			professions.add(new Profession(i));
		}
		
	}
	
	public enum ProfessionStart{
		WARRIOR(warrior, 1, 0, 0),
		WOODSMAN(woodsman, 0, 1, 0),
		SETTLER(settler, 0, 0, 0),
		CHIEF(chief, 0, 0, 0),
		DRUID(druid, 0, 0, 1),
		SORCERER(sorcerer, 0, 0, 1),;
		
		String name; int meleeMod, rangeMod, magicMod;
		ProfessionStart(int name, int meleeMod, int rangeMod, int magicMod){
			this.name = professionNames[name];
		}
	}
	
	private class Profession {
		
		int level = 0;
		int meleeMod, rangeMod, magicMod;
		String name;
		Array<String> skills;
		boolean isCombat;
		

		public Profession(int type){
			name = professionNames[type];
		}
		
		public String getName(){ 
			return name;
		} 
		public void addLevel(){
			level++;
		}
	}

}
