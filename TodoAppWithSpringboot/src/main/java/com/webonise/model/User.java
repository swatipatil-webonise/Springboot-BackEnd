package com.webonise.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class User implements Serializable{
		
	private static final long serialVersionUID = 1L;
	@Id
	@Getter @Setter private int id;
	@Getter @Setter private String name;
	@Getter @Setter @Column(unique = true) private String username;
	@Getter @Setter private String password;
	@Getter @Setter @Column(unique = true) private String email;
	public User() {}
	public User(int id) {
		super();
		this.id = id;
	}
}
