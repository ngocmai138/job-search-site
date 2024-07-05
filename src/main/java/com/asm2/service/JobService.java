package com.asm2.service;

import java.util.List;

import com.asm2.entity.ApplyPost;
import com.asm2.entity.Category;
import com.asm2.entity.Company;
import com.asm2.entity.Cv;
import com.asm2.entity.Recruitment;
import com.asm2.entity.Role;
import com.asm2.entity.User;

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
	public List<ApplyPost> getApplyPostsByUserId(int userId, int pageSize, int pageNumber);
	public List<ApplyPost> getApplyPostsByRecruitmentId(int recruitmentId, int pageSize, int pageNumber);
	public List<Recruitment> searchRecruitmentsByTitle(String keyword, int pageSize, int pageNumber);
	public List<Recruitment> searchRecruitmentByCompany(String keyword, int pageSize, int pageNumber);
	public List<Recruitment> searchRecruitmentByAddress(String keyword, int pageSize, int pageNumber);
	public Long getTotalCompany();
	public Long getTotalRecruitment();
	public Long getTotalUser();
	public Long getTotalRecruitment(int userId);
	public Long getTotalApplyPostByUserId(int userId);
	public Long getTotalApplyPostByRecruitmentId(int recruitmentId);
	public Long getTotalSearchRecruitmentsByTitle(String keyword);
	public Long getTotalSearchRecruitmentsByCompany(String keyword);
	public Long getTotalSearchRecruitmentsByAddress(String keyword);
	public Role getRole(int roleId);
	public Company getCompanyByUsername(String userName);
	public Company getCompanyById(int companyId);
	public User getUserByUsername(String userName);
	public User getUserById(int userId);
	public Cv getCv(int cvId);
	public Cv getCvByUserId(int userId);
	public Recruitment getRecruitment(int recruitmentId);
	public void addOrUpdateUser(User user);
	public void mergeUser(User user);
	public void updateCompany(Company company);
	public void mergeCompany(Company company);
	public void deleteRecruitment(int recruitmentId);
	public void addOrUpdateRecruitment(Recruitment recruitment);
	public void addOrUpdateCv(Cv cv);
}
