package com.codingdojo.milagros.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="First name can´t be empty")
	@Size(min=2,max=100, message="First name needs at least 2 chars")
	private String firstName;
	
	@NotEmpty(message="Last name can´t be empty")
	@Size(min=2,max=100, message="Last name needs at least 2 chars")
	private String lastName;
	@NotEmpty(message="email is required")
	@Email
	private String email;
	@NotEmpty(message="Password is requiered")
	@Size(min=6, message="Password needs at least 6 chars")
	private String password;
	
	
	@Transient //NO SE GUARDA ESTE DATO EN LA BDD
	@NotEmpty(message="confirmation is requiered")
	@Size(min=6, message="Password needs at least 6 chars")
	private String confirmation;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	public User() {}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
	public void onCreatedAt() {
		this.createdAt = new Date();
	}
	@PreUpdate
	public void onUpdatedAt() {
		this.updatedAt = new Date();
	}
	
}
