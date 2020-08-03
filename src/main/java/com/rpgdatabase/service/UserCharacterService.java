package com.rpgdatabase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpgdatabase.entity.UserCharacter;
import com.rpgdatabase.repo.UserCharacterRepository;

@Service
public class UserCharacterService {
	@Autowired
	private UserCharacterRepository repository;
	private String error = null;
	
	public String getError() {
		String temp = error;
		error = null;
		return temp;
	}
	
	public UserCharacter createCharacter(String username, String characterName) {

		UserCharacter character = new UserCharacter(username, characterName);
		repository.save(character);
		return character;
	}
	
	public void deleteCharacter(String id) {

		repository.deleteById(id);
	}
	
	public UserCharacter getCharacter(String id) {

		Optional<UserCharacter> result = repository.findById(id);
		if(result.isPresent())
			return result.get();
		else
			return null;
	}
	
	public List<UserCharacter> getCharacters(String creatorName) {

		return repository.findAllByCreatorName(creatorName);
	}
}
