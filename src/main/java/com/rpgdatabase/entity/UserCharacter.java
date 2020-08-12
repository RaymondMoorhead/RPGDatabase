package com.rpgdatabase.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class UserCharacter {

	@Id
	public String id;
	public String creatorName;
	public String name;
	public String bio;
	public Map<String, String> features;

	public UserCharacter(String creatorName, String name, String bio) {
		super();
		this.id = null;
		this.creatorName = creatorName;
		this.name = name;
		this.bio = bio;
		this.features = new HashMap<String, String>();
	}
	
	public String getId() {
		return id;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public String getName() {
		return name;
	}
	public String getBio() {
		return bio;
	}
	public Map<String, String> getFeatures() {
		return features;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public boolean addFeature(String name, String description) {
		if(features.containsKey(name))
			return false;
		features.put(name, description);
		return true;
	}
	public boolean removeFeature(String name) {
		return features.remove(name) != null;
	}
	
	@Override
	public String toString() {
		return "UserCharacter [id=" + id + ", creatorName=" + creatorName + ", name=" + name + ", bio=" + bio + ", features=" + features
				+ "]";
	}
}
