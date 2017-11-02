package com.hrandika.polymer.repository.core;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hrandika.polymer.model.core.Role;

@Repository
public interface RoleRepository  extends PagingAndSortingRepository<Role,Long>{
	
	Role findOneByName(String name);

} // End RoleRepository
