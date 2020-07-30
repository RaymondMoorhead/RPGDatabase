package com.rpgdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpgdatabase.entity.User;
import com.rpgdatabase.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	UserService service;
	
	@GetMapping(value = "/register")
	public String showRegisterPage(ModelMap model){
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String showCharactersPage(ModelMap model, @RequestParam String name, @RequestParam String password, @RequestParam String email){
		
		User user = service.createUser(name, password, email);
		
		if (user == null) {
			model.put("errorMessage", service.getError());
			return "register";
		}
		
		model.put("name", name);
		
		return "list-user-characters";
	}
}
