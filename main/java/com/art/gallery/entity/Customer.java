package com.art.galley.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@NotNull(message = "Customer name is required.")
	@Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters.")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull(message = "Customer contact is required.")
	@Size(max = 10, message = "Contact Number must be of 10 digits only.")
	@Column(name = "contact", nullable = false, unique = true)
    private String contact; 
	
	@NotNull(message = "Customer address is required.")
	@Column(name = "address", nullable = false)
    private String address;
    
	public Customer() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", contact=" + contact + ", address=" + address +"]";
	}  
}
