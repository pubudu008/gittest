package com.hrandika.spring.care.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hrandika.spring.care.models.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

}