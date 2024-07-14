package com.asm2.service;

import java.util.List;

import com.asm2.entity.ApplyPost;
import com.asm2.entity.Category;
import com.asm2.entity.Company;
import com.asm2.entity.Cv;
import com.asm2.entity.FollowCompany;
import com.asm2.entity.Recruitment;
import com.asm2.entity.Role;
import com.asm2.entity.SaveJob;
import com.asm2.entity.User;
import com.asm2.entity.VerificationToken;

public interface JobService {
	public List<Company> getCompanies();
	public List<Recruitment> getRecruitments();
	public List<Category> getCategories();
	public List<Role> getRoles();
	public List<Company> getTopCompanies();
	public List<Recruitment> getTopRecruitments();
	public List<Category> getTopCategories();
	public List<Recruitment> getRecruitments(int userId);
	public List<Recruitment> getRecruitments(int userId, int pageSize, int pageNumber);
	public List<ApplyPost> getApplyPostsByRecruiterId(int recruiterId, int pageSize, int pageNumber);
	public List<ApplyPost> getApplyPostsByRecruitmentId(int recruitmentId, int pageSize, int pageNumber);
	public List<Recruitment> searchRecruitmentsByTitle(String keyword, int pageSize, int pageNumber);
	public List<Recruitment> searchRecruitmentByCompany(String keyword, int pageSize, int pageNumber);
	public List<Recruitment> searchRecruitmentByAddress(String keyword, int pageSize, int pageNumber);
	public List<FollowCompany> getFollowCompanies(int userId, int pageSize, int pageNumber);
	public List<ApplyPost> getApplyPostsByCandidateId(int candidateId, int pageSize, int pageNumber);
	public List<SaveJob> getSaveJobByUserId(int userId, int pageSize, int pageNumber);
	public Long getTotalSaveJobByUserId(int userId);
	public Long getTotalApplysPostsByCandidateId(int candidateId);
	public Long getTotalCompany();
	public Long getTotalRecruitment();
	public Long getTotalUser();
	public Long getTotalRecruitment(int userId);
	public Long getTotalApplyPostByRecruiterId(int recruiterId);
	public Long getTotalApplyPostByRecruitmentId(int recruitmentId);
	public Long getTotalSearchRecruitmentsByTitle(String keyword);
	public Long getTotalSearchRecruitmentsByCompany(String keyword);
	public Long getTotalSearchRecruitmentsByAddress(String keyword);
	public Long getTotalFollowCompanies(int userId);
	public Role getRole(int roleId);
	public Company getCompanyByUsername(String userName);
	public Company getCompanyById(int companyId);
	public User getUserByUsername(String userName);
	public User getUserById(int userId);
	public User getUserByEmail(String email);
	public Cv getCv(int cvId);
	public Cv getCvByUserId(int userId);
	public Recruitment getRecruitment(int recruitmentId);
	public VerificationToken getVerificationByToken(String token);
	public ApplyPost getApplyPost(User user, Recruitment recruitment);
	public SaveJob getSaveJob(int recruitmentId, int userId);
	public FollowCompany getFollowCompany(User user, Company company);
	public FollowCompany getFollowCompanyById(int followCompanyId);
	public ApplyPost getApplyPostById(int applyPostId);
	public SaveJob getSaveJobById(int saveJobId);
	public void deleteSaveJob(int saveJobId);
	public void deleteApplyPost(int applyPostId);
	public void deleteFollowCompany(int followCompanyId);
	public void createFollowCompany(User user, Company company);
	public void saveVerificationToken(VerificationToken verificationToken);
	public void addOrUpdateUser(User user);
	public void mergeUser(User user);
	public void updateCompany(Company company);
	public void mergeCompany(Company company);
	public void deleteRecruitment(int recruitmentId);
	public void saveOrUpdateRecruitment(Recruitment recruitment);
	public void saveOrUpdateCv(Cv cv);
	public void saveOrUpdateApplyPost(ApplyPost applyPost);
	public void createSaveJob(int recruitmentId, int userId);
}
