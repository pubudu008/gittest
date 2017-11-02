package com.example.demo.model.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.demo.model.User;

public interface UserProjections {

	@Projection(name = "company-name", types = User.class)
	interface CompanyNameProjection {
		String getFirstName();
		String getLastName();
		@Value("#{target.company == null ? 'notfound' : target.company.name}")
		String getCompanyName();
		
		@Value("#{target.company==null?'not Founded':target.company.email}")
		String getEmail();

		

	}

}
