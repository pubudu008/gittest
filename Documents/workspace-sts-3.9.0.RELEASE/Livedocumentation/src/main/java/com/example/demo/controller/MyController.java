package com.example.demo.controller;

import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Model;
import com.example.demo.service.ModelService;

@RestController
public class MyController {
	
	private @Inject ModelService modelService;
	
	@RequestMapping("api/model")
public HashMap<String, Model> getModels() {
	
	return modelService.getModels(); 
}

	
	@RequestMapping("api/model/{name}")
public Model getModelByName(@PathVariable String name) {
	
	return modelService.getModelByName(name);
}

}


