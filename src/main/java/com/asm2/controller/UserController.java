package com.asm2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm2.entity.Company;
import com.asm2.entity.Recruitment;
import com.asm2.entity.Role;
import com.asm2.entity.User;
import com.asm2.service.JobService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private JobService jobService;
	@RequestMapping("/updateProfile")
	public String updateProfile(@ModelAttribute("user") User user,
								@RequestParam("companyId") int companyId,
								HttpServletRequest request,
								RedirectAttributes redirectAttributes) {
		User existUser = jobService.getUserById(user.getId());
		String referer = request.getHeader("Referer");
		if(existUser == null) {
			redirectAttributes.addFlashAttribute("update_user_message", "User not found.");
	        return "redirect:"+referer;
		}
		// Set lại các thuộc tính liên quan đến User cũ để không bị mất
		user.setRoles(existUser.getRoles());
		user.setCompany(existUser.getCompany());
		user.setCv(existUser.getCv());
		user.setRecruitmentsSave(existUser.getRecruitmentsSave());
		user.setFollowCompanies(existUser.getFollowCompanies());
		user.setApplyPosts(existUser.getApplyPosts());
		
		//Gắn user lại cho company
		Company company = jobService.getCompanyById(companyId);
		company.setUser(user);
		
		jobService.addOrUpdateUser(user);
		jobService.updateCompany(company);
		redirectAttributes.addFlashAttribute("update_user_message","Update User successful!!");
		return "redirect:"+referer;
	}
	@RequestMapping("/updateCompany")
	public String updateCompany(@ModelAttribute("company") Company company, 
									@RequestParam("userId") int userId,
									HttpServletRequest request,
									RedirectAttributes redirectAttributes) {
		User user = jobService.getUserById(userId);
		company.setUser(user);
		jobService.updateCompany(company);
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("update_company_message","Update Company successful!!");
		return "redirect:"+referer;
	}
	
	@GetMapping("/showDetailCompany")
	public String detailCompany(@RequestParam("username") String userName, Model model){
		Company company = jobService.getCompanyByUsername(userName);
		model.addAttribute("company",company);
		return "detail-company";
	}
	@RequestMapping("/showListPost")
	public String listPost(@RequestParam("username") String userName,
							Model model) {
		User user = jobService.getUserByUsername(userName);
		System.out.println("user iddddddddddddddd: "+user.getId());
		List<Recruitment> recruitments = jobService.getRecruitments(user.getId());
		System.out.println("number of recruitmentssssssssssss: "+recruitments.size());
		model.addAttribute("user",user);
		model.addAttribute("recruitments",recruitments);
		return "post-list";
	}
}
