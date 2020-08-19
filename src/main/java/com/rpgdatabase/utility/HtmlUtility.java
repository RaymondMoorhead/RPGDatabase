package com.rpgdatabase.utility;

public class HtmlUtility {
	
	public static String formatForTextArea(String input) {
		StringBuilder result = new StringBuilder(input);
		int index = result.indexOf("\n", 0);
		
		while(index != -1) {
			result.insert(index + 1, "<br>");
			index = result.indexOf("\n", index + 5);
		}
		return result.toString();
	}

}
