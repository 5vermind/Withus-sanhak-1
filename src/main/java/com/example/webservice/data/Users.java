package com.example.webservice.data;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {
	@Id
	@Column(columnDefinition = "VARCHAR(50)")
	String id;
	@Column(columnDefinition = "VARCHAR(50)")
	String password;


	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(String id) {
		this.id = id;
	}
}
