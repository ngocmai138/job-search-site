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

import com.asm2.entity.ApplyPost;
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
								@RequestParam(name="pageSize", defaultValue = "5") int pageSize,
								@RequestParam(name="pageNumber", defaultValue = "1") int pageNumber,
								Model model) {
		Recruitment recruitment = jobService.getRecruitment(recruitmentId);
		List<ApplyPost> applyPosts = jobService.getApplyPostsByRecruitmentId(recruitmentId, pageSize, pageNumber);
		Long totalApplyPosts = jobService.getTotalApplyPostByRecruitmentId(recruitmentId);
		model.addAttribute("applyPosts",applyPosts);
		model.addAttribute("totalApplyPosts",totalApplyPosts);
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
		jobService.saveOrUpdateRecruitment(recruitment);
		
		String referer = request.getHeader("Referer");
		model.addAttribute(recruitment);
		redirectAttributes.addFlashAttribute("success_msg","success");
		return "redirect:"+referer;
	}
	@RequestMapping("/post")
	public String post(@RequestParam("username") String userName,
						Model model) {
		Company company = jobService.getCompanyByUsername(userName);
		List<Category> categories = jobService.getCategories();
		model.addAttribute("company",company);
		model.addAttribute("categories",categories);
		return "post-job";
	}
	@RequestMapping("/add")
	public String add(@ModelAttribute("recruitment") Recruitment recruitment,
						HttpServletRequest request,
						RedirectAttributes redirectAttributes) {
		jobService.saveOrUpdateRecruitment(recruitment);
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("msg_success","success");
		return "redirect:"+referer;
	}
	@RequestMapping("/searchTitle")
	public String searchTitle(@RequestParam("keySearch") String keyword,
			@RequestParam(name="pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name="pageNumber", defaultValue = "1") int pageNumber,
							Model model) {
		Long totalCompany = jobService.getTotalCompany();
		Long totalRecruitment = jobService.getTotalRecruitment();
		Long totalUser = jobService.getTotalUser();
		List<Recruitment> recruitments = jobService.searchRecruitmentsByTitle(keyword,pageSize,pageNumber);
		Long totalRecruitments = jobService.getTotalSearchRecruitmentsByTitle(keyword);
		int totalPages = (int) Math.ceil((double) totalRecruitments/pageSize);
		int pagePrev = pageNumber-1;
		int pageNext = pageNumber+1;
		model.addAttribute("pagePrev",pagePrev);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("numberCompany", totalCompany);
		model.addAttribute("numberCandidate", totalUser);
		model.addAttribute("numberRecruitment", totalRecruitment);
		model.addAttribute("totalRecruitments", totalRecruitments);
		model.addAttribute("recruitments",recruitments);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("keySearch",keyword);
		return "result-search";
	}
	@RequestMapping("/searchCompanyName")
	public String searchCompanyName(@RequestParam("keySearch") String keyword,
			@RequestParam(name="pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name="pageNumber", defaultValue = "1") int pageNumber,
									Model model) {
		Long totalCompany = jobService.getTotalCompany();
		Long totalRecruitment = jobService.getTotalRecruitment();
		Long totalUser = jobService.getTotalUser();
		List<Recruitment> recruitments = jobService.searchRecruitmentByCompany(keyword,pageSize,pageNumber);
		Long totalRecruitments = jobService.getTotalSearchRecruitmentsByCompany(keyword);
		int totalPages = (int) Math.ceil((double) totalRecruitments/pageSize);
		int pagePrev = pageNumber-1;
		int pageNext = pageNumber+1;
		model.addAttribute("pagePrev",pagePrev);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("recruitments",recruitments);
		model.addAttribute("numberCompany", totalCompany);
		model.addAttribute("numberCandidate", totalUser);
		model.addAttribute("numberRecruitment", totalRecruitment);
		model.addAttribute("totalRecruitments", totalRecruitments);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("keySearch",keyword);
		return "result-search-company";
	}
	@RequestMapping("/searchAddress")
	public String searchAddress(@RequestParam("keySearch") String keyword,
			@RequestParam(name="pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name="pageNumber", defaultValue = "1") int pageNumber,
									Model model) {
		Long totalCompany = jobService.getTotalCompany();
		Long totalRecruitment = jobService.getTotalRecruitment();
		Long totalUser = jobService.getTotalUser();
		List<Recruitment> recruitments = jobService.searchRecruitmentByAddress(keyword,pageSize,pageNumber);
		Long totalRecruitments = jobService.getTotalSearchRecruitmentsByAddress(keyword);
		int totalPages = (int) Math.ceil((double) totalRecruitments/pageSize);
		int pagePrev = pageNumber-1;
		int pageNext = pageNumber+1;
		model.addAttribute("pagePrev",pagePrev);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("recruitments",recruitments);
		model.addAttribute("numberCompany", totalCompany);
		model.addAttribute("numberCandidate", totalUser);
		model.addAttribute("numberRecruitment", totalRecruitment);
		model.addAttribute("totalRecruitments", totalRecruitments);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("keySearch",keyword);
		return "result-search-address";
	}
}
