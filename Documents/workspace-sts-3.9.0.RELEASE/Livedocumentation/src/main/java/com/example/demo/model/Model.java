package com.example.demo.model;

import java.util.List;

import com.example.demo.dto.ProjectionDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model {

	String name;
	String packageName;
	String endPoint;
	List<Property> properties;
	List<ProjectionDto> projections;
	
}