package com.rpgdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rpgdatabase.entity.User;
import com.rpgdatabase.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	@Autowired
    private User curUser;
	
    @ModelAttribute("curUser")
    public User getCurUser() {
        return this.curUser;
    }
	
//	@GetMapping(value = "/account-details")
//	public String showReVerifyPage(ModelMap model){
//		model.put("maxPasswordLength", service.maxPasswordLength);
//		return "re-verify-password";
//	}
	
	@GetMapping(value = "/account-details")
	public String showReVerifyPage(ModelMap model){
		System.out.println("Starting account-details, user is: " + (curUser == null ? "NULL" : curUser.toString()));
		return "account-details";
	}
	
//	@PostMapping(value = "/account-details")
//	public String showAccountDetailsPage(ModelMap model, @RequestParam String password){
//		
//		User user = service.getUser(model.get("name").toString(), password);
//		
//		if (user == null) {
//			model.put("errorMessage", "Invalid Credentials");
//			return "re-verify-password";
//		}
//		
//		model.put("email", user.email);
//		
//		return "account-details";
//	}
}
