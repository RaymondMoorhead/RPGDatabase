package com.rpgdatabase.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.rpgdatabase.utility.dice.Dice;

class DiceTest {
	
	static final int testNum = 10000;
	static final int maxDiceQuantity = 100;
	static final int maxDiceSize = 100;

	@Test
	void testRollInput() {
		assertThrows(Exception.class, () -> {
			Dice.roll(0, 1);
		});
		
		assertThrows(Exception.class, () -> {
			Dice.roll(-1, 1);
		});
		
		assertThrows(Exception.class, () -> {
			Dice.roll(1, 0);
		});
		
		assertThrows(Exception.class, () -> {
			Dice.roll(1, -1);
		});
	}
	
	@Test
	void testRollValues() {
		Random rand = new Random(0);
		int result = -1;
		int quantity = 1;
		int size = -1;
		
		for(int i = 0; i < testNum; ++i) {
			try {
				quantity = rand.nextInt(maxDiceQuantity) + 1;
				size = rand.nextInt(maxDiceSize) + 1;
				result = Dice.roll(quantity, size);
				assertTrue((result >= quantity) && (result <= (quantity * size)));
			} catch (Exception e) {
				fail("Dice.roll threw an unexpected error");
				e.printStackTrace();
				break;
			}
		}
	}
	
	@Test
	void testValidParse() {
		Dice testDie = new Dice();
		String[] inputs = {
				"1d20",
				"d4",
				"100d100",
				"d100",
				"100d4"
		};
		
		for(String input : inputs)
			assertTrue(testDie.parseDice(input));
	}

	@Test
	void testInvalidParse() {
		Dice testDie = new Dice();
		String[] inputs = {
				"1d 20",
				" d4",
				"100 d 100",
				"d-100",
				"-100d4",
				"104",
				"100d 4"
		};
		
		for(String input : inputs)
			assertFalse(testDie.parseDice(input));
	}
}
