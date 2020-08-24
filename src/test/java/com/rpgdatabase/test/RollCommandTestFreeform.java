package com.rpgdatabase.test;

import java.util.Scanner;

import com.rpgdatabase.utility.dice.RollCommand;

public class RollCommandTestFreeform {

	public static void main(String[] args) throws Exception {
		RollCommand com = new RollCommand();
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(true) {
			input = scan.nextLine();
			if(input.equals("exit"))
				break;
			
			com.generate(input);
			if(com.head != null) {
				System.out.println("\tCompiled:" + com.head.toString());
				System.out.println("\tExecuted:" + com.execute());
			}
			else
				System.out.println("head is null");
		}
		scan.close();
	}

}
