package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Property {
	
	private String name;
	private String type;
	private Boolean unique;
	private String reference;
	private String[] enums;
	private String relation;
private Boolean iggnoreOnRead;
private Integer max;
private Integer min;
private Boolean required;


}
