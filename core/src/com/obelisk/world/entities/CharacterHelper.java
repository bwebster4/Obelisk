package com.obelisk.world.entities;

import com.obelisk.world.entities.skills.Skill;

public class CharacterHelper {
	
	/*
	 * Professions
	 */
	static final int warrior = 0, woodsman = 1, settler = 2, chief = 3, druid = 4, sorcerer = 5;
	static final int soldier = 6, musketeer = 7, blacksmith = 8, architect = 9, aristocrat = 10, 
			enchanter = 11, warlock = 12;
	static final int numProfessions = 13;
	static String[] professionNames = { "Warrior", "Woodsman", "Settler", "Chief", "Druid", "Sorcerer", 
		"Soldier", "Musketeer", "Blacksmith", "Architect", "Aristocrat", "Enchanter", "Warlock"};
	
	/*
	 * Skills
	 */
	
	static Skill doubleStrike = new Skill(Skill.oneTime, "Double Strike");
	
}
