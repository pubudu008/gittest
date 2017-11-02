package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.models.State;

public interface StateRepository extends PagingAndSortingRepository<State, Long>{

}
