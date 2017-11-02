package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.models.Reit;

public interface ReitRepository extends PagingAndSortingRepository<Reit, Long> {

}
