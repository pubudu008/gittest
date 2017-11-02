package com.example.demo.models.projection;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.Role;

public interface RoleDetail {
	
	@Projection(name="roles-name",types=Role.class)
	interface RoleProjection{
	String getName();	
	}

}
