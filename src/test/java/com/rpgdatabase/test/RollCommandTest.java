package com.rpgdatabase.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rpgdatabase.utility.dice.RollCommand;

class RollCommandTest {

	@Test
	void testIntegerGenerate() {
		RollCommand com = new RollCommand();
		
		assertTrue(com.generate("5") != null);
		assertTrue(com.generate("100") != null);
	}
	
	@Test
	void testIntegerMathGenerate() {
		RollCommand com = new RollCommand();
		
		assertTrue(com.generate("5 + 5") != null);
		assertTrue(com.generate("5 - 5") != null);
		assertTrue(com.generate("5 * 5") != null);
		assertTrue(com.generate("5 / 5") != null);
	}
	
	@Test
	void testDiceGenerate() {
		RollCommand com = new RollCommand();
		
		assertTrue(com.generate("1d20") != null);
		assertTrue(com.generate("5d20") != null);
	}
	
	@Test
	void testDiceAndIntegerMathGenerate() {
		RollCommand com = new RollCommand();
		assertTrue(com.generate("5d20 + 5") != null);
		assertTrue(com.generate("5 + 5d20") != null);
		assertTrue(com.generate("5d20 - 5") != null);
		assertTrue(com.generate("5 - 5d20") != null);
		assertTrue(com.generate("5d20 * 5") != null);
		assertTrue(com.generate("5 * 5d20") != null);
		assertTrue(com.generate("5d20 / 5") != null);
		assertTrue(com.generate("5 / 5d20") != null);
	}
	
	@Test
	void testIntegerExecute() {
		RollCommand com = new RollCommand();
		com.generate("5");
		
		try {
			assertTrue(com.execute() == 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIntegerMathExecute() {
		RollCommand com = new RollCommand();
		
		com.generate("5 + 5");
		try {
			assertTrue(com.execute() == 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDiceExecute() {
		RollCommand com = new RollCommand();
		
		com.generate("1d20");
		try {
			int result = com.execute();
			assertTrue((result >= 1) && (result <= 20));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDiceAndIntegerMathExecute() {
		RollCommand com = new RollCommand();
		
		try {
			com.generate("1d20 + 5");
			int result = com.execute();
			assertTrue((result >= 6) && (result <= 25));
			
			com.generate("5 + 1d20");
			result = com.execute();
			assertTrue((result >= 6) && (result <= 25));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
