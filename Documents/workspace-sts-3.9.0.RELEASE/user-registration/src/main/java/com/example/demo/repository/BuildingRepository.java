package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.Building;

public interface BuildingRepository extends PagingAndSortingRepository<Building, Long> {

}
