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

import com.asm2.entity.Category;
import com.asm2.entity.Company;
import com.asm2.entity.Recruitment;
import com.asm2.service.JobService;

@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {
	@Autowired
	private JobService jobService;
	@RequestMapping("/detail")
	public String showDetail(@RequestParam("recruitmentId") int recruitmentId,
								Model model) {
		Recruitment recruitment = jobService.getRecruitment(recruitmentId);
		model.addAttribute("recruitment",recruitment);
		return "detail-post";
	}
	@RequestMapping("/showDetailCompany")
	public String detailCompany(@RequestParam("companyId") int companyId, Model model){
		Company company = jobService.getCompanyById(companyId);
		model.addAttribute("company",company);
		return "detail-company";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("recruitmentId") int recruitmentId,
							HttpServletRequest request,
							RedirectAttributes redirectAttributes) {
		jobService.deleteRecruitment(recruitmentId);
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("success_msg","success delete");
		return "redirect:"+referer;
	}
	@RequestMapping("/editpost")
	public String editpost(@RequestParam("recruitmentId") int recruitmentId,
							Model model) {
		Recruitment recruitment = jobService.getRecruitment(recruitmentId);
		List<Category> categories = jobService.getCategories();
		model.addAttribute("recruitment",recruitment);
		model.addAttribute("categories",categories);
		return "edit-job";
	}
	@RequestMapping("/edit")
	public String edit(@ModelAttribute("recruitment") Recruitment recruitment,
						Model model,
						HttpServletRequest request,
						RedirectAttributes redirectAttributes) {
		jobService.addOrUpdateRecruitment(recruitment);
		
		String referer = request.getHeader("Referer");
		model.addAttribute(recruitment);
		redirectAttributes.addFlashAttribute("success_msg","success");
		return "redirect:"+referer;
	}
}
