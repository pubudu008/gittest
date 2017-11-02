package com.hrandika.polymer.repository.core;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.hrandika.polymer.model.core.User;
import com.hrandika.polymer.model.core.projections.UserProjections;

@Repository
@RepositoryRestResource(excerptProjection = UserProjections.class)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findFirstByUserName(String userName);

	Optional<User> findOneByUserName(@Param("userName") String userName);

	Optional<User> findOneByEmail(@Param("email") String mail);

	List<User> findAllByRolesNameAndSubModulesNameAndActivatedOrderByUserName(@Param("r") String role,
			@Param("s") String subModule, @Param("a") Boolean active);

	List<User> findAllByRolesNameAndSubModulesNameOrderByUserName(@Param("role") String role,
			@Param("subModule") String subModule);

	List<User> findAllByUserNameIn(@Param("userNames") List<String> userNames);

} // End UserRepository