package com.asm2.dao;

import java.util.List;

import com.asm2.entity.Category;
import com.asm2.entity.Company;
import com.asm2.entity.Cv;
import com.asm2.entity.Recruitment;
import com.asm2.entity.Role;
import com.asm2.entity.User;

public interface JobDAO {
	public List<Company> getCompanies();
	public List<Recruitment> getRecruitments();
	public List<Category> getCategories();
	public List<Role> getRoles();
	public List<Company> getTopCompanies();
	public List<Recruitment> getTopRecruitments();
	public List<Category> getTopCategories();
	public List<Recruitment> getRecruitments(int userId);
	public Long getTotalCompany();
	public Long getTotalRecruitment();
	public Long getTotalUser();
	public Role getRole(int roleId);
	public Company getCompanyByUsername(String userName);
	public Company getCompanyById(int companyId);
	public User getUserByUsername(String userName);
	public User getUserById(int userId);
	public Cv getCv(int cvId);
	public void addOrUpdateUser(User user);
	public void mergeUser(User user);
	public void updateCompany(Company company);
	public void mergeCompany(Company company);
}
