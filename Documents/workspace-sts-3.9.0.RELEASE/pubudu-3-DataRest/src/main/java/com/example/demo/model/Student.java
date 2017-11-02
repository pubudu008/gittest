package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Student implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 2093810336718710432L;
private String id;
private String name;
private String property;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getProperty() {
	return property;
}
public void setProperty(String property) {
	this.property = property;
}



}
