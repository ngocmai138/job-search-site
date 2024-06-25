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
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="role_name")
	private String roleName;
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {CascadeType.DETACH, CascadeType.MERGE,
							CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="authorities",
				joinColumns = @JoinColumn(name="role_id"),
				inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> users;
	
	public Role() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		if(users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}
	public Role(String roleName, List<User> users) {
		super();
		this.roleName = roleName;
		this.users = users;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", users=" + users + "]";
	}
	
	
}
