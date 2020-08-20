package com.rpgdatabase.utility.dice;

public class RollNode {

	private RollToken token = null;
	
	private RollNode left = null, right = null;
	private Character operation = null;
	
	public int getMaxDepth() {
		if(token != null)
			return -1;
		else
			return Math.max(left.getMaxDepth(), right.getMaxDepth()) + 1;
	}
	
	public int evaluate() throws Exception {
		if(token != null)
			return token.getVal();
		else if(left != null && right != null) {
			int leftResult = left.evaluate();
			int rightResult = right.evaluate();
			
			switch(operation) {
				case '+':
					return leftResult + rightResult;
				case '-':
					return leftResult - rightResult;
				case '*':
					return leftResult * rightResult;
				case '/':
					return leftResult / rightResult;
				default:
					throw new Exception("RollNode.evaluate Invalid operation");
			}
		}
		else
			throw new Exception("No data provided");
	}
	
	public void setToken(RollToken token) {
		this.token = token;
		this.left = null;
		this.right = null;
		this.operation = null;
	}
	
	public void setNodes(RollNode left, RollNode right, Character operation) {
		this.left = left;
		this.right = right;
		this.operation = operation;
		this.token = null;
	}
	
	
}
