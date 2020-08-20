package com.rpgdatabase.utility.dice;

public class RollCommand {
	public String value;
	public RollNode head = null;;

	public RollCommand(String value) {
		super();
		this.value = value;
	}

	public RollCommand() {
		super();
		value = "0";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public int execute() throws Exception {
		if(head == null)
			interpret();
		
		return head.evaluate();
	}
	
	public boolean interpret(String value) {
		this.value = value;
		return interpret();
	}
	
	public boolean interpret() {
		
		throw new RuntimeException("RollCommand.interpret is not implemented");
	}
}
