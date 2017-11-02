package com.example.demo.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee{

	@Id
	@GeneratedValue
	private Long id;
	
	private String fname;
	private String lname;
	
	@ManyToOne
	private Company company;

}

