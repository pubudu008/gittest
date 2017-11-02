package com.example.myproject.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.demo.Repositories.topicRepository;
import com.example.myproject.demo.model.topic;

@Service
public class topicServices {
	
	@Autowired
	private topicRepository topicRepository;
	
//	private List<topic> topics=new ArrayList<>(Arrays.asList(
//			new topic("1", "pubudu", "Rathmalana"),
//			new topic("2","sasa","mt Lavinia"),
//			new topic("3", "Sahan", "Dehiwala")
//		));
	public List<topic> AllTopics(){
		List<topic> topics=new ArrayList<>();
		//return topics;
		topicRepository.findAll().forEach(topics::add);
		return topics;
		
	}
	public topic OneTopic(String id) {
		
		//return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
		return topicRepository.findOne(id);
		
	}
	public void addTopic(topic topic) {
	//topics.add(topic);
		topicRepository.save(topic);
	}
	
	public void delteTopic(String id) {
	
	//	topics.removeIf(t->t.getId().equals(id));	
		topicRepository.delete(id);
		
	}
	
	//update
	
	public void updateTopic(topic topic,String id) {
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
		
		topicRepository.save(topic);
	}
	public void patchUpdatetopic(topic tp, String id) {
	topicRepository.save(tp);
		
	}
	}


