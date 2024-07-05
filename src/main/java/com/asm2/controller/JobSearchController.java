package com.asm2.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm2.entity.ApplyPost;
import com.asm2.entity.Category;
import com.asm2.entity.Company;
import com.asm2.entity.Cv;
import com.asm2.entity.Recruitment;
import com.asm2.entity.Role;
import com.asm2.entity.User;
import com.asm2.service.JobService;

@Controller
public class JobSearchController {
	@Autowired
	private JobService jobService;

	@GetMapping("/")
	public String goHome(Model model, Principal principal) {
		List<Company> topCompanies = jobService.getTopCompanies();
		List<Recruitment> topRecruitments = jobService.getTopRecruitments();
		List<Category> topCategories = jobService.getTopCategories();
		Long totalCompany = jobService.getTotalCompany();
		Long totalRecruitment = jobService.getTotalRecruitment();
		Long totalUser = jobService.getTotalUser();
		model.addAttribute("companies", topCompanies);
		model.addAttribute("recruitments", topRecruitments);
		model.addAttribute("categories", topCategories);
		model.addAttribute("numberCompany", totalCompany);
		model.addAttribute("numberCandidate", totalUser);
		model.addAttribute("numberRecruitment", totalRecruitment);
		model.addAttribute("principal",principal);
		return "home";
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "admin-page";
	}

	@GetMapping("/showLoginPage")
	public String showLoginPage(Model model) {
		List<Role> roles = jobService.getRoles();
		model.addAttribute("roles", roles);
		return "login-page";
	}

	@GetMapping("/access-denied")
	public String deniedPage() {
		return "access-denied";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user, @RequestParam("role_id") int roleId,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String referer = request.getHeader("Referer");
		try {
			Role role = jobService.getRole(roleId);
			user.addRole(role);
			jobService.addOrUpdateUser(user);
			redirectAttributes.addFlashAttribute("msg_register_success", "success");
			return "redirect:" + referer;
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg_register_error", "fail");
			return "redirect:" + referer;
		}
	}
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping("/showProfile")
	public String profile(@RequestParam("username") String userName, Model model) {
		Company company = jobService.getCompanyByUsername(userName);
		User user = jobService.getUserByUsername(userName);
		Cv cv = jobService.getCvByUserId(user.getId());
		System.out.println("Đường dẫn tương đối: "+context.getRealPath("src/main/resources/uploads/"));
		model.addAttribute("company",company);
		model.addAttribute("user",user);
		model.addAttribute("Cv",cv);
		return "profile";
	}
	@RequestMapping("/listApplyPost")
	public String listApplyPost(@RequestParam("username") String userName,
									@RequestParam(name="pageNumber", defaultValue = "1") int pageNumber,
									@RequestParam(name="pageSize", defaultValue = "5") int pageSize,
									Model model) {
		User user = jobService.getUserByUsername(userName);
		List<ApplyPost> applyPosts = jobService.getApplyPostsByUserId(user.getId(), pageSize, pageNumber);
		Long totalApplyPost = jobService.getTotalApplyPostByUserId(user.getId());
		int totalPages = (int) Math.ceil((double)totalApplyPost/pageSize);
		int pagePre = pageNumber - 1;
		int pageNext = pageNumber +1;
		model.addAttribute("applyPosts",applyPosts);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("pagePre",pagePre);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("pageSize",pageSize);
		return "list-user";
	}
}
