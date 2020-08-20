package com.rpgdatabase.utility.dice;

public class RollToken {

	private Integer valInt = null;
	private Dice valDie = null;
	
	public int getVal() throws Exception {
		if(valInt != null)
			return valInt;
		else if(valDie != null)
			return valDie.roll();
		else
			throw new Exception("No value provided");
	}
	
	public void setVal(Integer valInt) {
		this.valInt = valInt;
		valDie = null;
	}
	public void setVal(Dice valDie) {
		this.valDie = valDie;
		valInt = null;
	}
	
	public void parseVal(String value) {
		this.valDie = null;
		this.valInt = null;
		
		try {
			valInt = Integer.parseInt(value);
		} catch(Exception e) {
			try {
				if(!valDie.parseDice(value))
					throw new Exception("Bad Dice Parse");
			} catch(Exception e2) {
				System.out.println("RollToken failed to parse an Integer or a Dice with string: " + value);
				System.out.println("\t" + e.getMessage());
				System.out.println("\t" + e2.getMessage());
			}
		}
	}
}
