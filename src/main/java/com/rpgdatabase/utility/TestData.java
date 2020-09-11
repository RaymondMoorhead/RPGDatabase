package com.rpgdatabase.utility;

import com.rpgdatabase.entity.User;
import com.rpgdatabase.entity.UserCharacter;
import com.rpgdatabase.service.UserCharacterService;
import com.rpgdatabase.service.UserService;

public class TestData {
	
	private static final String testUsername = "Karrejahl";
	private static final String testPassword = "ShieldBash!";
	
	private static final String testCharName = "Muansi";
	private static final String testCharBio = "\"Everything must be used to its fullest, never waste or you will suffer for it.\"\r\n" + 
			"\r\n" + 
			"\"It is we, in this moment, that are part of the time that continues, they are walking shadows of what might have been that will fade away, they may as well be useful to us.\"\r\n" + 
			"\r\n" + 
			"\"They are memories no one has had\"\r\n" + 
			"\r\n" + 
			"    I am Muansi (\"Large\" in Draconic), a Waphir of tribe Jilinth. I have been given the task of finding the Instruction of Irral Jilinth (his spellbook), which the Juanth claims to contain the secrets of greater Jilinth abilities. Irral's Instruction was described to me as a stack of thin and wide reeds, each covered in marks of many colors. This stack is bound together with twisted grass, protected by thin slabs of wood on the top and bottom, and may lie within a bag formed from the skin of Kecuala (a deity, and what Lizardfolk call themselves).\r\n" + 
			"\r\n" + 
			"     I was trained by the Juanth on how to speak the common tongue, what the scaleless may call the events of my tribe's past, and where the bound plants may be- probably within the home of the chieftain of our invader. I met with and questioned many humanoids, and they have told me of the death of this \"Khan,\" and places where this \"Khan\" has been.";

	public static User createTestUser(UserService service) {
		User user;
		if(service.doesUserExist(testUsername))
			user = service.getUser(testUsername, testPassword);
		else
			user = service.createUser(testUsername, testPassword, "coolguy@westmarches.com");
		return user;
	}
	
