package com.example.demo.models.projection;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.City;

public interface CityDetail {
	
	@Projection(name="cities-name",types=City.class)
		interface CityProjection{
			
			String getName();
		}

}
