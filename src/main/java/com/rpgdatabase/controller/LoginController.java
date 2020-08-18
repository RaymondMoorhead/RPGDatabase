package com.rpgdatabase.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rpgdatabase.entity.User;
import com.rpgdatabase.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService service;
	
	@GetMapping(value = "/logout")
	public String showLoginPage(HttpSession session){
		session.removeAttribute("curUser");
		return "redirect:/login";
	}
	
	@GetMapping(value = "/login")
	public String showLoginPage(ModelMap model){
		model.put("maxUsernameLength", service.maxUsernameLength);
		model.put("maxPasswordLength", service.maxPasswordLength);
		model.put("highestMaxLength", Math.max(service.maxUsernameLength, service.maxPasswordLength));
		return "login";
	}
	
	@PostMapping(value = "/login")
	public ModelAndView showCharactersPage(ModelMap model, HttpSession session, @RequestParam String name, @RequestParam String password){
		
		// cleanup
		model.remove("maxUsernameLength");
		model.remove("maxPasswordLength");
		model.remove("highestMaxLength");
		
		User user = service.getUser(name, password);
		
		if (user == null) {
			System.out.println("Login failed");
			model.put("errorMessage", "Invalid Credentials");
			return new ModelAndView("redirect:/login");
		}
		session.setAttribute("curUser", user);
		
		System.out.println("Login Succeeded, user is: " + user.toString());
		
		//return "list-user-characters";
		return new ModelAndView("redirect:/list-user-characters");
	}
}
