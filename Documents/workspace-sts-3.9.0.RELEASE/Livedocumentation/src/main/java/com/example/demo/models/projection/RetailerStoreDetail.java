package com.example.demo.models.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.demo.models.RetailerStore;

public interface RetailerStoreDetail {
	
	
	@Projection(name="retailerStores-name",types=RetailerStore.class)
	interface RetailerStoreProjection{
		String getAddress();
		Long getZip();
		String getMallName();
		String getMallAddress();
		String getMallRemarks();
		Boolean getActivated();
		
		@Value("#{target.state == null ? 'notfound' : target.state.name}")
		String getState();
		
		@Value("#{target.city == null ? 'notfound':target.city.name}")
		String getCity();
	}
	
}
