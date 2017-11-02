package com.rmacnc.demo.topics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class TopicService {
	@Autowired
	private TopicRepository topicRepository;
	
	public List<Topic> getAllTopics()
{
		List<Topic> topics =new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		return topics;
}
	public Topic getTopic(String id) {
		return topicRepository.findOne(id);
	}
	
	public void addTopic(Topic topic) {
		topicRepository.save(topic);
	}
	public void updateTopic(String id, Topic topic) {
		topicRepository.save(topic);
	}
	
	public void deleteTopc(String id) {
		topicRepository.delete(id);
	}
	
	public void createFile(String fileName, HttpServletResponse response) {

		File file= new File("/home/asitha/Documents", fileName + ".csv");
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("Name" + "," + "Age" + "," + "Address");
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileWriter fw =new FileWriter(file);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream in;
		try {
			in = new FileInputStream(file);
		

       
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        
			FileCopyUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
