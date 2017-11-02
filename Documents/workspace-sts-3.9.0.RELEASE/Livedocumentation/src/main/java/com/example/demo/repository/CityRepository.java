package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.City;
@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

}
