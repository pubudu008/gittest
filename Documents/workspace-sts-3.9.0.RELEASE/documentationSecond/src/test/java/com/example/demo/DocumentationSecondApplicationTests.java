package com.example.demo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.models.Company;
import com.example.demo.models.Employee;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentationSecondApplicationTests {

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
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation)).build();
	}

	@Test
	public void errorExample() throws Exception {
		this.mockMvc
				.perform(get("/error")
						.requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
						.requestAttr(RequestDispatcher.ERROR_REQUEST_URI,
								"/companies")
						.requestAttr(RequestDispatcher.ERROR_MESSAGE,
								"The employee 'http://localhost:8081/employees/123' does not exist"))
				.andDo(print()).andExpect(status().isBadRequest())
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
	public void indexExample() throws Exception {
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andDo(document("index-example",
					links(
							linkWithRel("companies").description("The <<resources-companies,Companies resource>>"),
							linkWithRel("employees").description("The <<resources-employees,Employees resource>>"),
							linkWithRel("profile").description("The ALPS profile for the service")),
					responseFields(
							subsectionWithPath("_links").description("<<resources-index-links,Links>> to other resources"))));

	}
	@Test
	public void companiesListExample() throws Exception {
		this.companyRepository.deleteAll();

		createCompany("RS","RS@gmail.com");
		createCompany("INNODATA","innodata@gmail.com");

		this.mockMvc.perform(get("/companies"))
			.andExpect(status().isOk())
			.andDo(document("companies-list-example",
					links(
							linkWithRel("self").description("Canonical link for this resource"),
							linkWithRel("profile").description("The ALPS profile for this resource")),
					responseFields(
							subsectionWithPath("_embedded.companies").description("An array of <<resources-company, Company resources>>"),
							subsectionWithPath("page").description("pagination"),
							subsectionWithPath("_links").description("<<resources-tags-list-links, Links>> to other resources"))));
	}
	
	private void createCompany(String name, String email) {
		Company com=new Company();
		com.setEmail(email);
		com.setName(name);
		this.companyRepository.save(com);
		
	}
	@Test
	public void companiesCreateExample() throws Exception {
		Map<String, String> employee = new HashMap<String, String>();
		employee.put("name", "REST");

		String employeeLocation = this.mockMvc
				.perform(
						post("/employees").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		Map<String, Object> company = new HashMap<String, Object>();
		company.put("name", "RS");
		company.put("email", "RS@gmail.com");
		company.put("employees", Arrays.asList(employeeLocation));

		this.mockMvc.perform(
				post("/companies").contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(company))).andExpect(
				status().isCreated())
				.andDo(document("companies-create-example",
						requestFields(
									fieldWithPath("name").description("The name of the company"),
									fieldWithPath("email").description("The email of the company"),
									fieldWithPath("employees").description("An array of employee resource URIs"))));
	}
	
	
	@Test
	public void companyGetExample() throws Exception {
		Map<String, String> employee = new HashMap<String, String>();
		employee.put("name", "REST");

		String employeeLocation = this.mockMvc
				.perform(
						post("/employees").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		Map<String, Object> company = new HashMap<String, Object>();
		company.put("name", "RS");
		company.put("email", "RS@gmail.com");
		company.put("employees", Arrays.asList(employeeLocation));

		String companyLocation = this.mockMvc
				.perform(
						post("/companies").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(company)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		this.mockMvc.perform(get(companyLocation))
			.andExpect(status().isOk())
			.andExpect(jsonPath("name", is(company.get("name"))))
			.andExpect(jsonPath("email", is(company.get("email"))))
			.andExpect(jsonPath("_links.self.href", is(companyLocation)))
			.andExpect(jsonPath("_links.employee", is(notNullValue())))
			.andDo(print())
			.andDo(document("companies-get-example",
					links(
							linkWithRel("self").description("Canonical link for this <<resources-company,company>>"),
							linkWithRel("company").description("This <<resources-company,company>>"),
							linkWithRel("employee").description("This company's employees")),
					responseFields(
							fieldWithPath("name").description("The name of the company"),
							fieldWithPath("email").description("The email of the company"),
							subsectionWithPath("_links").description("<<resources-company-links,Links>> to other resources"))));
	}
	

	@Test
	public void companyUpdateExample() throws Exception {
		Map<String, Object> company = new HashMap<String, Object>();
		company.put("name", "RS");
		company.put("email", "RS@gmail.com");

		String companyLocation = this.mockMvc
				.perform(
						post("/companies").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(company)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		this.mockMvc.perform(get(companyLocation)).andExpect(status().isOk())
				.andExpect(jsonPath("name", is(company.get("name"))))
				.andExpect(jsonPath("email", is(company.get("email"))))
				.andExpect(jsonPath("_links.self.href", is(companyLocation)))
				.andExpect(jsonPath("_links.employee", is(notNullValue())));

		Map<String, String> employee = new HashMap<String, String>();
		employee.put("name", "REST");

		String employeeLocation = this.mockMvc
				.perform(
						post("/employees").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		Map<String, Object> companyUpdate = new HashMap<String, Object>();
		companyUpdate.put("employees", Arrays.asList(employeeLocation));

		this.mockMvc.perform(
				patch(companyLocation).contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(companyUpdate)))
				.andExpect(status().isNoContent())
				.andDo(document("companies-update-example",
						requestFields(
								fieldWithPath("name").description("The name of the company").type(JsonFieldType.STRING).optional(),
								fieldWithPath("email").description("The email of the company").type(JsonFieldType.STRING).optional(),
								fieldWithPath("employees").description("An array of employee resource URIs").optional())));
	}


	
	
	
	
	

	@Test
	public void employeesListExample() throws Exception {
		this.employeeRepository.deleteAll();
		createEmployee("pubudu","perera");
		createEmployee("sahan","danusha");

		this.mockMvc.perform(get("/employees"))
			.andExpect(status().isOk())
			.andDo(document("employees-list-example",
					links(
							linkWithRel("self").description("Canonical link for this resource"),
							linkWithRel("profile").description("The ALPS profile for this resource")),
					responseFields(
							subsectionWithPath("_embedded.employees").description("An array of <<resources-employee,Employee resources>>"),
							subsectionWithPath("page").description("pagination"),
							subsectionWithPath("_links").description("<<resources-employees-list-links, Links>> to other resources"))));
	}

	private void createEmployee(String fname, String lname) {
		
Employee emp=new Employee();
emp.setFname(fname);
emp.setLname(lname);
this.employeeRepository.save(emp);
		
	}


}
