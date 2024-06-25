package com.asm2.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cv")
public class Cv {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="file_name")
	private String fileName;
	@Column(name="isActive")
	private boolean isActive;
	@JoinColumn(name="user_id")
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	public Cv() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public Cv(String fileName, boolean isActive, User user) {
		super();
		this.fileName = fileName;
		this.isActive = isActive;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Cv [id=" + id + ", fileName=" + fileName + ", isActive=" + isActive + ", user=" + user + "]";
	}
	
}
