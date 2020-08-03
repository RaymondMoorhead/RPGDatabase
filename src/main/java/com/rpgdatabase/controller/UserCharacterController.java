package com.rpgdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpgdatabase.entity.User;
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
		model.put("characters", service.getCharacters(curUser.username));
		return "list-user-characters";
	}
	
	@GetMapping(value = "/add-user-character")
	public String showAddCharacterPage(){
		return "add-user-character";
	}
	
	@PostMapping(value = "/add-user-character")
	public String addCharacter(@RequestParam String characterName){
		service.createCharacter(curUser.username, characterName);
		return "redirect:list-user-characters";
	}
	
	@GetMapping(value = "/delete-user-character")
	public String deleteCharacter(ModelMap model, @RequestParam String id){
		service.deleteCharacter(id);
		return "redirect:list-user-characters";
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
