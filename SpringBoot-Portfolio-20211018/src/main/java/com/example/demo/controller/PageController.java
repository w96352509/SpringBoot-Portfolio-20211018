package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
  
	@RequestMapping("/classify")
	public String getClassifyPage() {
		return "classify";
	}
	
	@RequestMapping("/home")
	public String gethome() {
		return "home";
	}
}
