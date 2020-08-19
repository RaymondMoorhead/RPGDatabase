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
import com.rpgdatabase.service.UserCharacterService;
import com.rpgdatabase.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	UserCharacterService charService;
	
	private final String confirmAccountDelete = "Are you sure you wish to delete your account? This action cannot be undone.";
	
    @ModelAttribute("curUser")
    public User getCurUser(HttpSession session) {
        return (User) session.getAttribute("curUser");
    }
	
//	@GetMapping(value = "/account-details")
//	public String showReVerifyPage(ModelMap model){
//		model.put("maxPasswordLength", service.maxPasswordLength);
//		return "re-verify-password";
//	}
	
	@GetMapping(value = "/account-details")
	public String showAccountDetailsPage(ModelMap model, HttpSession session){
		return "account-details";
	}
	
	@GetMapping(value = "/account-delete")
	public String startAccountDeletion(ModelMap model, HttpSession session){
		model.put("warningMessage", confirmAccountDelete);
		return "re-verify-password";
	}
	
	@PostMapping(value = "/account-delete")
	public String finishAccountDeletion(ModelMap model, HttpSession session, @RequestParam String password){
		User curUser = getCurUser(session);
		if(service.deleteuser(curUser.getUsername(), password)) {
			charService.deleteCharactersNoSafety(curUser.characterIds);
			return "redirect:/logout";
		}
		else {
			model.put("warningMessage", confirmAccountDelete);
			model.put("errorMessage", "Invalid Password");
			return "re-verify-password";
		}
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
