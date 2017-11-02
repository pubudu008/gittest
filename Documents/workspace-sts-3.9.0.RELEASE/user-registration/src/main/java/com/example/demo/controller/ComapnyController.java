package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRevisionRepository;

@RestController
@RequestMapping("/api/companies")
public class ComapnyController {

@Autowired
private CompanyRevisionRepository companyRevisionRepository;

@RequestMapping("/history/{id}")
public List<Company> getRevision(@PathVariable Long id){
	
	return companyRevisionRepository.listRepository(id);
}
}
