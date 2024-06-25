package com.asm1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm1.dao.JobDAO;
import com.asm1.entiry.Company;

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
