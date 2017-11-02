package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRevisionRepository;

@RestController
@RequestMapping("/api/users/history")
public class UserController {
	@Autowired
	private UserRevisionRepository userRevisionRepository;

	@RequestMapping("revisions/{userId}")
	public List<User> getUserRevisions(@PathVariable Long userId) {

		return userRevisionRepository.listUserRevisions(userId);

	}
		

}
