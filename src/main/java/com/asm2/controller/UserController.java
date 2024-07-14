package com.asm2.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.asm2.entity.ApplyPost;
import com.asm2.entity.Company;
import com.asm2.entity.Cv;
import com.asm2.entity.FollowCompany;
import com.asm2.entity.Recruitment;
import com.asm2.entity.SaveJob;
import com.asm2.entity.User;
import com.asm2.entity.VerificationToken;
import com.asm2.mail.RegistrationService;
import com.asm2.service.JobService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JobService jobService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private RegistrationService registrationService;

	@RequestMapping("/uploadCv")
	@ResponseBody
	public String uploadCv(@RequestParam("file") MultipartFile file, @RequestParam("userId") int userId) {
		User user = jobService.getUserById(userId);
		String fileName = user.getUserName() + "_cv.pdf";
		if (file.isEmpty()) {
			System.out.println("No file selected.");
			return "No file selected.";
		}
		try {
			byte[] bytes = file.getBytes();
			String uploadDir = servletContext.getRealPath("/uploads");
			File uploadDirFile = new File(uploadDir);
			if (!uploadDirFile.exists())
				uploadDirFile.mkdirs();
			Path path = Paths.get(uploadDir, fileName);
			Files.write(path, bytes);
			System.out.println("File saved: " + path.toString());

			Cv existingCv = jobService.getCvByUserId(userId);
			if (existingCv != null) {
				existingCv.setFileName(fileName);
				jobService.saveOrUpdateCv(existingCv);
			} else {
				Cv cv = new Cv();
				cv.setFileName(fileName);
				cv.setUser(user);
				jobService.saveOrUpdateCv(cv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error uploading file";
		}
		return fileName;
	}

	@RequestMapping("/uploadImage")
	public @ResponseBody String uploadImage(@RequestParam("file") MultipartFile file,
			@RequestParam("userId") int userId) {
		User user = jobService.getUserById(userId);
		if (user == null)
			return "user not found";
		String fileName = "person_" + user.getId() + ".jpg";

		if (file.isEmpty())
			return "Error";
		try {
			byte[] bytes = file.getBytes();
			String uploadDir = servletContext.getRealPath("/uploads");
			File uploadDirFile = new File(uploadDir);
			if (!uploadDirFile.exists())
				uploadDirFile.mkdirs();
			Path path = Paths.get(uploadDir, fileName);
			Files.write(path, bytes);
			System.out.println("Photo saved: " + path);
			user.setImage(fileName);
			jobService.addOrUpdateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
		return fileName;
	}

	@RequestMapping("/updateProfile")
	public String updateProfile(@ModelAttribute("user") User user,
			@RequestParam(value = "companyId", required = false) Integer companyId, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		User existUser = jobService.getUserById(user.getId());
		String referer = request.getHeader("Referer");
		if (existUser == null) {
			redirectAttributes.addFlashAttribute("update_user_message", "User not found.");
			return "redirect:" + referer;
		}
		// Set lại các thuộc tính liên quan đến User cũ để không bị mất
		user.setRoles(existUser.getRoles());
		user.setCompany(existUser.getCompany());
		user.setCv(existUser.getCv());
		user.setSaveJobs(existUser.getSaveJobs());
		user.setFollowCompanies(existUser.getFollowCompanies());
		user.setApplyPosts(existUser.getApplyPosts());

		// Gắn user lại cho company
		if (companyId != null) {
			Company company = jobService.getCompanyById(companyId);
			company.setUser(user);
			jobService.updateCompany(company);
		}

		jobService.addOrUpdateUser(user);
		redirectAttributes.addFlashAttribute("update_user_message", "Update User successful!!");
		return "redirect:" + referer;
	}

	@RequestMapping("/updateCompany")
	public String updateCompany(@ModelAttribute("company") Company company, @RequestParam("userId") int userId,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		User user = jobService.getUserById(userId);
		company.setUser(user);
		jobService.updateCompany(company);
		String referer = request.getHeader("Referer");
		redirectAttributes.addFlashAttribute("update_company_message", "Update Company successful!!");
		return "redirect:" + referer;
	}

	@GetMapping("/showDetailCompany")
	public String detailCompany(@RequestParam(value = "username", required = false) String userName,
			@RequestParam(value = "companyId", required = false) Integer companyId, 
			HttpServletRequest request,
			Model model) {
		Company company = null; 
		if (userName != null) {
			company = jobService.getCompanyByUsername(userName);
		}
		if(companyId !=null) {
			company = jobService.getCompanyById(companyId);
		}
		if(company == null) {
			String referer = request.getHeader("Referer");
			return "redirect:"+referer;
		}
		model.addAttribute("company", company);
		return "detail-company";
	}

	@RequestMapping("/showListPost")
	public String listPost(@RequestParam("username") String userName,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber, Model model) {
		User user = jobService.getUserByUsername(userName);
		List<Recruitment> recruitments = jobService.getRecruitments(user.getId(), pageSize, pageNumber);
		Long totalRecruitments = jobService.getTotalRecruitment(user.getId());
		int totalPage = (int) Math.ceil((double) totalRecruitments / pageSize);
		int pagePrev = pageNumber - 1;
		int pageNext = pageNumber + 1;
		model.addAttribute("user", user);
		model.addAttribute("recruitments", recruitments);
		model.addAttribute("pagePrev", pagePrev);
		model.addAttribute("pageNext", pageNext);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNumber", pageNumber);
		return "post-list";
	}

	@RequestMapping("/deleteCv")
	public String deleteCv(@RequestParam("idCv") String idCv, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping("/applyJob")
	@ResponseBody
	public String applyJob(@RequestParam("userName") String userName,
			@RequestParam("idRe") int recruitmentId, @RequestParam("text") String text,
			@RequestParam("useExistingCV") boolean useExistingCV,
			@RequestParam(value = "file", required = false) MultipartFile file, Principal principal) {
		System.out.println("userName: "+userName);
		if (principal == null)
			return "false";
		User user = jobService.getUserByUsername(userName);
		Recruitment recruitment = jobService.getRecruitment(recruitmentId);
		ApplyPost existingApplyPost = jobService.getApplyPost(user, recruitment);
		if (existingApplyPost != null)
			return "exist";
		ApplyPost applyPost = new ApplyPost();
		applyPost.setUser(user);
		applyPost.setRecruitment(recruitment);
		applyPost.setText(text);
		if (useExistingCV) {
			String existingCV = user.getCv().getFileName();
			applyPost.setNameCv(existingCV);
		} else {
			if (file != null && !file.isEmpty()) {
				String fileName = user.getUserName() + "_cv.pdf";
				try {
					String uploadDir = servletContext.getRealPath("/uploads");
					Path path = Paths.get(uploadDir, fileName);
					Files.write(path, file.getBytes());
					applyPost.setNameCv(fileName);
					System.out.println("File saved: " + path);
				} catch (Exception e) {
					e.printStackTrace();
					return "false";
				}
			}
		}
		jobService.saveOrUpdateApplyPost(applyPost);
		return "true";
	}

	@RequestMapping("/saveJob")
	@ResponseBody
	public String saveJob(@RequestParam("idRe") int idRe, @RequestParam("userName") String userName,
			Principal principal) {
		if (principal == null)
			return "false";
		User user = jobService.getUserByUsername(userName);
		SaveJob existingSaveJob = jobService.getSaveJob(idRe, user.getId());
		if (existingSaveJob != null)
			return "exist";
		jobService.createSaveJob(idRe, user.getId());
		return "true";
	}

	@RequestMapping("/confirmAccount")
	public String confirmAccount(@RequestParam("email") String email, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String referer = request.getHeader("Referer");
		User user = jobService.getUserByEmail(email);
		registrationService.sendConfirmationEmail(user, request);
		redirectAttributes.addFlashAttribute("confirm_await", "success");
		return "redirect:" + referer;
	}

	@RequestMapping("/verify")
	public String verifyAccount(@RequestParam("token") String token, RedirectAttributes redirectAttributes,
			HttpServletRequest request, Model model) {
		VerificationToken verificationToken = jobService.getVerificationByToken(token);
		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		if (verificationToken == null) {
			model.addAttribute("message", "Invalid verification token");
			System.out.println("verification token is null");
			return "redirect:" + appUrl;
		}
		User user = verificationToken.getUser();
		if (user == null) {
			model.addAttribute("message", "Invalid verification token");
			System.out.println("user is null");
			return "redirect:" + appUrl;
		}
		user.setStatus(1);
		jobService.addOrUpdateUser(user);
		System.out.println("Account verified successfully");
		model.addAttribute("message", "Account verified successfully");

		return "redirect:" + appUrl;
	}
	
	@RequestMapping("/followCompany")
	@ResponseBody
	public String followCompany(@RequestParam("idCompany") int companyId,
									@RequestParam("username") String userName,
									Principal principal) {
		if(principal == null) return "false";
		User user = jobService.getUserByUsername(userName);
		Company company = jobService.getCompanyById(companyId);
		FollowCompany existFollowCompany = jobService.getFollowCompany(user, company);
		if(existFollowCompany !=null) return "exist";
		jobService.createFollowCompany(user, company);
		return "true";
	}
	@RequestMapping("/deleteFollowCompany")
	@ResponseBody
	public String deleteFollowCompany(@RequestParam("idFC") int followCompanyId,
										Principal principal) {
		if(principal == null) return "false";
		FollowCompany followCompany = jobService.getFollowCompanyById(followCompanyId);
		if(followCompany ==null) return "deleted";
		jobService.deleteFollowCompany(followCompanyId);
		return "true";
	}
	@RequestMapping("/deleteApplyPost")
	@ResponseBody
	public String deleteApplyPost(@RequestParam("idAP") int applyPostId,
			Principal principal) {
		if(principal == null) return "false";
		ApplyPost applyPost = jobService.getApplyPostById(applyPostId);
		if(applyPost ==null) return "deleted";
		jobService.deleteApplyPost(applyPostId);
		return "true";
	}
	@RequestMapping("/getListCompany")
	public String getListCompany(@RequestParam("username") String userName,
									@RequestParam(value="pageSize", defaultValue = "2") int pageSize,
									@RequestParam(value="pageNumber", defaultValue = "1") int pageNumber,
									Model model) {
		User user = jobService.getUserByUsername(userName);
		List<FollowCompany> followCompanies = jobService.getFollowCompanies(user.getId(), pageSize, pageNumber);
		Long totalFollowCompanies = jobService.getTotalFollowCompanies(user.getId());
		int totalPages = (int)Math.ceil((double)totalFollowCompanies/pageSize);
		int pageNext = pageNumber+1;
		int pagePrev = pageNumber-1;
		model.addAttribute("totalFollowCompanies",totalFollowCompanies);
		model.addAttribute("followCompanies",followCompanies);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("pagePrev",pagePrev);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageNumber",pageNumber);
		return "list-follow-company";
	}
	@RequestMapping("/getListApply")
	public String getListApply(@RequestParam("username") String userName,
			@RequestParam(value="pageSize", defaultValue = "2") int pageSize,
			@RequestParam(value="pageNumber", defaultValue = "1") int pageNumber,
			Model model) {
		User user = jobService.getUserByUsername(userName);
		List<ApplyPost> applyPosts = jobService.getApplyPostsByCandidateId(user.getId(), pageSize, pageNumber);
		Long totalApplyPosts = jobService.getTotalApplysPostsByCandidateId(user.getId());
		int totalPages = (int)Math.ceil((double)totalApplyPosts/pageSize);
		int pageNext = pageNumber+1;
		int pagePrev = pageNumber-1;
		
		model.addAttribute("totalApplyPosts",totalApplyPosts);
		model.addAttribute("applyPosts",applyPosts);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("pagePrev",pagePrev);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageNumber",pageNumber);
		return "list-apply-job";
	}
	@RequestMapping("/companyPost")
	public String companyPost(@RequestParam("companyId") int companyId,
								@RequestParam(value="pageSize", defaultValue = "2") int pageSize,
								@RequestParam(value="pageNumber", defaultValue = "1") int pageNumber,
								Model model) {
		Company company = jobService.getCompanyById(companyId);
		List<Recruitment> recruitments = jobService.searchRecruitmentByCompany(company.getNameCompany(), pageSize, pageNumber);
		Long totalRecruitments = jobService.getTotalSearchRecruitmentsByCompany(company.getNameCompany());
		int totalPages = (int)Math.ceil((double)totalRecruitments/pageSize);
		int pageNext = pageNumber+1;
		int pagePrev = pageNumber-1;
		
		model.addAttribute("totalRecruitments",totalRecruitments);
		model.addAttribute("recruitments",recruitments);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("pagePrev",pagePrev);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("company",company);
		return "post-company";
	}
	
	@RequestMapping("/getListSaveJob")
	public String getListSaveJob(@RequestParam("username") String userName,
									@RequestParam(value="pageSize", defaultValue = "2") int pageSize,
									@RequestParam(value="pageNumber", defaultValue = "1") int pageNumber,
									Model model) {
		User user = jobService.getUserByUsername(userName);
		List<SaveJob> saveJobs = jobService.getSaveJobByUserId(user.getId(), pageSize, pageNumber);
		Long totalSaveJobs = jobService.getTotalSaveJobByUserId(user.getId());
		int totalPages = (int)Math.ceil((double)totalSaveJobs/pageSize);
		int pageNext = pageNumber+1;
		int pagePrev = pageNumber-1;
		
		model.addAttribute("totalSaveJobs",totalSaveJobs);
		model.addAttribute("saveJobs",saveJobs);
		model.addAttribute("pageNext",pageNext);
		model.addAttribute("pagePrev",pagePrev);
		model.addAttribute("totalPages",totalPages);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageNumber",pageNumber);
		return "list-save-job";
	}
	@RequestMapping("/deleteSaveJob")
	@ResponseBody
	public String deleteSaveJob(@RequestParam("idSJ") int saveJobId,
									Principal principal) {
		if(principal == null) return "false";
		SaveJob saveJob = jobService.getSaveJobById(saveJobId);
		if(saveJob ==null) return "deleted";
		jobService.deleteSaveJob(saveJobId);
		return "true";
	}
}
