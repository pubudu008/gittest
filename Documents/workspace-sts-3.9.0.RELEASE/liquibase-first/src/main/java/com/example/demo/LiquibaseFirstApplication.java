package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiquibaseFirstApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(LiquibaseFirstApplication.class);
		utils.ProfileUtils.setAsDev(app);
		app.run(args);
	}
}
