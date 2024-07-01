package com.asm2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm2.dao.JobDAO;
import com.asm2.entity.Category;
import com.asm2.entity.Company;
import com.asm2.entity.Cv;
import com.asm2.entity.Recruitment;
import com.asm2.entity.Role;
import com.asm2.entity.User;

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
	public void addOrUpdateRecruitment(Recruitment recruitment) {
		jobDAO.addOrUpdateRecruitment(recruitment);
	}

}
