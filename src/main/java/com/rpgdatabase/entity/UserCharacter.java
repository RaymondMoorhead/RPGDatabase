package com.rpgdatabase.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class UserCharacter {

	@Id
	public String id;
	public String creatorName;
	public String name;
	public Map<String, String> features;
	

	
	public UserCharacter(String creatorName, String name) {
		super();
		this.id = null;
		this.creatorName = creatorName;
		this.name = name;
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
	public Map<String, String> getFeatures() {
		return features;
	}
	
	public boolean addFeature(String name, String description) {
		if(features.containsKey(name))
			return false;
		features.put(name, description);
		return true;
	}
}
