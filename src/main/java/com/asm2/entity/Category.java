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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="number_choose")
	private int numberChoose;
	@Column(name="isActive")
	private boolean isActive;
	@OneToMany(fetch = FetchType.LAZY,
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "category_id")
	private List<Recruitment> recruitments;
	
	public Category() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberChoose() {
		return numberChoose;
	}
	public void setNumberChoose(int numberChoose) {
		this.numberChoose = numberChoose;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
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
	
	public void addRecruitment(Recruitment recruitment) {
		if(recruitments == null) {
			recruitments = new ArrayList<>();
		}
		recruitments.add(recruitment);
	}
	
	public Category(String name, int numberChoose, boolean isActive) {
		super();
		this.name = name;
		this.numberChoose = numberChoose;
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", numberChoose=" + numberChoose + ", isActive=" + isActive
				+ "]";
	}
	
	
}
