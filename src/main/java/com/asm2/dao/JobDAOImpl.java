package com.asm2.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asm2.entity.Category;
import com.asm2.entity.Company;
import com.asm2.entity.Cv;
import com.asm2.entity.Recruitment;
import com.asm2.entity.Role;
import com.asm2.entity.User;

@Repository
@Transactional
public class JobDAOImpl implements JobDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Company> getCompanies() {
		Session session = sessionFactory.getCurrentSession();
		Query<Company> query = session.createQuery("from Company where isActive=1", Company.class);
		List<Company> companies = query.getResultList();
		return companies;
	}

	@Override
	public List<Recruitment> getRecruitments() {
		Session session = sessionFactory.getCurrentSession();
		Query<Recruitment> query = session.createQuery("from Recruitment where isActive=1", Recruitment.class);
		List<Recruitment> recruitments = query.getResultList();
		return recruitments;
	}

	@Override
	public List<Category> getCategories() {
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("from Category where isActive=1", Category.class);
		List<Category> categories = query.getResultList();
		return categories;
	}

	@Override
	public List<Category> getTopCategories() {
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("from Category "
													+ "where isActive=1 "
													+ "order by numberChoose desc ", 
													Category.class);
		query.setMaxResults(4);
		List<Category> categories = query.getResultList();
		return categories;
	}

	@Override
	public List<Company> getTopCompanies() {
		Session session = sessionFactory.getCurrentSession();
		Query<Company> query = session.createQuery("from Company "
													+ "where isActive=1 "
													+ "order by numberOfRecruitments desc", 
													Company.class);
		query.setMaxResults(4);
		List<Company> companies = query.getResultList();
		return companies;
	}

	@Override
	public List<Recruitment> getTopRecruitments() {
		Session session = sessionFactory.getCurrentSession();
		Query<Recruitment> query = session.createQuery("select r "
														+ "from Recruitment r "
														+ "join r.applyPosts a "
														+ "where r.isActive = 1 "
														+ "group by r "
														+ "order by count(a) desc",
														Recruitment.class);
		query.setMaxResults(4);
		List<Recruitment> recruitments = query.getResultList();
		return recruitments;
	}

	@Override
	public Long getTotalCompany() {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery("select count(1) from Company where isActive = 1", Long.class);
		return query.uniqueResult();
	}

	@Override
	public Long getTotalRecruitment() {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery("select count(1) from Recruitment where isActive = 1",Long.class);
		return query.uniqueResult();
	}

	@Override
	public Long getTotalUser() {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery("select count(1) from User where isActive = 1",Long.class);
		return query.uniqueResult();
	}

	@Override
	public List<Role> getRoles() {
		Session session = sessionFactory.getCurrentSession();
		Query<Role> query = session.createQuery("from Role", Role.class);
		List<Role> roles = query.getResultList();
		return roles;
	}

	@Override
	public Role getRole(int roleId) {
		Session session = sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, roleId);
		return role;
	}

	@Override
	public void addOrUpdateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	@Override
	public Company getCompanyByUsername(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query<Company> query = session.createQuery("from Company c where c.user.userName = :userName",Company.class);
		query.setParameter("userName", userName);
		return query.uniqueResult();
	}

	@Override
	public User getUserByUsername(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User where userName = :userName",User.class);
		query.setParameter("userName", userName);
		return query.uniqueResult();
	}

	@Override
	public void updateCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();
		session.update(company);
	}

	@Override
	public User getUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		return user;
	}

	@Override
	public Company getCompanyById(int companyId) {
		return sessionFactory.getCurrentSession().get(Company.class, companyId);
	}

	@Override
	public Cv getCv(int cvId) {
		return sessionFactory.getCurrentSession().get(Cv.class, cvId);
	}

	@Override
	public void mergeUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(user);
	}

	@Override
	public void mergeCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(company);
	}

	@Override
	public List<Recruitment> getRecruitments(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Recruitment> query = 
							session.createQuery("select r from Recruitment r "
												+ "join r.company c "
												+ "join c.user u "
												+ "where u.id= :userId "
												+ "and r.isActive = 1",
												Recruitment.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public Recruitment getRecruitment(int recruitmentId) {
		return sessionFactory.getCurrentSession().get(Recruitment.class, recruitmentId);
	}

	@Override
	public void deleteRecruitment(int recruitmentId) {
		Session session = sessionFactory.getCurrentSession();
		Recruitment recruitment = session.get(Recruitment.class, recruitmentId);
		recruitment.setIsActive(false);
		session.update(recruitment);
	}

	@Override
	public void addOrUpdateRecruitment(Recruitment recruitment) {
		sessionFactory.getCurrentSession().saveOrUpdate(recruitment);
	}
	

}
