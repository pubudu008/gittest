package com.example.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.models.User;

public interface UserRepository extends PagingAndSortingRepository<User,Long>{

}