	public static UserCharacter createTestCharacter(UserCharacterService service) {
		UserCharacter character;
		character = service.createCharacter(testUsername, testCharName, testCharBio);
		
		// add the features
		character.addFeature("Attack", "Attempt to strike your enemy with some weapon", "1d20");
		character.addFeature("Spear Attack", "Attempt to strike your enemy with your spear", "1d20");
		character.addFeature("+1 Spear", "Stab with your magical spear", "1d8 + 1").addOutRoll("1", "Spear Attack");
		character.addFeature("Bite", " Your fanged maw is a natural weapon, which you can use to make unarmed strikes. If you hit with it, you deal piercing damage equal to 1d6 + your Strength modifier, instead of the bludgeoning damage normal for an unarmed strike. ", "1d6");
		character.addFeature("Armor Class", "Your ability to evade or deflect blows", "10");
		character.addFeature("Shield", "A simple shield of bone and leather which offers basic protection", "").addOutRoll("2", "Armor Class");
		character.addFeature("Ability Scores", "STR: 666+2(6th ASI)\r\n" + 
				"DEX: 554\r\n" + 
				"CON: 655+2(Race)\r\n" + 
				"INT: 433\r\n" + 
				"WIS:443+1(Race)\r\n" + 
				"CHA: 222", "").addOutRoll("5", "Attack", "Spear Attack", "+1 Spear", "Bite").addOutRoll("2", "Armor Class");
		character.addFeature("Progression", "4-[Spear Mastery]\r\n" + 
				"5-Extra Attack\r\n" + 
				"6-[+2STR]\r\n" + 
				"7-Echo Avatar\r\n" + 
				"8-[Polearm Master]\r\n" + 
				"9-Indomitable\r\n" + 
				"10-Shadow Martyr\r\n" + 
				"11-++Extra Attack\r\n" + 
				"12-[2DEX]\r\n" + 
				"13-++Indomitable\r\n" + 
				"14-[+2CON]\r\n" + 
				"15-Reclaim Potential\r\n" + 
				"16-[Medium Armor Master]\r\n" + 
				"17-++Action Surge, ++Indomitable\r\n" + 
				"18-Legion of One\r\n" + 
				"19-[Shield Master]\r\n" + 
				"20-++Extra Attack", "");
		character.addFeature("Fighter: Echo Knight", "A martial master, taught how to summon and use echoes, the otherwise lost remnants of unprogressed timelines", "").addOutRoll("6", "Second Wind");
		character.addFeature("Spear Mastery", "You gain a +1 bonus to attack rolls you make with a spear.\r\n" + 
				"\r\n" + 
				"When you use a spear, its damage die changes from a d6 to a d8, and from a d8 to a d10 when wielded with two hands. (This benefit has no effect if another feature has already improved the weapon’s die.)\r\n" + 
				"\r\n" + 
				"You can set your spear to receive a charge. As a bonus action, choose a creature you can see that is at least 20 feet away from you. If that creatures moves within your spear’s reach on its next turn, you can make a melee attack against it with your spear as a reaction. If the attack hits, the target takes an extra 1d8 piercing damage, or an extra 1d10 piercing damage if you wield the spear with two hands. You can’t use this ability if the creature used the Disengage action before moving.\r\n" + 
				"\r\n" + 
				"As a bonus action on your turn, you can increase your reach with a spear by 5 feet for the rest of your turn.", "").addOutRoll("1", "Spear Attack");
		character.addFeature("Fighting Style: Dueling", "When you are wielding a melee weapon in one hand and no other weapons, you gain a +2 bonus to damage rolls with that weapon", "").addOutRoll("2", "+1 Spear");
		character.addFeature("Second Wind", "You have a limited well of stamina that you can draw on to protect yourself from harm. On your turn, you can use a bonus action to regain hit points equal to 1d10 + your fighter level. \r\n" + 
				"\r\n" + 
				"Once you use this feature, you must finish a short or long rest before you can use it again.\r\n" + 
				"", "1d10");
		character.addFeature("Extra Attack", "Beginning at 5th Level, you can Attack twice, instead of once, whenever you take the Attack action on Your Turn.\r\n" + 
				"\r\n" + 
				"The number of attacks increases to three when you reach 11th level in this class and to four when you reach 20th level in this class.", "");
		character.addFeature("Manifest Echo", "  You can use a bonus action to magically manifest an echo of yourself in an unoccupied space you can see within 15 feet of you. This echo is a magical, translucent, gray image of you that lasts until it is destroyed, until you dismiss it as a bonus action, until you manifest another echo, or until you're incapacitated.\r\n" + 
				"  Your echo has AC 14 + your proficiency bonus, 1 hit point, and immunity to all conditions. If it has to make a saving throw, it uses your saving throw bonus for the roll. It is the same size as you, and it occupies its space. On your turn, you can mentally command the echo to move up to 30 feet in any direction (no action required). If your echo is ever more than 30 feet from you at the end of your turn, it is destroyed. You can use the echo in the following ways:\r\n" + 
				"\r\n" + 
				"As a bonus action, you can teleport, magically swapping places with your echo at a cost of 15 feet of your movement, regardless of the distance between the two of you.\r\n" + 
				"\r\n" + 
				"When you take the Attack action on your turn, any attack you make with that action can originate from your space or the echo's space. You make this choice for each attack.\r\n" + 
				"\r\n" + 
				"When a creature that you can see within 5 feet of your echo moves at least 5 feet away from it, you can use your reaction to make an opportunity attack against that creature as if you were in the echo's space.", "");
		character.addFeature("Unleash Incarnation", "  You can heighten your echo's fury. Whenever you take the Attack action, you can make one additional melee attack from the echo's position.\r\n" + 
				"  You can use this feature a number of times equal to your Constitution modifier (a minimum of once). You regain all expended uses when you finish a long rest.", "");
		character.addFeature("Cunning Artisan", "As part of a short rest, you can harvest bone and hide from a slain beast, construct, dragon, monstrosity, or plant creature of size Small or larger to create one of the following items: a shield, a club, a javelin, or 1d4 darts or blowgun needles. To use this trait, you need a blade, such as a dagger, or appropriate artisan's tools, such as leatherworker's tools", "");
		character.addFeature("Hungry Jaws", " In battle, you can throw yourself into a vicious feeding frenzy. As a bonus action, you can make a special attack with your bite. If the attack hits, it deals its normal damage, and you gain temporary hit points (minimum of 1) equal to your Constitution modifier, and you can't use this trait again until you finish a short or long rest. ", "");
		character.addFeature("Hunter's Lore", "You gain proficiency with two of the following skills of your choice: Animal Handling, Nature, Perception, Stealth, and Survival.\r\n" + 
				"\r\n" + 
				"Chose: Perception, Survival", "");
		character.addFeature("Natural Armor", " You have tough, scaly skin. When you\r\n" + 
				"aren't wearing armor, your AC is 13 + your Dexterity modifier. You can use your natural armor to determine your AC if the armor you wear would leave you with a lower AC. A shield's benefits apply as normal while you use your natural armor. ", "").addOutRoll("3", "Armor Class");
		
		character.addFeature("Wanderer", "  You have an excellent memory for maps and geography, and you can always recall the general layout of terrain, settlements, and other features around you. In addition, you can find food and fresh water for yourself and up to five other people each day, provided that the land offers berries, small game, water, and so forth.\r\n" + 
				"", "");
		
		service.updateCharacter(character);
		return character;
	}
}
