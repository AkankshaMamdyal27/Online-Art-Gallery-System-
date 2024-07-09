package com.art.galley.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "orders", uniqueConstraints = { @UniqueConstraint(columnNames = "order_num") })
public class Order {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "price", nullable = false)
    private double price;
    
    @Column(name = "customer_id", length = 30, nullable = false)
    private Long customer_id;
    
    @Column(name = "confirmation", length = 255, nullable = false)
    private String confirmation;
    
    @Column(name = "phone", length = 10, nullable = false)
    private String phone;
    
    @Column(name = "active", nullable = false)
	private Boolean active;
    
//    @Column(name = "payment_id", length = 6, nullable = false)
//    private int paymentId;
    
    @Column(name = "order_date", nullable = false)
    private Date orderDate;
    
    //@OneToMany(mappedBy = "order")
    //@OneToMany(cascade = CascadeType.ALL, mappedBy="order", orphanRemoval = true)
    //private Set<OrderDetail> odSet = new HashSet<>();

	public Order() {}
	
	public Order(int id, double price, int customer_id, String confirmation, String phone, Boolean active, Date orderDate) {
		this.id = id;
		this.price = price;
		this.customer_id = custome_id;
		this.Phone = Phone;
		this.confirmation = confirmation;
		this.active = active;
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String Phone) {
		this.Phone = Phone;
	}
	
	public String getconfirmation() {
		return Confirmation;
	}

	public void setconfirmation(String Confirmation) {
		this.Confirmation = Confirmation;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", price=" + price + ",confirmation=" + confirmation + ", customer_id=" + customer_id+ ",phone=" + phone + ", + ", active=" + active + ", orderDate=" + orderDate + "]";
	}
    
}
