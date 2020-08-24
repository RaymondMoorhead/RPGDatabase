package com.rpgdatabase.utility.dice;

public class RollNode {

	// only one of these will be used
	private Dice dice = null;
	private String math = null;
	
	private RollNode next = null;
	
	public String getExpression() throws Exception {

		String value = null;
		if(dice != null)
			value = Integer.toString(dice.roll());
		else if(math != null)
			value = math;
		else
			throw new Exception("RollNode.getExpression() called on unset node");
		
		if(next != null)
			return value + next.getExpression();
		else
			return value;
	}
	
	public static RollNode generate(String input) {
		// because the result will be executed as javascript, 
		// it requires at least a minimum of sanitization
		if(input.matches(".*[abcefghijklmnopqrstuvwxyzABCEFGHIJKLMNOPQRSTUVWXYZ;].*")) {
			System.out.println("suspicious string in RollNode.generate, compilation refused");
			return null;
		}
		
		return generateNoSafety(input);
	}
	
	private static boolean validDiceChar(char character) {
		return (('0' <= character) && (character <= '9')) || (character == 'd' || character == 'D');
	}
	
	private static RollNode generateNoSafety(String input) {
		if(input.isEmpty())
			return null;
		
		RollNode node = new RollNode();
		
		// are there any die rolls in the remaining math?
		int nextDice = input.indexOf('d');
		if((nextDice == -1) && ((nextDice = input.indexOf('D')) == -1)) {
			node.math = input;
			return node;
		}
		
		// if so, find them
		int start = nextDice;
		int end = nextDice;
		
		while((start > 0) && validDiceChar(input.charAt(start))) {
			--start;
		}
		
		if(!validDiceChar(input.charAt(start)))
			++start;
		
		// string begins with a dice roll
		if(start == 0) {
			while((end < input.length()) && validDiceChar(input.charAt(end))) {
				++end;
			}
			
			node.dice = new Dice();
			node.dice.parseDice(input.substring(0, end));
			node.next = generateNoSafety(input.substring(end));
		}
		// string begins with an arithmetic expression
		else {
			node.math = input.substring(0, start);
			node.next = generateNoSafety(input.substring(start));
		}
		
		return node;
	}

	@Override
	public String toString() {
		return (dice != null ? dice.toString() : math) + (next != null ? next.toString() : "");
	}
	
	
}
