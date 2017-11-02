package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class topicServices {
	private List<topic> topics=new ArrayList<>(Arrays.asList(
			new topic("1", "pubudu", "Rathmalana"),
			new topic("2","sasa","mt Lavinia"),
			new topic("3", "Sahan", "Dehiwala")
		));
	public List<topic> AllTopics(){
		
		return topics;
	}
	public topic OneTopic(String id) {
		
		return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
	}
	public void addTopic(topic topic) {
	topics.add(topic);
		
	}
	
	public void delteTopic(String id) {
	
		topics.removeIf(t->t.getId().equals(id));		
		
	}
	
	//update
	
	public void updateTopic(topic topic,String id) {
		for(int x=0;x<topics.size();x++) {
			topic t=topics.get(x);
			
			if(t.getId().equals(id)) {
				
				topics.add(x, topic);
			return;
		}
		
		
	}
	}
	}


