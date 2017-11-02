package com.example.demo.models.projection;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.User;

public interface UserDetail {
	
	@Projection(name = "users-name", types = User.class)
	interface UserNameProjection {
		Boolean getActivated();
		String getUserName();
		String getEmail();
		
	}


}
