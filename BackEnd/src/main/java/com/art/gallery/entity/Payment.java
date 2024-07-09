package com.art.galley.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "payment")
public class Payment {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "amount", nullable = false)
    private double amount;
	
	@NotNull(message = "Method is required.")
	@Size(min = 2, max = 50, message = "Method must be between 2 and 50 characters.")
	@Column(name = "method", nullable = false)
	private String method;
	
	@NotNull(message = "Art description is required.")
	@Size(min = 2, max = 1000, message = "Art description must be between 2 and 1000 characters.")
	@Column(name = "description", nullable = false)
	private String description;	
    
	@Column(name = "active", nullable = false)
	private Boolean active;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date Date;

	public Payment() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", amount=" + amount + ", method=" + method + ", description=" + description
				+ ", active=" + active + ", createDate=" + createDate + "]";
	} 
}
