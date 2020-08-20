package com.rpgdatabase.entity;

public class Feat {
	public String name;
	public String description;
//	public String rollCommand;
//	public List<Pair<RollCommand, List<String>> > modTargets
	
	public Feat(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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
}