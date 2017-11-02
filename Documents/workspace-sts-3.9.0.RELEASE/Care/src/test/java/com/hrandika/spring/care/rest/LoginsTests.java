package com.hrandika.spring.care.rest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hrandika.spring.care.models.Login;
import com.hrandika.spring.care.models.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginsTests {

	public @Rule final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	private @Autowired WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation)).build();
	}

	private void createLogin(String userName, String password, String email, Set<Role> roles) {
		Login login = new Login(userName, password, email, true, roles);
	}

}
