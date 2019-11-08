package com.webonise.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private String name;
	@Id
	@Column(unique = true) private String username;
	private String password;
	@Column(unique = true) private String email;
	
	public User() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
}
