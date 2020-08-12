package com.rpgdatabase.controller;

import java.util.List;

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

@Controller
public class UserCharacterController {
	@Autowired
	UserCharacterService service;
	@Autowired
    private User curUser;
	
    @ModelAttribute("curUser")
    public User getCurUser() {
        return this.curUser;
    }
	
	@GetMapping(value = "/list-user-characters")
	public String showCharactersPage(ModelMap model){
		List<UserCharacter> characters = service.getCharacters(curUser.getUsername());
		model.put("characters", characters);
		return "list-user-characters";
	}
	
	@GetMapping(value = "/add-user-character")
	public String showAddCharacterPage(){
		return "add-user-character";
	}
	
	@PostMapping(value = "/add-user-character")
	public String addCharacter(@RequestParam String characterName, @RequestParam String characterBio){
		service.createCharacter(curUser.getUsername(), characterName, characterBio);
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
		model.put("character", character);
		return "view-user-character";
	}
	
	@GetMapping(value = "/add-user-character-feat")
	public String addCharacterFeat(ModelMap model, @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		character.features.put("NewFeat", "");
		model.put("character", character);
		model.put("editFeat", "NewFeat");
		return "view-user-character";
	}
	
	@GetMapping(value = "/edit-user-character-name")
	public String editCharacterName(ModelMap model,  @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
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
	public String editCharacterFeat(ModelMap model,  @RequestParam String id, @RequestParam String featName){
		UserCharacter character = service.getCharacter(id);
		model.put("character", character);
		model.put("editFeat", featName);
		return "view-user-character";
	}
	
	@PostMapping(value = "/edit-user-character-feat")
	public String saveCharacterFeat(ModelMap model, @RequestParam String id, @RequestParam String oldName, @RequestParam String name, @RequestParam String desc){
		UserCharacter character = service.getCharacter(id);
		character.features.remove(oldName);
		character.features.put(name, desc);
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	@GetMapping(value = "/delete-user-character-feat")
	public String deleteCharacterFeat(ModelMap model, @RequestParam String id, @RequestParam String featName){
		UserCharacter character = service.getCharacter(id);
		character.features.remove(featName);
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
//	@PostMapping(value = "/edit-character")
//	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password, @RequestParam String email){
//		
//		User user = service.createUser(name, password, email);
//		
//		if (user == null) {
//			model.put("errorMessage", service.getError());
//			return "register";
//		}
//		
//		model.put("name", name);
//		
//		return "welcome";
//	}
}
