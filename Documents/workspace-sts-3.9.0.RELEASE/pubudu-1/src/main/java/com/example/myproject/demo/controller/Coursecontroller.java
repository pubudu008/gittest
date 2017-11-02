package com.example.myproject.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject.demo.model.Coursetopic;
import com.example.myproject.demo.model.topic;
import com.example.myproject.demo.services.CoursetopicServices;


@RestController
public class Coursecontroller {
	@Autowired
	private CoursetopicServices CoursetopicServices;
	
	@RequestMapping("/topics/{id}/couses")
	public List<Coursetopic> CoursegetAllTopic(@PathVariable String id){
		
		return CoursetopicServices.CourseAllTopics(id);
	}
	
	@RequestMapping("/topics/{topicID}/couses/{id}")
public Coursetopic CoursegetOneTopic(@PathVariable String id) {
	
	return CoursetopicServices.CourseOneTopic(id);
			
}
	@RequestMapping(method=RequestMethod.POST,value="/topics/{topicID}/couses")
	public void Courseaddtopic(@RequestBody Coursetopic tp,@PathVariable String topicID) {
		tp.setTopic(new topic(topicID,"",""));
		CoursetopicServices.CourseaddTopic(tp);
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/topics/{topicID}/couses/{id}")
	public void CoursedeleteData(@PathVariable String id) {
		
		CoursetopicServices.CoursedelteTopic(id);	
	}
	@RequestMapping(method=RequestMethod.PUT,value="/topics/{topicID}/couses/{id}")
	public void CourseupdateData(@RequestBody Coursetopic tp,@PathVariable String topicID,@PathVariable String id) {
		tp.setTopic(new topic(topicID,"",""));
		
		CoursetopicServices.CourseupdateTopic(tp);
	}
	@RequestMapping(method=RequestMethod.PATCH,value="/topics/{topicID}/couses/{id}")
	public void CoursepatchUpdate(@RequestBody Coursetopic tp,@PathVariable String topicID,@PathVariable String id) {
	tp.setTopic(new topic(topicID,"",""));
		CoursetopicServices.CoursepatchUpdatetopic(tp,id);
		
	}
}

