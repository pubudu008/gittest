package com.example.demo.models.projection;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.State;

public interface StateDetail {

	

		@Projection(name="states-name",types=State.class)
		interface StateProjection{
			
			String getName();
			String getAbbr();
		}
}
