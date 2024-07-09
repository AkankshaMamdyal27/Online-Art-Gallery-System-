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
@Table(name = "artist")
public class Artist {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull(message = "Name can't be blank.")
	@Size(max = 50)
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull(message = "Style can't be blank.")
	@Size(max = 50)
	@Column(name = "style", nullable = false)
	private String style;
	
	@NotNull(message = "Age can't be blank.")
	@Size(max = 50)
	@Column(name = "age", nullable = false)
	private int age;
	
	@NotNull(message = "Birthplace can't be blank.")
	@Size(max = 1000)
	@Column(name = "birthPlace", nullable = false)
	private String birthPlace;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "contact_date", nullable = false)
	private Date contactDate;

	public Contact() {
		
	}

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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.borthplace = birthplace;
	}

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", style=" + style + ", age=" + age + ", birthplace="
				+ birthplace + ", contactDate=" + contactDate + "]";
	}


}
