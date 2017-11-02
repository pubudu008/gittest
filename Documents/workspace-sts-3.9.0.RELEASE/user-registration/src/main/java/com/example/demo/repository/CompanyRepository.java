package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Company;
@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

}
