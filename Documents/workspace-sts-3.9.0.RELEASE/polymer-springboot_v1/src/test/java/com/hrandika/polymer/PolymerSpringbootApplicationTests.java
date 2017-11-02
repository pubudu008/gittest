package com.hrandika.polymer;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hrandika.polymer.model.core.User;
import com.hrandika.polymer.repository.core.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PolymerSpringbootApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Inject
	private UserRepository userRepository;
	
	@Test
	public void loadAdminUser(){
		User admin = userRepository.findFirstByUserName("admin");
		if (admin == null) {
			admin = new User();
			admin.setUserName("admin");
			admin.setPassword("admin");
			admin.setActivated(true);
			admin.setEmail("admin@admin.com");
			userRepository.save(admin);
			log.info("Saving admin with default password:admin");
		}
	}

}
