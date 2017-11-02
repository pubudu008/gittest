package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.Car;

public interface CarRepository extends PagingAndSortingRepository<Car,Long>{

}
