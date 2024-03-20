package com.smart_contact_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class User_Controller {

	@RequestMapping("/index")
	public String dashboard() {
		
		return "normal/user_dashboard";
	}
}
