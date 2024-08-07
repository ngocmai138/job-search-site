package com.asm2.entity;

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
import javax.persistence.PrimaryKeyJoinColumn;
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
	private Integer status;
	@Column(name="isActive")
	private boolean isActive;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Company company;

	@OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
	private Cv cv;
	
	@ManyToMany(fetch = FetchType.EAGER,
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
	@OneToMany(mappedBy = "user")
	private List<FollowCompany> followCompanies;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<ApplyPost> applyPosts;
	@OneToMany(mappedBy = "user")
	private List<SaveJob> saveJobs;
	
	public User() {
		this.status=0;
		this.isActive=true;
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
	public Integer getStatus() {
		return status;
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
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Cv getSv() {
		return cv;
	}
	public void setSv(Cv sv) {
		this.cv = sv;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<SaveJob> getSaveJobs() {
		return saveJobs;
	}

	public void setSaveJobs(List<SaveJob> saveJobs) {
		this.saveJobs = saveJobs;
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



	public List<FollowCompany> getFollowCompanies() {
		return followCompanies;
	}

	public void setFollowCompanies(List<FollowCompany> followCompanies) {
		this.followCompanies = followCompanies;
	}

	public List<ApplyPost> getApplyPosts() {
		return applyPosts;
	}


	public void setApplyPosts(List<ApplyPost> applyPosts) {
		this.applyPosts = applyPosts;
	}


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Cv getCv() {
		return cv;
	}

	public void setCv(Cv cv) {
		this.cv = cv;
	}

	public void addCompany(Company company) {
		if(companies == null) {
			companies = new ArrayList<>();
		}
		companies.add(company);
	}
	
	public void addFollowCompany(FollowCompany followCompany) {
		if(followCompanies == null) {
			followCompanies = new ArrayList<FollowCompany>();
		}
		followCompanies.add(followCompany);
	}
	public void addApplyPost(ApplyPost applyPost) {
		if(applyPosts == null) {
			applyPosts = new ArrayList<ApplyPost>();
		}
		applyPosts.add(applyPost);
	}
	public void addSaveJob(SaveJob saveJob) {
		if(saveJobs == null) {
			saveJobs = new ArrayList<SaveJob>();
		}
		saveJobs.add(saveJob);
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
		this.cv = sv;
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", address=" + address + ", description=" + description + ", email=" + email
				+ ", userName=" + userName + ", image=" + image + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", status=" + status + ", isActive=" + isActive + ", sv=" + cv + ", roles=" + roles
				+ "]";
	}


	
}
