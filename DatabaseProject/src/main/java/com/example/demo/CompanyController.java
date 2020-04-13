package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompanyController {
	
	@RequestMapping("/home")
	public String login() {
		return "login";
	}
	
}
