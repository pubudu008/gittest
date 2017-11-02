package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;


//@PreAuthorize("hasRole('ADMIN')")
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	Optional<User> findOneByUserName(@Param("userName") String userName);
	
	User findFirstByUserName(String userName);

}
