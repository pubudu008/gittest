package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class controller {
	@Autowired
	private topicServices topicServices;
	
	@RequestMapping("/Hello")
	public String Hello() {
	
		
		return "hiiiii";
		
	}
	@RequestMapping("/topics")
	public List<topic> getAllTopic(){
		
		return topicServices.AllTopics();
	}
	
	@RequestMapping("/topics/{id}")
public topic getOneTopic(@PathVariable String id) {
	
	return topicServices.OneTopic(id);
			
}
	@RequestMapping(method=RequestMethod.POST,value="/topics")
	public void addtopic(@RequestBody topic tp) {
		
		topicServices.addTopic(tp);
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/topics/{id}")
	public void deleteData(@PathVariable String id) {
		
	topicServices.delteTopic(id);	
	}
	@RequestMapping(method=RequestMethod.PUT,value="/topics/{id}")
	public void updateData(@RequestBody topic tp,@PathVariable String id) {
		
	topicServices.updateTopic(tp, id);
	}
	
}
