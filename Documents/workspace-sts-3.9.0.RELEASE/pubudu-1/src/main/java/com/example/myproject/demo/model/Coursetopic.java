package com.example.myproject.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Coursetopic {
	
	@Id
private String id;
private String name;
private String property;

@ManyToOne
private topic topic;



public Coursetopic() {
	
	
}

public Coursetopic(String id, String name, String property,String topicID) {
	super();
	this.id = id;
	this.name = name;
	this.property = property;
	this.topic=new topic(topicID,"","");
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

public topic getTopic() {
	return topic;
}

public void setTopic(topic topic) {
	this.topic = topic;
}


}
