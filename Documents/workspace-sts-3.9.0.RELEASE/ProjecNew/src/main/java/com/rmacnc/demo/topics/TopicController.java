package com.rmacnc.demo.topics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics(){
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST ,value = "/topics")
	public void addTopic(@RequestBody Topic topic) {
		File file= new File("/home/asitha/Documents/file", ".csv");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		topicService.addTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value = "/topics/{id}")
	public void updateTopic(@RequestBody Topic topic , @PathVariable String id) {
		topicService.updateTopic(id, topic);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value = "/topics/{id}")
	public void deletetopic(@PathVariable String id) {
		topicService.deleteTopc(id);
	}
	
	@RequestMapping(method=RequestMethod.GET ,value = "/create-file")
	public void createFile(@RequestParam("file") String fileName , HttpServletResponse response) {
		
		topicService.createFile(fileName , response);
	}
	
	
	
}
