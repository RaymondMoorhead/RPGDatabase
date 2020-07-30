package com.rpgdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.rpgdatabase.service.UserCharacterService;

@Controller
public class UserCharacterController {
	@Autowired
	UserCharacterService service;
	
	@GetMapping(value = "/list-user-characters")
	public String showCharactersPage(ModelMap model){
		String name = (String) model.get("name");
		model.put("characters", service.getCharacters(name));
		return "list-user-characters";
	}
	
	@GetMapping(value = "/delete-user-character")
	public String deleteCharacter(ModelMap model){
		// TODO finish this
		return "list-user-characters";
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
