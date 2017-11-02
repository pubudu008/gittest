package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.RetailerStore;
import com.example.demo.repository.RetailerStoreRepository;

@RestController
@RequestMapping("/api/retailerStores")
public class RetailerStoreController {
	private RetailerStoreRepository retailerStoreRepository;
	
	
	public ArrayList<RetailerStore> getRetailStore(){
		ArrayList<RetailerStore> retailerStores=(ArrayList<RetailerStore>) retailerStoreRepository.findAll();
		
		return retailerStores;
		
	}
	

}
