package com.hrandika.spring.care;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hrandika.spring.care.utils.ProfileUtils;

@SpringBootApplication
public class CareApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CareApplication.class);
		// Change the default profile to "dev" insted of "default".
		ProfileUtils.setAsDev(app);
		app.run(args);
	}// End main

}// End class