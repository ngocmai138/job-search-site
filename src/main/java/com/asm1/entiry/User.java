package com.asm1.entiry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="address")
	private String address;
	@Column(name="description")
	private String description;
	@Column(name="email")
	private String email;
	@Column(name="user_name")
	private String userName;
	@Column(name="image")
	private String image;
	@Column(name="password")
	private String password;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="status")
	private int status;
	@Column(name="isActive")
	private boolean isActive;
	@JoinColumn(name="cv_id")
	@OneToOne(cascade = CascadeType.ALL)
	private Cv sv;
	
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="authorities",
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<Role> roles;
	
	@OneToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private List<Company> companies;
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "save_job",
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="recruitment_id"))	
	private List<Recruitment> recruitmentsSave;
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="follow_company",
				joinColumns = @JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="company_id"))
	private List<Company> followCompanies;
	@OneToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private List<ApplyPost> applyPosts;
	public User() {
		this.status=1;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Cv getSv() {
		return sv;
	}
	public void setSv(Cv sv) {
		this.sv = sv;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void addRole(Role role) {
		if(roles == null) {
			roles = new ArrayList<>();
		}
		roles.add(role);
	}
	public List<Company> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	public List<Recruitment> getRecruitments() {
		return recruitmentsSave;
	}
	public void setRecruitments(List<Recruitment> recruitments) {
		this.recruitmentsSave = recruitments;
	}

	public List<Company> getFollowCompanies() {
		return followCompanies;
	}


	public void setFollowCompanies(List<Company> followCompanies) {
		this.followCompanies = followCompanies;
	}


	public List<Recruitment> getRecruitmentsSave() {
		return recruitmentsSave;
	}


	public void setRecruitmentsSave(List<Recruitment> recruitmentsSave) {
		this.recruitmentsSave = recruitmentsSave;
	}


	public List<ApplyPost> getApplyPosts() {
		return applyPosts;
	}


	public void setApplyPosts(List<ApplyPost> applyPosts) {
		this.applyPosts = applyPosts;
	}


	public void addRecruitmentSave(Recruitment recruitment) {
		if(recruitmentsSave == null) {
			recruitmentsSave = new ArrayList<Recruitment>();
		}
		recruitmentsSave.add(recruitment);
	}

	public void addCompany(Company company) {
		if(companies == null) {
			companies = new ArrayList<>();
		}
		companies.add(company);
	}
	
	public void addFollowCompany(Company company) {
		if(followCompanies == null) {
			followCompanies = new ArrayList<Company>();
		}
		followCompanies.add(company);
	}
	public void addApplyPost(ApplyPost applyPost) {
		if(applyPosts == null) {
			applyPosts = new ArrayList<ApplyPost>();
		}
		applyPosts.add(applyPost);
	}

	public User(String address, String description, String email, String userName, String image, String password,
			String phoneNumber, int status, boolean isActive, Cv sv, List<Role> roles) {
		super();
		this.address = address;
		this.description = description;
		this.email = email;
		this.userName = userName;
		this.image = image;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.isActive = isActive;
		this.sv = sv;
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", address=" + address + ", description=" + description + ", email=" + email
				+ ", userName=" + userName + ", image=" + image + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", status=" + status + ", isActive=" + isActive + ", sv=" + sv + ", roles=" + roles
				+ "]";
	}


	
}
