package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.hateoas.ResourceSupport;

@Entity
public class Car extends ResourceSupport{
	@Id
private long id;
	
	private String brand;
	
	public Car() {}
	
	public Car(long id, String brand) {
		this.id = id;
		this.brand = brand;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

}
