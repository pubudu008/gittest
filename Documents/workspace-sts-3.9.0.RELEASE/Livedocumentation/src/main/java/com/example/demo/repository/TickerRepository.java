package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.models.Ticker;

public interface TickerRepository extends PagingAndSortingRepository<Ticker, Long>{

}
