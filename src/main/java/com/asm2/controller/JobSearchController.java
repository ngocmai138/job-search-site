package com.asm2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.asm2.entity.Company;
import com.asm2.service.JobService;

@Controller
public class JobSearchController {
	@Autowired
	private JobService jobService;
	@GetMapping("/")
	public String goHome(Model model) {
		List<Company> companies = jobService.getCompanies();
		model.addAttribute("companies",companies);
		return "home";
	}
	@GetMapping("/access-denied")
	public String deniedPage() {
		return "access-denied";
	}
}
