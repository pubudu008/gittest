package com.example.myproject.demo.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.myproject.demo.model.Coursetopic;

public interface CoursetopicRepository extends CrudRepository<Coursetopic,String>{
	public List<Coursetopic> findByTopicId(String topicID);

}
