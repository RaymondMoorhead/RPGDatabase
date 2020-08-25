package com.rpgdatabase.utility.dice;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RollCommand {
	public String value;
	public RollNode head;
	private static ScriptEngine engine;
	
	static {
		engine = new ScriptEngineManager().getEngineByName("graal.js");
	}

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
		
	    return (Integer)engine.eval(head.getExpression());
	}
	
	// this method should almost never be used, because compiling
	// a command just to execute it once is slow and terrible,
	// but JavaScipt demands to have only the worst at its grimy
	// hag-like fingertips, so here we are.
	public static int executeOnce(String input) throws Exception {
		RollNode node = RollNode.generate(input);
		if(node == null)
			return -1;
		else
			return (Integer)engine.eval(node.getExpression());
	}
	public static String getFive() { return "5";}
	
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
