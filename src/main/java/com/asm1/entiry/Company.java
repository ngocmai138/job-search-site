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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "address")
	private String address;
	@Column(name="description")
	private String description;
	@Column(name="email")
	private String email;
	@Column(name="logo")
	private String logo;
	@Column(name="name_company")
	private String nameCompany;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="status")
	private int status;
	@Column(name="isActive")
	private boolean isActive;
	@JoinColumn(name="user_id")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	private User user;
	@OneToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="company_id")
	private List<Recruitment> recruitments;
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="follow_company",
				joinColumns = @JoinColumn(name="company_id"),
				inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> usersFollow;
	
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getNameCompany() {
		return nameCompany;
	}
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Recruitment> getRecruitments() {
		return recruitments;
	}
	public void setRecruitments(List<Recruitment> recruitments) {
		this.recruitments = recruitments;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<User> getUsers() {
		return usersFollow;
	}
	public void setUsers(List<User> users) {
		this.usersFollow = users;
	}
	
	public void addRecuitment(Recruitment recruitment) {
		if(recruitments == null) {
			recruitments = new ArrayList<Recruitment>();
		}
		recruitments.add(recruitment);
	}
	
	public void addUserFollow(User user) {
		if(usersFollow == null) {
			usersFollow = new ArrayList<User>();
		}
		usersFollow.add(user);
	}
	
	public Company(String address, String description, String email, String logo, String nameCompany,
			String phoneNumber, int status, boolean isActive, User user) {
		super();
		this.address = address;
		this.description = description;
		this.email = email;
		this.logo = logo;
		this.nameCompany = nameCompany;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.isActive = isActive;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", address=" + address + ", description=" + description + ", email=" + email
				+ ", logo=" + logo + ", nameCompany=" + nameCompany + ", phoneNumber=" + phoneNumber + ", status="
				+ status + ", isActive=" + isActive + ", user=" + user + "]";
	}
	
	
}
