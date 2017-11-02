package com.hrandika.polymer.repository.core;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hrandika.polymer.model.core.Company;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

}
