package com.asm2.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asm2.entity.Company;
import com.asm2.entity.Cv;
import com.asm2.entity.Recruitment;
import com.asm2.entity.User;
import com.asm2.service.JobService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private JobService jobService;
	@Autowired
    private ServletContext servletContext;
	
	
	@RequestMapping("/uploadCv")
	public String uploadCv(@RequestParam("file") MultipartFile file,
							@RequestParam("userId") int userId,
							HttpServletRequest request,
							RedirectAttributes redirectAttributes) {
		User user = jobService.getUserById(userId);
		String fileName = user.getUserName()+".pdf";
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message","Please select a file to upload");
			return "redirect:/uploadStatus";
		}
		try {
			byte[] bytes = file.getBytes();
			String uploadDir = servletContext.getRealPath("/uploads");
			File uploadDirFile = new File(uploadDir);
		 
			if(!uploadDirFile.exists()) uploadDirFile.mkdirs();
			Path path = Paths.get(uploadDir, fileName);
			System.out.println("Tệp được lưu vào: "+path.toAbsolutePath());
			Files.write(path, bytes);
			
			Cv existingCv = jobService.getCvByUserId(userId);
			if(existingCv!=null) {
				existingCv.setFileName(fileName);
				jobService.addOrUpdateCv(existingCv);
			}else {
				Cv cv = new Cv();
				cv.setFileName(fileName);
				cv.setUser(user);
				jobService.addOrUpdateCv(cv);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("message","You successfully uploaded CV");
		return "redirect:"+referer;
	}
	
//	@PostMapping("/uploadCv")
//	public @ResponseBody String uploadCv(@RequestParam("file") MultipartFile file,
//							@RequestParam("userId") int userId,
//							RedirectAttributes redirectAttributes) {
//		System.out.println("UUUUUUUUUpppload");
//		User user = jobService.getUserById(userId);
//		String fileName = user.getUserName()+".pdf";
//		if(file.isEmpty()) {
//			System.out.println("No file selected.");
//			redirectAttributes.addFlashAttribute("message","Please select a file to upload");
//			return "No file selected.";
//		}
//		try {
//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(UPLOADED_FOLDER+fileName);
//			Files.createDirectories(path.getParent());
//			Files.write(path, bytes);
//			System.out.println("File saved: " + path.toString());
//			
//			Cv existingCv = jobService.getCvByUserId(userId);
//			if(existingCv!=null) {
//				existingCv.setFileName(fileName);
//				jobService.addOrUpdateCv(existingCv);
//			}else {
//				Cv cv = new Cv();
//				cv.setFileName(fileName);
//				cv.setUser(user);
//				jobService.addOrUpdateCv(cv);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//			return "Error uploading file";
//		}
//		return fileName;	
//	}
	
	@RequestMapping("/updateProfile")
	public String updateProfile(@ModelAttribute("user") User user,
								@RequestParam(value = "companyId", required = false) Integer companyId,
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
		if(companyId!=null) {			
			Company company = jobService.getCompanyById(companyId);
			company.setUser(user);
			jobService.updateCompany(company);
		}
		
		jobService.addOrUpdateUser(user);
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
							@RequestParam(name="pageSize", defaultValue = "5") int pageSize,
							@RequestParam(name="pageNumber", defaultValue = "1") int pageNumber,
							Model model) {
		User user = jobService.getUserByUsername(userName);
		List<Recruitment> recruitments = jobService.getRecruitments(user.getId(),pageSize, pageNumber);
		Long totalRecruitments = jobService.getTotalRecruitment(user.getId());
		int totalPage = (int)Math.ceil((double)totalRecruitments/pageSize);
		int pagePrev = pageNumber-1;
		int pageNext = pageNumber+1;
		model.addAttribute("user",user);
		model.addAttribute("recruitments",recruitments);
		model.addAttribute("pagePrev",pagePrev);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageNumber",pageNumber);
		return "post-list";
	}
	@RequestMapping("/deleteCv")
	public String deleteCv(@RequestParam("idCv") String idCv,
							HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		return "redirect:"+referer;
	}
	
}
