package com.rmacnc.demo.topics;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, String>{
	
	//Topic findFirstByName(String name);
	
	//ArrayList<Topic> findAllByName(String name);

}
