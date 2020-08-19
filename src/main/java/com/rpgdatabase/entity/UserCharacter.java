package com.rpgdatabase.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class UserCharacter {
	


	@Id
	public String id;
	public String creatorName;
	public String name;
	public String bio;
	public List<Feat> features;

	public UserCharacter(String creatorName, String name, String bio) {
		super();
		this.id = null;
		this.creatorName = creatorName;
		this.name = name;
		this.bio = bio;
		this.features = new ArrayList<Feat>();
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
	public List<Feat> getFeatures() {
		return features;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public void addFeature(String name, String description) {
		features.add(new Feat(name, description));
	}
	
	public void changeFeature(int index, String name, String description) {
		Feat feat = features.get(index);
		feat.name = name;
		feat.description = description;
	}
	
	public void removeFeature(int index) {
		features.remove(index);
	}
	
	@Override
	public String toString() {
		return "UserCharacter [id=" + id + ", creatorName=" + creatorName + ", name=" + name + ", bio=" + bio + ", features=" + features
				+ "]";
	}
}
