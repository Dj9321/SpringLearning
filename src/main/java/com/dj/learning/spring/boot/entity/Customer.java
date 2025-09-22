package com.dj.learning.spring.boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//@Entity(name = "customer")
@Entity
@Table(name = "customer", schema = "public")
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Identity means DB will take care of creating id 
	private long id;
	private String email;
	private String pwd;
	@Column(name = "role")
	private String role;

}
