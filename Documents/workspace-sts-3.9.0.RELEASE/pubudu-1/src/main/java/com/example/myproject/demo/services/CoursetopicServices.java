package com.example.myproject.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.demo.Repositories.CoursetopicRepository;
import com.example.myproject.demo.model.Coursetopic;

@Service
public class CoursetopicServices {
	
	@Autowired
	private CoursetopicRepository CoursetopicRepository;
	
//	private List<topic> topics=new ArrayList<>(Arrays.asList(
//			new topic("1", "pubudu", "Rathmalana"),
//			new topic("2","sasa","mt Lavinia"),
//			new topic("3", "Sahan", "Dehiwala")
//		));
	public List<Coursetopic> CourseAllTopics(String topicID){
		List<Coursetopic> Coursetopics=new ArrayList<>();
		//return topics;
		
		//CoursetopicRepository.findAll().forEach(Coursetopics::add);
		CoursetopicRepository.findByTopicId(topicID).forEach(Coursetopics::add);
		
		return Coursetopics;
		
	}
	public Coursetopic CourseOneTopic(String id) {
		
		//return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
		return CoursetopicRepository.findOne(id);
		
	}
	public void CourseaddTopic(Coursetopic topic) {
	//topics.add(topic);
		CoursetopicRepository.save(topic);
	}
	
	public void CoursedelteTopic(String id) {
	
	//	topics.removeIf(t->t.getId().equals(id));	
		CoursetopicRepository.delete(id);
		
	}
	
	//update
	
	public void CourseupdateTopic(Coursetopic topic) {
//		for(int x=0;x<topics.size();x++) {
//			topic t=topics.get(x);
//			
//			if(t.getId().equals(id)) {
//				
//				topics.add(x, topic);
//			return;
//		}
//		
//		
//	}
		
		CoursetopicRepository.save(topic);
	}
	public void CoursepatchUpdatetopic(Coursetopic tp, String id) {
		CoursetopicRepository.save(tp);
		
	}
	}


