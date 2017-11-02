package com.rmacnc.demo.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	@Autowired
	private CourseRepository topicRepository;
	
	public List<Course> getAllCourses(String topicId)
{
		List<Course> Course =new ArrayList<>();
		topicRepository.findByTopicId(topicId)
		.forEach(Course::add);
		return Course;
}
	public Course getCourse(String id) {
		return topicRepository.findOne(id);
	}
	
	public void addCourse(Course Course) {
		topicRepository.save(Course);
	}
	public void updateCourse( Course Course) {
		topicRepository.save(Course);
	}
	
	public void deleteCourse(String id) {
		topicRepository.delete(id);
	}
}
