package com.smart_contact_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart_contact_manager.entities.User;
import com.smart_contact_manager.helper.Message;
import com.smart_contact_manager.repo.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String homeController(Model model) {
		
		model.addAttribute("title", "Home-Smart Contact Manager");
		
		return "home";
	}
	
	@GetMapping("/about")
	public String aboutController(Model model) {
		
		model.addAttribute("title", "About-Smart Contact Manager");
		
		return "about";
	}
	
	@GetMapping("/signup")
	public String signupController(Model model) {
		
		model.addAttribute("title", "Register-Smart Contact Manager");
		
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	@PostMapping(value="/do_register")
	public String resisterUserController(@Valid @ModelAttribute("user") User user,BindingResult bindingResult,@RequestParam(value = "agreement",defaultValue = "false") boolean agreement,Model model,HttpSession session) {
		
		try {
			if(!agreement) {
				System.out.println("You have not agree the terms and conditions");
				throw new Exception("You have not agree the terms and conditions");
			}
			
			if(bindingResult.hasErrors()) {
				System.out.println("ERROR"+bindingResult.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement"+agreement);
			System.out.println("USER "+user);
			
			User result = this.userRepository.save(user);
			
			model.addAttribute("user", new User());
			
			session.setAttribute("message", new Message("Successfully Register !!", "alert-success"));
			return "signup";
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong"+e.getMessage(), "alert-danger"));
			return "signup";
		}
		
		
	}

	@GetMapping("/signin")
	public String customSigninController(Model model) {
		
		model.addAttribute("title", "Login-Smart Contact Manager");
		return "login";
	}

}
