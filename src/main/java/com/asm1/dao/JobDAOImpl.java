package com.asm1.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asm1.entiry.Company;

@Repository
@Transactional
public class JobDAOImpl implements JobDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Company> getCompanies() {
		Session session = sessionFactory.getCurrentSession();
		Query<Company> query = session.createQuery("from Company where isActive = 1", Company.class);
		List<Company> companies = query.getResultList();
		return companies;
	}
	

}
