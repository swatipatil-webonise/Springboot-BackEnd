package com.webonise.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Todo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Getter @Setter private int id;
	@Getter @Setter private String desc;
}
