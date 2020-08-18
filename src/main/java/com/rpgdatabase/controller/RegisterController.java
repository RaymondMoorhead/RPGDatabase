package com.rpgdatabase.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		model.put("maxUsernameLength", service.maxUsernameLength);
		model.put("maxPasswordLength", service.maxPasswordLength);
		model.put("maxEmailLength", service.maxEmailLength);
		model.put("highestMaxLength", Math.max(Math.max(service.maxUsernameLength, service.maxPasswordLength), service.maxEmailLength));
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String showCharactersPage(ModelMap model, HttpSession session, @RequestParam String name, @RequestParam String password, @RequestParam String email){
		
		// cleanup
		model.remove("maxUsernameLength");
		model.remove("maxPasswordLength");
		model.remove("maxEmailLength");
		model.remove("highestMaxLength");
		
		User user = service.createUser(name, password, email);
		
		if (user == null) {
			model.put("errorMessage", service.getError());
			return "register";
		}
		
		session.setAttribute("curUser", user);
		model.put("name", name);
		
		return "list-user-characters";
	}
}
