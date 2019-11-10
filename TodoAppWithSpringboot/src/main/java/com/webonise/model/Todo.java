package com.webonise.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String desc;

	public Todo() {
	}

	public Todo(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", desc=" + desc + "]";
	}
}
