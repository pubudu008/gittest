package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.models.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

}
