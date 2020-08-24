package com.rpgdatabase.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rpgdatabase.utility.Pair;

public class Feat {
	public String name;
	public String description;
	public String selfRoll = null;
	// List<Pair< rollCommand, List<targetFeatures> > >
	public List<Pair<String, List<String> > > outRoll;
	
	public Feat(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		selfRoll = null;
		outRoll = new ArrayList<Pair<String, List<String> > >();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean canSelfRoll() {
		return selfRoll != null;
	}
	public String getSelfRoll() {
		return selfRoll;
	}
	public void setSelfRoll(String selfRoll) {
		this.selfRoll = selfRoll;
	}

	public List<Pair<String, List<String>>> getOutRoll() {
		return outRoll;
	}
	public void setOutRoll(List<Pair<String, List<String>>> outRoll) {
		this.outRoll = outRoll;
	}
	public void addOutRoll(String rollCommand, String... targetFeatures) {
		Pair<String, List<String> > pair = new Pair<String, List<String> >();
		pair.first = rollCommand;
		pair.second = new ArrayList<String>();
		Collections.addAll(pair.second, targetFeatures);
		outRoll.add(pair);
	}
}