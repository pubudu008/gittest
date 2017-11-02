package com.example.demo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.RequestDispatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentationRestApi1ApplicationTests {
	
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc=MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation)).build();
		
	}
	
	@Test
	public void errorExample() throws Exception {
	this.mockMvc.perform(get("/error").requestAttr(RequestDispatcher.ERROR_STATUS_CODE,400).requestAttr(RequestDispatcher.ERROR_REQUEST_URI, "/companies").requestAttr(RequestDispatcher.ERROR_MESSAGE,
			"The tag 'http://localhost:8080/companies/123' does not exist")).andDo(print()).andExpect(status().isBadRequest())
			.andExpect(jsonPath("error", is("Bad Request")))
			.andExpect(jsonPath("timestamp", is(notNullValue())))
			.andExpect(jsonPath("status", is(400)))
			.andExpect(jsonPath("path", is(notNullValue())))
			.andDo(document("error-example",
					responseFields(
							fieldWithPath("error").description("The HTTP error that occurred, e.g. `Bad Request`"),
							fieldWithPath("message").description("A description of the cause of the error"),
							fieldWithPath("path").description("The path to which the request was made"),
							fieldWithPath("status").description("The HTTP status code, e.g. `400`"),
							fieldWithPath("timestamp").description("The time, in milliseconds, at which the error occurred"))));

			
	}
	@Test
	public void indexExample() throws Exception{

		this.mockMvc.perform(get("/")).andExpect(status().isOk()).andDo(document("index-example",
				links(
						linkWithRel("companies").description("The <<resources-companies,Companies resource>>"),
						linkWithRel("employees").description("The <<resources-employee,Employees resource>>"),
						linkWithRel("profile").description("The ALPS profile for the service")),
				responseFields(
						subsectionWithPath("_links").description("resources-index-links,Links to other resources"))));

	}

	
}





