package com.example.demo.models.projection;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.Ticker;

public interface TickerDetail {
	
	
		@Projection(name="tickers-name",types=Ticker.class)
		interface TickerProjection{
			
			String getName();
		}

	}
