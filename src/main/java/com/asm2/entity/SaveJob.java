package com.asm2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="save_job")
public class SaveJob {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="recruitment_id")
	private Recruitment recruitment;
	public SaveJob() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Recruitment getRecruitment() {
		return recruitment;
	}
	public void setRecruitment(Recruitment recruitment) {
		this.recruitment = recruitment;
	}
	public SaveJob(User user, Recruitment recruitment) {
		super();
		this.user = user;
		this.recruitment = recruitment;
	}
	@Override
	public String toString() {
		return "SaveJob [id=" + id + ", user=" + user + ", recruitment=" + recruitment + "]";
	}
	
}
