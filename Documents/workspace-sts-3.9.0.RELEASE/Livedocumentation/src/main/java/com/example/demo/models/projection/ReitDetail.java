package com.example.demo.models.projection;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.Reit;

public interface ReitDetail {

	@Projection(name="reits-name",types=Reit.class)
	interface ReitProjection{
		
		String getName();
	}
}
