package com.rpgdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpgdatabase.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService service;
	
	@GetMapping(value = "/login")
	public String showLoginPage(ModelMap model){
		return "login";
	}
	
	@PostMapping(value = "/login")
	public String showCharactersPage(ModelMap model, @RequestParam String name, @RequestParam String password){
		
		boolean isValidUser = service.validateUser(name, password);
		
		if (!isValidUser) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		
		model.put("name", name);
		
		return "list-user-characters";
	}
}
