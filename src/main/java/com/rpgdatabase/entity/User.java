package com.rpgdatabase.entity;

import java.util.ArrayList;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class User {
	@Id
	public String username;
	public String password;
	public String email;
	public ArrayList<String> characterIds;

	public User() {
		super();
		this.username = "DefaultUsername";
		this.password = "DefaultPassword";
		this.email = "DefaultEmail";
		characterIds = new ArrayList<String>();
	}

	public User(String username, String password, String email, ArrayList<String> characterIds) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.characterIds = characterIds;
	}

	@Override
	public String toString() {
		return "{username:" + username + ", password:" + password + ", email:" + email + ", numCharacters:" + characterIds.size() + "}";
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
	
	public void clear() {
		this.username = null;
		this.password = null;
		this.email = null;
		this.characterIds = null;
	}
	
	public void copy(User user) {
		this.username = user.username;
		this.password = user.password;
		this.email = user.email;
		this.characterIds = user.characterIds;
	}
}
