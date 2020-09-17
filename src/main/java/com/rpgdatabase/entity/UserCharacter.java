package com.rpgdatabase.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;

import com.rpgdatabase.utility.Pair;

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
	
	public void setId(String id) {
		this.id = id;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public void setFeatures(List<Feat> features) {
		this.features = features;
	}
	
	public Feat addFeature(String name, String description, String selfRoll) {
		Feat result = new Feat(name, description, selfRoll);
		features.add(result);
		return result;
	}
	
	public Feat changeFeature(int index, String name, String description, String selfRoll) {
		Feat feat = features.get(index);
		feat.name = name;
		feat.description = description;
		feat.selfRoll = selfRoll;
		return feat;
	}
	
	public void removeFeature(int index) {
		features.remove(index);
	}
	
	public void compileFeatureMods() {
		Map<String, StringBuilder> featModStore = new HashMap<String, StringBuilder>();
		StringBuilder featMod;
		
		// first pass to identify targets and tally modifiers
		for(int i = 0; i < features.size(); ++i){
			for(Pair<String, List<String> > mod : features.get(i).outRoll) {
				for(String target : mod.second) {
					featMod = featModStore.get(target);
					if(featMod == null)
						featModStore.put(target, featMod = new StringBuilder());
					featMod.append(featMod.length() == 0 ? mod.first : " + " + mod.first);
				}
			}
		}
		
		// second pass to apply modifiers
		for(int i = 0; i < features.size(); ++i) {
			featMod = featModStore.get(features.get(i).name);
			features.get(i).externalMods = (featMod == null ? "0" : featMod.toString());
		}
	}
	
	@Override
	public String toString() {
		return "UserCharacter [id=" + id + ", creatorName=" + creatorName + ", name=" + name + ", bio=" + bio + ", features=" + features
				+ "]";
	}
}
