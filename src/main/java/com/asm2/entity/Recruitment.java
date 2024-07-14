package com.asm2.entity;

import java.sql.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="recruitment")
public class Recruitment {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="address")
	private String address;
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="description")
	private String description;
	@Column(name="experience")
	private String experience;
	@Column(name="quantity")
	private int quantity;
	@Column(name="`rank`")
	private String rank;
	@Column(name="salary")
	private String salary;
	@Column(name="status")
	private int status;
	@Column(name="title")
	private String title;
	@Column(name="type")
	private String type;
	@Column(name="view")
	private int view;
	@JoinColumn(name = "category_id")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	private Category category;
	@JoinColumn(name="company_id")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})			
	private Company company;
	@Column(name="deadline")
	private Date deadline;
	@Column(name="isActive")
	private boolean isActive;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitment")
	private List<ApplyPost> applyPosts;
	@OneToMany(mappedBy = "recruitment")
	private List<SaveJob> saveJobs;
	
	public Recruitment() {}
	
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
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public List<ApplyPost> getApplyPosts() {
		return applyPosts;
	}
	public void setApplyPosts(List<ApplyPost> applyPosts) {
		this.applyPosts = applyPosts;
	}
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date(System.currentTimeMillis());
		this.isActive = true;
	}
	public Recruitment(String address, String description, String experience, int quantity, String rank, String salary,
			int status, String title, String type, int view, Category category, Company company, Date deadline,
			boolean isActive) {
		super();
		this.address = address;
		this.description = description;
		this.experience = experience;
		this.quantity = quantity;
		this.rank = rank;
		this.salary = salary;
		this.status = status;
		this.title = title;
		this.type = type;
		this.view = view;
		this.category = category;
		this.company = company;
		this.deadline = deadline;
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Recruitment [id=" + id + ", address=" + address + ", description=" + description + ", experience="
				+ experience + ", quantity=" + quantity + ", rank=" + rank + ", salary=" + salary + ", status=" + status
				+ ", title=" + title + ", type=" + type + ", view=" + view + ", category=" + category + ", company="
				+ company + ", deadline=" + deadline + ", isActive=" + isActive + "]";
	}
	
}
