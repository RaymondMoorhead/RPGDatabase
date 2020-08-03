package com.rpgdatabase.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.rpgdatabase.exception.BadDecryptException;
import com.rpgdatabase.security.Encrypt;

class TestEncrypt {

	@Test
	void testEncrypt1() {
		String encrypted = Encrypt.encryptIrreversable("Hello", "password");
		assertTrue(encrypted.equals(Encrypt.encryptIrreversable("Hello", "password")));
		assertFalse(encrypted.equals("Hello"));
		assertThrows(BadDecryptException.class, () -> {Encrypt.decrypt(encrypted, "password").equals("Hello");});
	}
	
	@Test
	void testEncrypt2() {
		String encrypted = Encrypt.encryptIrreversable("Hello", "password1", "password2");
		assertTrue(encrypted.equals(Encrypt.encryptIrreversable("Hello", "password1", "password2")));
		assertFalse(encrypted.equals("Hello"));
	}
	
	@Test
	void testEncrypt3() {
		String encrypted = Encrypt.encryptIrreversable("Hello", "password1", "password2", 20);
		assertTrue(encrypted.equals(Encrypt.encryptIrreversable("Hello", "password1", "password2", 20)));
		assertTrue(encrypted.length() == 20);
		assertFalse(encrypted.equals("Hello"));
	}
	
	@Test
	void testEncryptReversable() {
		assertTrue(Encrypt.encrypt("Hello", "password").equals(Encrypt.encrypt("Hello", "password")));
		assertFalse(Encrypt.encrypt("Hello", "password").equals("Hello"));
		
		String encrypted = Encrypt.encrypt("Hello", "password");
		try {
			assertTrue(Encrypt.decrypt(encrypted, "password").contentEquals("Hello"));
		} catch (BadDecryptException e) {
			e.printStackTrace();
		}
	}

}
