package com.asm2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm2.dao.JobDAO;
import com.asm2.entity.Company;

@Service
@Transactional
public class JobServiceImpl implements JobService{
	@Autowired
	private JobDAO jobDAO;

	@Override
	public List<Company> getCompanies() {
		return jobDAO.getCompanies();
	}

}
