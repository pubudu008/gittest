package com.example.myproject.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class topic {
	
	@Id
private String id;
private String name;
private String property;

public topic() {
	
	
}

public topic(String id, String name, String property) {
	super();
	this.id = id;
	this.name = name;
	this.property = property;
}
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
