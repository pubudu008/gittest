package com.example.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.models.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long>{

}
