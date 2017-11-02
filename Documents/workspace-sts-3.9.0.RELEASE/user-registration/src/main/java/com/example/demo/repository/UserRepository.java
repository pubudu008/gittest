package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Company;
import com.example.demo.model.User;
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	List<User> findAllByCompanyBuildingName(@Param("bName") String name);
User findByCompany(Company com);
}
