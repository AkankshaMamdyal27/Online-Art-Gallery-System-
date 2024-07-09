package com.art.galley.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "art")
public class Art {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "type", nullable = false)
	private int type;
	
	@Column(name = "description", nullable = false)
	private double description;
	
	@OneToOne
	@JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "ART_CUST_FK") )
	@JsonIgnore
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	//@Cascade({ org.hibernate.annotations.CascadeType.ALL })
    @JoinColumn(name = "art_id", nullable = false, foreignKey = @ForeignKey(name = "ART_ID_FK") )
	@JsonIgnore
	private Art art;

	public Art() {}

	public Art(int type, double description, Customer customer) {
		this.type = type;
		this.description = description;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getDescription() {
		return description;
	}

	public void setDescription(double description) {
		this.description = description;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", type=" + type + ", description=" + description + ", customer=" + customer + ]";
	}
}
