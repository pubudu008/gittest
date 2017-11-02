package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.models.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

}
