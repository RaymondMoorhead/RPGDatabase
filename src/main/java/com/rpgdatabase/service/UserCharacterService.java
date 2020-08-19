package com.rpgdatabase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpgdatabase.entity.User;
import com.rpgdatabase.entity.UserCharacter;
import com.rpgdatabase.repo.UserCharacterRepository;
import com.rpgdatabase.repo.UserRepository;
import com.rpgdatabase.utility.HtmlUtility;

@Service
public class UserCharacterService {
	@Autowired
	private UserCharacterRepository charRepository;
	@Autowired
	private UserRepository userRepository;
	private String error = null;
	
	public String getError() {
		String temp = error;
		error = null;
		return temp;
	}
	
	public UserCharacter createCharacter(String username, String characterName, String characterBio) {

		UserCharacter character = new UserCharacter(username, characterName, characterBio);
		charRepository.insert(character);
		
		User user = userRepository.findByUsername(username);
		user.characterIds.add(character.getId());
		userRepository.save(user);
		
		return character;
	}
	
	public void updateCharacter(UserCharacter character) {
		charRepository.save(character);
	}
	
	public void deleteCharacter(String id) {

		Optional<UserCharacter> character = charRepository.findById(id);
		charRepository.deleteById(id);
		
		User user = userRepository.findByUsername(character.get().getCreatorName());
		user.characterIds.remove(character.get().getId());
		userRepository.save(user);
	}
	
	// does not remove the reference to the character from the User, so
	// this method should only be called when the User has already been
	// deleted
	public void deleteCharactersNoSafety(List<String> ids) {
		for(String id : ids)
			charRepository.deleteById(id);
	}
	
	public UserCharacter getCharacter(String id) {

		Optional<UserCharacter> result = charRepository.findById(id);
		if(result.isPresent())
			return result.get();
		else
			return null;
	}
	
	public List<UserCharacter> getCharacters(String creatorName) {

		return charRepository.findAllByCreatorName(creatorName);
	}
}
