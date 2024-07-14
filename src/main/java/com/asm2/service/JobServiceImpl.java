package com.asm2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm2.dao.JobDAO;
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

@Service
@Transactional
public class JobServiceImpl implements JobService{
	@Autowired
	private JobDAO jobDAO;

	@Override
	public List<Company> getCompanies() {
		return jobDAO.getCompanies();
	}

	@Override
	public List<Recruitment> getRecruitments() {
		return jobDAO.getRecruitments();
	}

	@Override
	public List<Category> getCategories() {
		return jobDAO.getCategories();
	}

	@Override
	public List<Category> getTopCategories() {
		return jobDAO.getTopCategories();
	}

	@Override
	public List<Company> getTopCompanies() {
		return jobDAO.getTopCompanies();
	}

	@Override
	public List<Recruitment> getTopRecruitments() {
		return jobDAO.getTopRecruitments();
	}

	@Override
	public Long getTotalCompany() {
		return jobDAO.getTotalCompany();
	}

	@Override
	public Long getTotalRecruitment() {
		return jobDAO.getTotalRecruitment();
	}

	@Override
	public Long getTotalUser() {
		return jobDAO.getTotalUser();
	}

	@Override
	public List<Role> getRoles() {
		return jobDAO.getRoles();
	}

	@Override
	public Role getRole(int roleId) {
		return jobDAO.getRole(roleId);
	}

	@Override
	public void addOrUpdateUser(User user) {
		jobDAO.addOrUpdateUser(user);
	}

	@Override
	public Company getCompanyByUsername(String userName) {
		return jobDAO.getCompanyByUsername(userName);
	}

	@Override
	public User getUserByUsername(String userName) {
		return jobDAO.getUserByUsername(userName);
	}

	@Override
	public void updateCompany(Company company) {
		jobDAO.updateCompany(company);
	}

	@Override
	public User getUserById(int userId) {
		return jobDAO.getUserById(userId);
	}

	@Override
	public Company getCompanyById(int companyId) {
		return jobDAO.getCompanyById(companyId);
	}

	@Override
	public Cv getCv(int cvId) {
		return jobDAO.getCv(cvId);
	}

	@Override
	public void mergeUser(User user) {
		jobDAO.mergeUser(user);
	}

	@Override
	public void mergeCompany(Company company) {
		jobDAO.mergeCompany(company);
	}

	@Override
	public List<Recruitment> getRecruitments(int userId) {
		return jobDAO.getRecruitments(userId);
	}

	@Override
	public Recruitment getRecruitment(int recruitmentId) {
		return jobDAO.getRecruitment(recruitmentId);
	}

	@Override
	public void deleteRecruitment(int recruitmentId) {
		jobDAO.deleteRecruitment(recruitmentId);
	}

	@Override
	public void saveOrUpdateRecruitment(Recruitment recruitment) {
		jobDAO.saveOrUpdateRecruitment(recruitment);
	}

	@Override
	public List<Recruitment> getRecruitments(int userId, int pageSize, int pageNumber) {
		return jobDAO.getRecruitments(userId, pageSize, pageNumber);
	}

	@Override
	public Long getTotalRecruitment(int userId) {
		return jobDAO.getTotalRecruitment(userId);
	}

	@Override
	public List<ApplyPost> getApplyPostsByRecruiterId(int userId, int pageSize, int pageNumber) {
		return jobDAO.getApplyPostsByRecruiterId(userId, pageSize, pageNumber);
	}

	@Override
	public Long getTotalApplyPostByRecruiterId(int userId) {
		return jobDAO.getTotalApplyPostByRecruiterId(userId);
	}

	@Override
	public List<Recruitment> searchRecruitmentsByTitle(String keyword, int pageSize, int pageNumber) {
		return jobDAO.searchRecruitmentsByTitle(keyword,pageSize,pageNumber);
	}

	@Override
	public List<Recruitment> searchRecruitmentByCompany(String keyword, int pageSize, int pageNumber) {
		return jobDAO.searchRecruitmentByCompany(keyword,pageSize,pageNumber);
	}

	@Override
	public List<Recruitment> searchRecruitmentByAddress(String keyword, int pageSize, int pageNumber) {
		return jobDAO.searchRecruitmentByAddress(keyword,pageSize,pageNumber);
	}

