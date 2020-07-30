package com.rpgdatabase.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	public String username;
	public String password;
	public String email;
	public ArrayList<String> characterIds;

	public User(String username, String password, String email, ArrayList<String> characterIds) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.characterIds = characterIds;
	}

	@Override
	public String toString() {
		return "{username:" + username + ", password:" + password + ", email:" + email + "}";
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public ArrayList<String> getCharacterIds() {
		return characterIds;
	}
}
