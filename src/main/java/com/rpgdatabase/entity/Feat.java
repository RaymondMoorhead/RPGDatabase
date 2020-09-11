package com.rpgdatabase.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.rpgdatabase.utility.Pair;
import com.rpgdatabase.utility.dice.RollCommand;

public class Feat {
	public String name;
	public String description;
	public String selfRoll = null;
	@Transient
	public String externalMods = null;
	@Transient
	private RollCommand roller = null;
	// List<Pair< rollCommand, List<targetFeatures> > >
	public List<Pair<String, List<String> > > outRoll;
	
	public Feat(String name, String description, String selfRoll) {
		super();
		this.name = name;
		this.description = description;
		this.selfRoll = selfRoll;
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
		return (selfRoll != null) && !selfRoll.isBlank();
	}
	public String getSelfRoll() {
		return selfRoll;
	}
	public void setSelfRoll(String selfRoll) {
		this.selfRoll = selfRoll;
	}
	
	public String getExternalMods() {
		return externalMods;
	}
	public void setExternalMods(String externalMods) {
		this.externalMods = externalMods;
	}
	
	public boolean compile() {
		if(!canSelfRoll())
			return false;
		
		roller = new RollCommand();
		return roller.generate(externalMods == null ? selfRoll : selfRoll + externalMods) != null;
	}
	
	public boolean isCompiled() {
		return roller != null;
	}
	
	public int roll() throws Exception {
		if(!isCompiled()) {
			if(!compile())
				throw new Exception("Feat.compile did not return successfully");
		}
			
		return roller.execute();
	}

	public List<Pair<String, List<String>>> getOutRoll() {
		return outRoll;
	}
	public void setOutRoll(List<Pair<String, List<String>>> outRoll) {
		this.outRoll = outRoll;
	}
	public Feat addOutRoll(String rollCommand, String... targetFeatures) {
		Pair<String, List<String> > pair = new Pair<String, List<String> >();
		pair.first = rollCommand;
		pair.second = new ArrayList<String>();
		Collections.addAll(pair.second, targetFeatures);
		outRoll.add(pair);
		return this;
	}
}