	@Override
	public Long getTotalSearchRecruitmentsByTitle(String keyword) {
		return jobDAO.getTotalSearchRecruitmentsByTitle(keyword);
	}

	@Override
	public Long getTotalSearchRecruitmentsByCompany(String keyword) {
		return jobDAO.getTotalSearchRecruitmentsByCompany(keyword);
	}

	@Override
	public Long getTotalSearchRecruitmentsByAddress(String keyword) {
		return jobDAO.getTotalSearchRecruitmentsByAddress(keyword);
	}

	@Override
	public List<ApplyPost> getApplyPostsByRecruitmentId(int recruitmentId, int pageSize, int pageNumber) {
		return jobDAO.getApplyPostsByRecruitmentId(recruitmentId, pageSize, pageNumber);
	}

	@Override
	public Long getTotalApplyPostByRecruitmentId(int recruitmentId) {
		return jobDAO.getTotalApplyPostByRecruitmentId(recruitmentId);
	}

	@Override
	public Cv getCvByUserId(int userId) {
		return jobDAO.getCvByUserId(userId);
	}

	@Override
	public void saveOrUpdateCv(Cv cv) {
		jobDAO.saveOrUpdateCv(cv);
		
	}

	@Override
	public void saveOrUpdateApplyPost(ApplyPost applyPost) {
		jobDAO.saveOrUpdateApplyPost(applyPost);
	}

	@Override
	public User getUserByEmail(String email) {
		return jobDAO.getUserByEmail(email);
	}

	@Override
	public VerificationToken getVerificationByToken(String token) {
		return jobDAO.getVerificationByToken(token);
	}

	@Override
	public void saveVerificationToken(VerificationToken verificationToken) {
		jobDAO.saveVerificationToken(verificationToken);
	}

	@Override
	public void createSaveJob(int recruitmentId, int userId) {
		jobDAO.createSaveJob(recruitmentId, userId);
	}

	@Override
	public SaveJob getSaveJob(int recruitmentId, int userId) {
		return jobDAO.getSaveJob(recruitmentId, userId);
	}

	@Override
	public ApplyPost getApplyPost(User user, Recruitment recruitment) {
		return jobDAO.getApplyPost(user, recruitment);
	}

	@Override
	public FollowCompany getFollowCompany(User user, Company company) {
		return jobDAO.getFollowCompany(user, company);
	}

	@Override
	public void createFollowCompany(User user, Company company) {
		jobDAO.createFollowCompany(user, company);
		
	}

	@Override
	public List<FollowCompany> getFollowCompanies(int userId, int pageSize, int pageNumber) {
		return jobDAO.getFollowCompanies(userId, pageSize, pageNumber);
	}

	@Override
	public Long getTotalFollowCompanies(int userId) {
		return jobDAO.getTotalFollowCompanies(userId);
	}

	@Override
	public FollowCompany getFollowCompanyById(int followCompanyId) {
		return jobDAO.getFollowCompanyById(followCompanyId);
	}

	@Override
	public void deleteFollowCompany(int followCompanyId) {
		jobDAO.deleteFollowCompany(followCompanyId);
		
	}

	@Override
	public List<ApplyPost> getApplyPostsByCandidateId(int candidateId, int pageSize, int pageNumber) {
		return jobDAO.getApplyPostsByCandidateId(candidateId, pageSize, pageNumber);
	}

	@Override
	public Long getTotalApplysPostsByCandidateId(int candidateId) {
		return jobDAO.getTotalApplysPostsByCandidateId(candidateId);
	}

	@Override
	public ApplyPost getApplyPostById(int applyPostId) {
		return jobDAO.getApplyPostById(applyPostId);
	}

	@Override
	public void deleteApplyPost(int applyPostId) {
		jobDAO.deleteApplyPost(applyPostId);
	}

	@Override
	public List<SaveJob> getSaveJobByUserId(int userId, int pageSize, int pageNumber) {
		return jobDAO.getSaveJobByUserId(userId, pageSize, pageNumber);
	}

	@Override
	public Long getTotalSaveJobByUserId(int userId) {
		return jobDAO.getTotalSaveJobByUserId(userId);
	}

	@Override
	public SaveJob getSaveJobById(int saveJobId) {
		return jobDAO.getSaveJobById(saveJobId);
	}

	@Override
	public void deleteSaveJob(int saveJobId) {
		jobDAO.deleteSaveJob(saveJobId);
	}
}
