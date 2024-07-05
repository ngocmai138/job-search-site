package com.asm2.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="applypost")
public class ApplyPost {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="status")
	private int status;
	@Column(name="text")
	private String text;
	@Column(name="name_cv")
	private String nameCv;
	@Column(name="image")
	private String image;
	@JoinColumn(name="recruitment_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Recruitment recruitment;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	public ApplyPost() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getNameCv() {
		return nameCv;
	}
	public void setNameCv(String nameCv) {
		this.nameCv = nameCv;
	}
	public Recruitment getRecruitment() {
		return recruitment;
	}
	public void setRecruitment(Recruitment recruitment) {
		this.recruitment = recruitment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ApplyPost(Date createdAt, int status, String text, String nameCv, Recruitment recruitment, User user) {
		super();
		this.createdAt = createdAt;
		this.status = status;
		this.text = text;
		this.nameCv = nameCv;
		this.recruitment = recruitment;
		this.user = user;
	}
	@Override
	public String toString() {
		return "ApplyPost [id=" + id + ", createdAt=" + createdAt + ", status=" + status + ", text=" + text
				+ ", nameCv=" + nameCv + ", recruitment=" + recruitment + ", user=" + user + "]";
	}
	
	
	
}
