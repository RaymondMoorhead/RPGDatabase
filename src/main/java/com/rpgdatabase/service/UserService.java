package com.rpgdatabase.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpgdatabase.entity.User;
import com.rpgdatabase.exception.BadDecryptException;
import com.rpgdatabase.repo.UserRepository;
import com.rpgdatabase.security.Encrypt;

@Service
public class UserService {
	
	public final int maxUsernameLength = 20;
	public final int maxPasswordLength = 20;
	public final int maxEmailLength = 40;
	
	@Autowired
	private UserRepository repository;
	private String error = null;
	
	public String getError() {
		String temp = error;
		error = null;
		return temp;
	}
	
	// error is not used here since for security purposes we don't want
	// to let someone know why it failed, by user existence or password
	public boolean validateUser(String username, String password) {
		return repository.findByUsernameAndPassword(username, morphPassword(username, password)) != null;
	}
	
	public User createUser(String username, String password, String email) {
		// check for existing user
		if(repository.existsByUsername(username)) {
			error = "Username already exists";
			return null;
		}
		else if((error = validatePasswordStrength(password)) != null)
			return null;
		
		User user = new User(username, password, email, new ArrayList<String>());
		user = encryptUser(user);
		repository.save(user);
		return decryptUser(user);
	}
	
	public User getUser(String username, String password) {
		User user = repository.findByUsernameAndPassword(username, morphPassword(username, password));
		if(user != null)
			return decryptUser(user);
		return user;
	}
	
	public boolean doesUserExist(String username) {
		return repository.existsByUsername(username);
	}
	
	public boolean deleteuser(String username, String password) {
		if(validateUser(username, password)) {
			repository.deleteById(username);
			return true;
		}
		else
			return false;
	}
	
	// PRIVATE METHODS FOR VALIDATION PURPOSES
	
	private User encryptUser(User user) {
		user.password = morphPassword(user.username, user.password);
		user.email = Encrypt.encrypt(user.email, user.username);
		return user;
	}
	
	private User decryptUser(User user) {
		try {
			user.password = null; // for security purposes, we needed it earlier, not anymore
			user.email = Encrypt.decrypt(user.email, user.username);
		} catch (BadDecryptException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	private String morphPassword(String username, String password) {
		return Encrypt.encryptIrreversable(password, username, password, maxPasswordLength);
	}
	
	// returns null if everything is fine, otherwise returns the error
	private static String validatePasswordStrength(String password) {
		// check for minimum length
		if(password.length() < 8)
			return "Password is less Than 8 Characters";
		
		// check for required characters
		boolean lower = false, upper = false, special = false;
		for(int i = password.length() - 1; i >= 0; --i) {
			if((password.charAt(i) >= 'a') && (password.charAt(i) <= 'z'))
				lower = true;
			else if((password.charAt(i) >= 'A') && (password.charAt(i) <= 'Z'))
				upper = true;
			else
				special = true;
		}
		
		if(!lower)
			return "Password has no lowercase characters";
		else if(!upper)
			return "Password has no uppercase characters";
		else if(!special)
			return "Password has no special characters";
		else
			return null;
	}
}
