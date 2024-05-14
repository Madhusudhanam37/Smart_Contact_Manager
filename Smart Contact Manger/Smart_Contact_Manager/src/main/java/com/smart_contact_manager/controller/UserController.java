package com.smart_contact_manager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart_contact_manager.entities.User;
import com.smart_contact_manager.repo.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		
		String userName = principal.getName();
		System.out.println("UserName"+userName);
		
		User user = userRepository.getUserByUserName(userName);
		System.out.println("User"+user);
		
		model.addAttribute("user", user);
		return "normal/user_dashboard";
	}
}
