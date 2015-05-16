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
	
	public void addLevel(int profession){
		professions.get(profession).addLevel();
	}
	public String getHitDie(int profession){
		return ProfessionStats.valueOf(CharacterHelper.professionNames[profession]).hitDie;
	}
	
	public enum ProfessionStats{
		Warrior("1d12", 2, 1, 2),
		Woodsman("1d8", 2, 1, 2),
		Settler("1d6", 0, 0, 4),
		Chief("1d8", 1, 1, 3),
		Druid("1d8", 1, 1, 3),
		Sorcerer("1d6", 0, 1, 3),
		
		Soldier("1d10", 3, 1, 1),
		Musketeer("1d8", 2, 1, 2),
		Blacksmith("1d6", 0, 0, 4),
		Architect("1d6", 0, 0, 4),
		Aristocrat("1d8", 0, 1, 3),
		Enchanter("1d8", 2, 1, 2),
		Warlock("1d6", 0, 1, 4);
		
		public String hitDie;
		public int BAB, lvlBAB, lvlSkills;
		private int[] skills;
		
		ProfessionStats(String hitDie, int BAB, int lvlBAB, int lvlSkills){
			this.hitDie = hitDie;
			this.BAB = BAB;
			this.lvlBAB = lvlBAB;
			this.lvlSkills = lvlSkills;
			
		}
		

	}
	
	private class Profession {
		
		int type;
		int level = 0;
		int BAB;
		private String name;
		private String hitDie;
		Array<Skill> skills;
		boolean isCombat;
		
		ProfessionStats stats;

		public Profession(int type){
			this.type = type;
			name = CharacterHelper.professionNames[type];
			
			stats = ProfessionStats.valueOf(name);
			BAB = stats.BAB;
			hitDie = stats.hitDie;
			skills = new Array<Skill>();
		}
		
		public String getName(){ 
			return name;
		} 
		public String getHitDie(){
			return hitDie;
		}
		public void addLevel(){
			level++;
			BAB += stats.lvlBAB;
		}
	}


}
