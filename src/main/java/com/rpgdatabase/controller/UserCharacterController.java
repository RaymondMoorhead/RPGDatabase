package com.rpgdatabase.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpgdatabase.entity.User;
import com.rpgdatabase.entity.UserCharacter;
import com.rpgdatabase.service.UserCharacterService;
import com.rpgdatabase.utility.HtmlUtility;

@Controller
public class UserCharacterController {
	@Autowired
	UserCharacterService service;

    @ModelAttribute("curUser")
    public User getCurUser(HttpSession session) {
        return (User) session.getAttribute("curUser");
    }
	
	@GetMapping(value = "/list-user-characters")
	public String showCharactersPage(ModelMap model, HttpSession session){
		List<UserCharacter> characters = service.getCharacters(getCurUser(session).getUsername());
		if(characters != null) {
			for(UserCharacter character : characters)
				formatBio(character);
			model.put("characters", characters);
		}
		return "list-user-characters";
	}
	
	@GetMapping(value = "/add-user-character")
	public String showAddCharacterPage(){
		return "add-user-character";
	}
	
	@PostMapping(value = "/add-user-character")
	public String addCharacter(HttpSession session, @RequestParam String characterName, @RequestParam String characterBio){
		service.createCharacter(getCurUser(session).getUsername(), characterName, characterBio);
		return "redirect:list-user-characters";
	}
	
	@GetMapping(value = "/delete-user-character")
	public String deleteCharacter(ModelMap model, @RequestParam String id){
		service.deleteCharacter(id);
		return "redirect:list-user-characters";
	}
	
	@GetMapping(value = "/view-user-character")
	public String viewCharacter(ModelMap model, @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		formatBio(character);
		formatFeats(character);
		model.put("character", character);
		return "view-user-character";
	}
	
	@GetMapping(value = "/add-user-character-feat")
	public String addCharacterFeat(ModelMap model, @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		formatBio(character);
		formatFeats(character);
		character.addFeature("", "", "");
		model.put("character", character);
		model.put("editFeat", character.features.size() - 1);
		return "view-user-character";
	}
	
	@GetMapping(value = "/edit-user-character-name")
	public String editCharacterName(ModelMap model,  @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		formatBio(character);
		formatFeats(character);
		model.put("character", character);
		model.put("editName", true);
		return "view-user-character";
	}
	
	@PostMapping(value = "/edit-user-character-name")
	public String saveCharacterName(ModelMap model,  @RequestParam String id, @RequestParam String name){
		UserCharacter character = service.getCharacter(id);
		character.setName(name);
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	@GetMapping(value = "/edit-user-character-bio")
	public String editCharacterBio(ModelMap model,  @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		formatFeats(character);
		model.put("character", character);
		model.put("editBio", true);
		return "view-user-character";
	}
	
	@PostMapping(value = "/edit-user-character-bio")
	public String saveCharacterBio(ModelMap model,  @RequestParam String id, @RequestParam String bio){
		UserCharacter character = service.getCharacter(id);
		character.setBio(bio);
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	@GetMapping(value = "/edit-user-character-feat")
	public String editCharacterFeat(ModelMap model,  @RequestParam String id, @RequestParam int index){
		UserCharacter character = service.getCharacter(id);
		formatBio(character);
		formatFeats(character, index);
		model.put("character", character);
		model.put("editFeat", index);
		return "view-user-character";
	}
	
	@PostMapping(value = "/edit-user-character-feat")
	public String saveCharacterFeat(ModelMap model, @RequestParam String id, @RequestParam int index, @RequestParam String name, @RequestParam String desc, @RequestParam String selfRoll){
		UserCharacter character = service.getCharacter(id);
		
		if(index == character.features.size())
			character.addFeature(name, desc, selfRoll); // must be a new feature
		else
			character.changeFeature(index, name, desc, selfRoll);
		
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	@GetMapping(value = "/delete-user-character-feat")
	public String deleteCharacterFeat(ModelMap model, @RequestParam String id, @RequestParam int index){
		UserCharacter character = service.getCharacter(id);
		character.removeFeature(index);
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	// PRIVATE HELPER METHODS
	
	private void formatBio(UserCharacter character) {
		character.bio = HtmlUtility.formatForTextArea(character.bio);
	}
	
	private void formatFeats(UserCharacter character) {
		formatFeats(character, -1);
	}
	
	private void formatFeats(UserCharacter character, int exceptThisFeatIndex) {
		character.compileFeatureMods();
		for(int i = 0; i < character.features.size(); ++i){
			// ignore formatting a feat (because it' being edited)
			if(i == exceptThisFeatIndex)
				continue;

			// handle feat formatting
			character.features.get(i).description = HtmlUtility.formatForTextArea(character.features.get(i).getDescription());
		}
		

	}
}
