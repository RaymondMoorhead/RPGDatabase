package com.rpgdatabase.utility.dice;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class RollCommand {
	public String value;
	public RollNode head;

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
		if(head == null) {
			if(value != null)
				generate();
			else {
				System.out.println("RollCommand.execute() called with no value");
				return 0;
			}
		}
		
		if(head == null) {
			System.out.println("RollCommand.generate() failed");
			return 0;
		}
		
	    ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
	    return (Integer)engine.eval(head.getExpression());
	}
	
	public RollNode generate() {
		return generate(value);
	}
	
	public RollNode generate(String input) {
		return head = RollNode.generate(value = input);
	}

	@Override
	public String toString() {
		return value;
	}
}
