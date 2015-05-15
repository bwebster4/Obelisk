package com.obelisk.world.entities;

import java.util.Arrays;

import com.badlogic.gdx.utils.Array;
import com.obelisk.world.entities.skills.Skill;

public class ProfessionChart {

	private Character character;
	private Array<Profession> professions = new Array<Profession>();
	
	public ProfessionChart(Character character){
		this.character = character;
		
		for(int i = 0; i < CharacterHelper.numProfessions; i++){
			professions.add(new Profession(i));
		}
		
	}
	
	public enum ProfessionStats{
		Warrior(2, 1, 2),
		Woodsman(2, 1, 2),
		Settler(0, 0, 4),
		Chief(1, 1, 3),
		Druid(1, 1, 3),
		Sorcerer(1, 1, 3),
		
		Soldier(3, 1, 1);
		
		public int BAB, lvlBAB, lvlSkills;
		private int[] skills;
		
		ProfessionStats(int BAB, int lvlBAB, int lvlSkills){
			this.BAB = BAB;
			this.lvlBAB = lvlBAB;
			this.lvlSkills = lvlSkills;
			
		}
		

	}
	
	private class Profession {
		
		int type;
		int level = 0;
		int BAB;
		String name;
		Array<Skill> skills;
		boolean isCombat;
		
		ProfessionStats stats;

		public Profession(int type){
			this.type = type;
			name = CharacterHelper.professionNames[type];
			
			stats = ProfessionStats.valueOf(name);
			BAB = stats.BAB;
			skills = new Array<Skill>();
		}
		
		public String getName(){ 
			return name;
		} 
		public void addLevel(){
			level++;
			BAB += stats.lvlBAB;
		}
	}


}
