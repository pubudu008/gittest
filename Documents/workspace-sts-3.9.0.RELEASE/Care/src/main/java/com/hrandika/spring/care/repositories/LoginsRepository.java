package com.hrandika.spring.care.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hrandika.spring.care.models.Login;

@Repository
public interface LoginsRepository extends PagingAndSortingRepository<Login, Long> {

}