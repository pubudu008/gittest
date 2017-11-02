package com.example.demo.dto;

import com.example.demo.model.Property;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProjectionDto {
private String name;
private Property[] properties;
}
