package com.rpgdatabase.security;

import java.util.Random;

public final class Encrypt {
	
	// PUBLIC METHODS

	public static String encrypt(String toEncrypt)
	{
		return Jumble(stringToHash(toEncrypt), null);
	}
	
	public static String encrypt(String toEncrypt, String key)
	{
		return Jumble(stringToHash(toEncrypt), key);
	}
	
	// PRIVATE JUMBLER VARIABLES
	
	private static final int RAND_VARIATION = 20;
	
	// PRIVATE JUMBLER METHODS
	
	private static String Jumble(String str, String extraSeedKey) {
		
		// set up Random, use the string to set the seed, extra seed key can be used
		Random rand = new Random();
		long seed = 0;
		for(int i = 0; i < str.length(); i += 2)
			seed += str.charAt(i);
		if(extraSeedKey != null)
			for(int i = 1; i < extraSeedKey.length(); i += 2)
				seed += extraSeedKey.charAt(i);
		rand.setSeed(seed);
		
		// jumble string
		StringBuilder result = new StringBuilder(str);
		for(int i = 0; i < result.length(); ++i)
			result.setCharAt(i, (char)(result.charAt(i) + rand.nextInt(RAND_VARIATION)));
		
		return result.toString();
	}
	
	// PRIVATE HASH VARIABLES
	
	private static final char LOWEST_VAL_CHAR = ' ';
	private static final char HIGHEST_VAL_CHAR = '~';
	private static final char[] HASH_INDICES = { '4', 's', 'n', 'V', '1', '3',
			'O', '2', 'C', 'U', '|', '?', 'R', '*', 'y', 'Z', 'P', '=', ' ',
			'X', '>', 'c', 'e', 'F', 't', '7', 'S', '/', 'm', '"', '5', '9',
			'G', 'f', '!', 'K', '%', 'l', '#', 'Y', 'E', 'W', '`', 'q', '$',
			'd', 'H', 'b', 'i', 'v', '}', '.', 'Q', 'T', 'k', 'z', 'o', 'r',
			'A', 'B', 'w', '+', ';', 'p', '\'', 'J', ']', 'M', 'g', '[', '0',
			'L', ',', '{', '-', '^', '~', 'u', '8', '@', 'I', '(', ':', '&',
			'6', 'x', ')', 'D', '\\', '<', 'h', '_', 'a', 'N', 'j' };
	
	// PRIVATE HASH METHODS
	
	private static String stringToHash(String str) {
		String result = "";
		for(char c : str.toCharArray())
			result += toHash(c);
		return result;
	}
	
	private static char toHash(char c) {
		if(c < LOWEST_VAL_CHAR)
			return (char)(c + HIGHEST_VAL_CHAR);
		else if(c > HIGHEST_VAL_CHAR)
			return (char)(c + HIGHEST_VAL_CHAR + LOWEST_VAL_CHAR);
		else
			return HASH_INDICES[c - LOWEST_VAL_CHAR];
	}
}
