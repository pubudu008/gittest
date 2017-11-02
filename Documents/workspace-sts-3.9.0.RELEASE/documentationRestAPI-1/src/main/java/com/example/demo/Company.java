package com.example.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue
private Long id;
private String name;

public List<Employee> getEmplyee() {
	return emplyee;
}
public void setEmplyee(List<Employee> emplyee) {
	this.emplyee = emplyee;
}
private String email;

@ManyToMany
List<Employee> emplyee;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
