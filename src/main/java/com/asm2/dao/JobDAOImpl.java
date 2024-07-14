package com.asm2.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public void saveOrUpdateRecruitment(Recruitment recruitment) {
		sessionFactory.getCurrentSession().saveOrUpdate(recruitment);
	}

	@Override
	public List<Recruitment> getRecruitments(int userId, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<Recruitment> query = session.createQuery(
				"select r from Recruitment r "
				+ "join r.company c "
				+ "join c.user u "
				+ "where u.id= :userId "
				+ "and r.isActive = 1",Recruitment.class);
		query.setParameter("userId", userId);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNumber-1)*pageSize);
		return query.getResultList();
	}

	@Override
	public Long getTotalRecruitment(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count(r) from Recruitment r "
				+ "join r.company c "
				+ "join c.user u "
				+ "where u.id= :userId "
				+ "and r.isActive = 1",Long.class);
		query.setParameter("userId",userId);
		return query.uniqueResult();
	}

	@Override
	public List<ApplyPost> getApplyPostsByRecruiterId(int userId, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<ApplyPost> query = session.createQuery(""
				+ "select a "
				+ "from ApplyPost a "
				+ "join a.recruitment r "
				+ "join r.company c "
				+ "join c.user u "
				+ "where u.id = :userId",ApplyPost.class);
		query.setParameter("userId", userId);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public Long getTotalApplyPostByRecruiterId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count(r) "
				+ "from ApplyPost a "
				+ "join a.recruitment r "
				+ "join r.company c "
				+ "join c.user u "
				+ "where u.id = :userId",Long.class);
		query.setParameter("userId", userId);
		return query.uniqueResult();
	}

	@Override
	public List<Recruitment> searchRecruitmentsByTitle(String keyword, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<Recruitment> query = session.createQuery("from Recruitment "
				+ "where title like :keyword",Recruitment.class);
		query.setParameter("keyword", "%"+keyword+"%");
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNumber-1)*pageSize);
		List<Recruitment> recruitments = query.getResultList();
		return recruitments;
	}

	@Override
	public List<Recruitment> searchRecruitmentByCompany(String keyword, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<Recruitment> query = session.createQuery(""
				+ "select r from Recruitment r "
				+ "join r.company c "
				+ "where c.nameCompany like :keyword",Recruitment.class);
		query.setParameter("keyword", "%"+keyword+"%");
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNumber-1)*pageSize);
		List<Recruitment> recruitments = query.getResultList();
		return recruitments;
	}

	@Override
	public List<Recruitment> searchRecruitmentByAddress(String keyword, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<Recruitment> query = session.createQuery(""
				+ "select r from Recruitment r "
				+ "where address like :keyword",Recruitment.class);
		query.setParameter("keyword", "%"+keyword+"%");
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNumber-1)*pageSize);
		List<Recruitment> recruitments = query.getResultList();
		return recruitments;
	}

	@Override
	public Long getTotalSearchRecruitmentsByTitle(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count(r) from Recruitment r "
				+ "where title like :keyword",Long.class);
		query.setParameter("keyword", "%"+keyword+"%");
		return query.uniqueResult();
	}

	@Override
	public Long getTotalSearchRecruitmentsByCompany(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count(r) from Recruitment r "
				+ "join r.company c "
				+ "where c.nameCompany like :keyword",Long.class);
		query.setParameter("keyword", "%"+keyword+"%");
		return query.uniqueResult();
	}

	@Override
	public Long getTotalSearchRecruitmentsByAddress(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count(r) from Recruitment r "
				+ "where address like :keyword",Long.class);
		query.setParameter("keyword", "%"+keyword+"%");
		return query.uniqueResult();
	}

	@Override
	public List<ApplyPost> getApplyPostsByRecruitmentId(int recruitmentId, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<ApplyPost> query = session.createQuery(""
				+ "select a "
				+ "from ApplyPost a "
				+ "join fetch a.recruitment r "
				+ "where r.id = :recruitmentId",ApplyPost.class);
		query.setParameter("recruitmentId", recruitmentId);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public Long getTotalApplyPostByRecruitmentId(int recruitmentId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count(a) "
				+ "from ApplyPost a "
				+ "join a.recruitment r "
				+ "where r.id = :recruitmentId",Long.class);
		query.setParameter("recruitmentId", recruitmentId);
		return query.uniqueResult();
	}

	@Override
	public Cv getCvByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Cv> query = session.createQuery(""
				+ "select c "
				+ "from Cv c "
				+ "join c.user u "
				+ "where u.id = :userId",Cv.class);
		query.setParameter("userId", userId);
		return query.uniqueResult();
	}

	@Override
	public void saveOrUpdateCv(Cv cv) {
		sessionFactory.getCurrentSession().saveOrUpdate(cv);
	}

	@Override
	public void saveOrUpdateApplyPost(ApplyPost applyPost) {
		sessionFactory.getCurrentSession().saveOrUpdate(applyPost);
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery(""
				+ "from User "
				+ "where email = :email",
				User.class);
		query.setParameter("email", email);
		return query.uniqueResult();
	}

	@Override
	public VerificationToken getVerificationByToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		Query<VerificationToken> query = session.createQuery(""
				+ "from VerificationToken "
				+ "where token =:token",VerificationToken.class);
		query.setParameter("token", token);
		return query.uniqueResult();
	}

	@Override
	public void saveVerificationToken(VerificationToken verificationToken) {
		sessionFactory.getCurrentSession().save(verificationToken);
	}

	@Override
	public void createSaveJob(int recruitmentId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Recruitment recruitment = session.get(Recruitment.class, recruitmentId);
		User user = session.get(User.class, userId);
		if(recruitment!=null & user != null) {
			SaveJob saveJob = new SaveJob(user, recruitment);
			session.save(saveJob);
		}
		else {
			throw new RuntimeException("Recruitment or User not found");
		}
	}

	@Override
	public SaveJob getSaveJob(int recruitmentId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<SaveJob> query = session.createQuery("from SaveJob s "
				+ "where s.recruitment.id =: recruitmentId "
				+ "and s.user.id =: userId",SaveJob.class);
		query.setParameter("userId", userId);
		query.setParameter("recruitmentId", recruitmentId);
		return query.uniqueResult();
	}

	@Override
	public ApplyPost getApplyPost(User user, Recruitment recruitment) {
		Session session = sessionFactory.getCurrentSession();
		Query<ApplyPost> query = session.createQuery(""
				+ "from ApplyPost a "
				+ "where a.user.id = :usId "
				+ "and a.recruitment.id = :reId",
				ApplyPost.class);
		query.setParameter("usId", user.getId());
		query.setParameter("reId", recruitment.getId());
		return query.uniqueResult();
	}

	@Override
	public FollowCompany getFollowCompany(User user, Company company) {
		Session session = sessionFactory.getCurrentSession();
		Query<FollowCompany> query = session.createQuery(""
				+ "from FollowCompany f "
				+ "where f.user.id =: userId "
				+ "and f.company.id =: companyId", FollowCompany.class);
		query.setParameter("userId", user.getId());
		query.setParameter("companyId", company.getId());
		return query.uniqueResult();
	}

	@Override
	public void createFollowCompany(User user, Company company) {
		FollowCompany followCompany = new FollowCompany(user, company);
		sessionFactory.getCurrentSession().save(followCompany);
	}

	@Override
	public List<FollowCompany> getFollowCompanies(int userId, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<FollowCompany> query = session.createQuery(""
				+ "from FollowCompany f "
				+ "where f.user.id = :userId", FollowCompany.class);
		query.setParameter("userId", userId);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNumber-1)*pageSize);
		return query.getResultList();
	}

	@Override
	public Long getTotalFollowCompanies(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count (f) "
				+ "from FollowCompany f "
				+ "where f.user.id = :userId",Long.class);
		query.setParameter("userId", userId);
		return query.uniqueResult();
	}

	@Override
	public FollowCompany getFollowCompanyById(int followCompanyId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(FollowCompany.class, followCompanyId);
	}

	@Override
	public void deleteFollowCompany(int followCompanyId) {
		Session session = sessionFactory.getCurrentSession();
		FollowCompany followCompany = session.get(FollowCompany.class, followCompanyId);
		if(followCompany!=null)
		session.delete(followCompany);
	}

	@Override
	public List<ApplyPost> getApplyPostsByCandidateId(int candidateId, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<ApplyPost> query = session.createQuery(""
				+ "from ApplyPost a "
				+ "where a.user.id = :userId", ApplyPost.class);
		query.setParameter("userId", candidateId);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public Long getTotalApplysPostsByCandidateId(int candidateId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count(a) "
				+ "from ApplyPost a "
				+ "where a.user.id = :userId", Long.class);
		query.setParameter("userId", candidateId);
		return query.uniqueResult();
	}

	@Override
	public ApplyPost getApplyPostById(int applyPostId) {
		return sessionFactory.getCurrentSession().get(ApplyPost.class, applyPostId);
	}

	@Override
	public void deleteApplyPost(int applyPostId) {
		Session session = sessionFactory.getCurrentSession();
		ApplyPost applyPost = session.get(ApplyPost.class, applyPostId);
		session.delete(applyPost);
	}

	@Override
	public List<SaveJob> getSaveJobByUserId(int userId, int pageSize, int pageNumber) {
		Session session = sessionFactory.getCurrentSession();
		Query<SaveJob> query = session.createQuery(""
				+ "from SaveJob s "
				+ "where s.user.id = :userId", SaveJob.class);
		query.setParameter("userId", userId);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public Long getTotalSaveJobByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery(""
				+ "select count(s) "
				+ "from SaveJob s "
				+ "where s.user.id = :userId", Long.class);
		query.setParameter("userId", userId);
		return query.uniqueResult();
	}

	@Override
	public SaveJob getSaveJobById(int saveJobId) {
		return sessionFactory.getCurrentSession().get(SaveJob.class, saveJobId);
	}

	@Override
	public void deleteSaveJob(int saveJobId) {
		Session session = sessionFactory.getCurrentSession();
		SaveJob saveJob = session.get(SaveJob.class, saveJobId);
		session.delete(saveJob);
	}
	

}
