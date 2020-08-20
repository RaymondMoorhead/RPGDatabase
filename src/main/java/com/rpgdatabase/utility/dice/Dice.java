package com.rpgdatabase.utility.dice;

import java.util.Random;

public class Dice {

	int quantity = 1;
	int size = 1;
	
	public Dice(int quantity, int size) {
		super();
		this.quantity = quantity;
		this.size = size;
	}
	public Dice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	// returns true on success
	public boolean parseDice(String input) {
		int quantity;
		int size;
		try {
			// parse die rolls
			int dIndex = input.indexOf('d');
			
			if(dIndex == -1)
				return false;
			
			// parse quantity
			// this means that "d20" defaults to "1d20"
			quantity = 1;
			if(dIndex > 0)
				quantity = Integer.parseInt(input.substring(0, dIndex));
			
			// parse size
			size = Integer.parseInt(input.substring(++dIndex));
			
			// final safety checks
			if(quantity < 0 || size < 0)
				return false;
			
			// lock-in the result
			this.quantity = quantity;
			this.size = size;
			
			return true;
		} catch(Exception e) {
			System.out.println("Dice.parseDice Exception: " + e.getMessage());
			return false;
		}
	}
	
	public int roll() throws Exception {
		return roll(quantity, size);
	}
	
	public static int roll(int quantity, int size) throws Exception {
		
		// safety check
		if(quantity < 1)
			throw new Exception("(quantity <= 0) provided to Dice.roll");
		else if(size < 1)
			throw new Exception("(size <= 0) provided to Dice.roll");
		
		Random rand = new Random();
		int result = 0;
		
		while(quantity > 0) {
			result += rand.nextInt(size) + 1;
			--quantity;
		}
		return result;
	}
}
