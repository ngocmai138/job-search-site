package com.asm2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobSearchController {
	@GetMapping("/")
	public String goHome() {
		return "home";
	}
	@GetMapping("/access-denied")
	public String deniedPage() {
		return "access-denied";
	}
}
