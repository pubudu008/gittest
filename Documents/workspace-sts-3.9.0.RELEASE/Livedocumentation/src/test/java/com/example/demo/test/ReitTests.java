package com.example.demo.test;

import static org.hamcrest.Matchers.is;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

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

import com.example.demo.models.Reit;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.ReitRepository;
import com.example.demo.repository.RetailerStoreRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StateRepository;
import com.example.demo.repository.TickerRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReitTests {
	
	@Test
	public void contextLoads() {
	}
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private RoleRepository roleRepository;

	
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ReitRepository reitRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private RetailerStoreRepository retailerStoreRepository;
	@Autowired
	private TickerRepository tickerRepository;

	
	
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
	
	
	
	/*
	  =========================Reit entity==============================
	  
	 @author Pubudu Sanadaruwan
	  @throws Exception
	 


	  List Reit example
	  
	 @author Pubudu Sanadaruwan
	 @throws Exception
	 */


	@Test
	public void reitListExample() throws Exception {
		this.reitRepository.deleteAll();

		createReit("Jafna");
		createReit("Trinco");

		
		
		this.mockMvc.perform(get("/api/reits"))
			.andExpect(status().isOk())
			.andDo(document("reits-list-example",
					links(
							linkWithRel("self").description("Canonical link for this resource"),
							linkWithRel("profile").description("The ALPS profile for this resource")),
					responseFields(
							subsectionWithPath("_embedded.reits").description("An array of <<resources-reits, Reit resources>>"),
							subsectionWithPath("page").description("pagination"),
							subsectionWithPath("_links").description("<<resources-reits-list-links, Links>> to other resources"))));
	}

	private void createReit(String name) {
		Reit reit=new Reit();
		reit.setName(name);
		
		this.reitRepository.save(reit);
		
	}


	/*
	  Creating Reit example
	  
	  @author Pubudu Sanadaruwan
	  @throws Exception
	 */



	@Test
	public void reitCreateExample() throws Exception {
		
			
				Map<String, Object> reits = new HashMap<String, Object>();
		
				reits.put("name", "kagalle");
				            
		this.mockMvc.perform(
				post("/api/reits").contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(reits))).andExpect(
				status().isCreated())
				.andDo(document("reits-create-example",
						requestFields(
								fieldWithPath("name").description("The name of the reits"))));

	}

	/*

	 Getting Reit example
	 
	 @author Pubudu Sanadaruwan
	 @throws Exception
	 */


	@Test
	public void reitGetExample() throws Exception {
		Map<String, Object> reits = new HashMap<String, Object>();

		reits.put("name", "Thangalle");

	String reitLocation = this.mockMvc
	.perform(
			post("/api/reits").contentType(MediaTypes.HAL_JSON).content(
					this.objectMapper.writeValueAsString(reits)))
	.andExpect(status().isCreated()).andReturn().getResponse()
	.getHeader("Location");

		this.mockMvc.perform(get(reitLocation)).andExpect(status().isOk())
		.andExpect(jsonPath("name", is(reits.get("name"))))
		.andExpect(jsonPath("_links.self.href", is(reitLocation)))
			.andDo(print())
			.andDo(document("reits-get-example",
					links(
							linkWithRel("self").description("Canonical link for this <<resources-reit,reit>>"),
							linkWithRel("reit").description("This <<resources-reit,reit>>")),
							responseFields(
							fieldWithPath("name").description("The name of the reit"),
							subsectionWithPath("_links").description("<<resources-reit-links,Links>> to other resources"))));
	}





	/*
	  update Reit example
	 
	  @author Pubudu Sanadaruwan
	  @throws Exception
	 */



	@Test
	public void reitUpdateExample() throws Exception {

		Map<String, Object> reits = new HashMap<String, Object>();
		reits.put("name","mt.Lavinia");
	     
		String reitLocation = this.mockMvc
				.perform(
						post("/api/reits").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(reits)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		this.mockMvc.perform(get(reitLocation)).andExpect(status().isOk())
				.andExpect(jsonPath("name", is(reits.get("name"))))
				.andExpect(jsonPath("_links.self.href", is(reitLocation)));

				
		this.mockMvc.perform(
				patch(reitLocation).contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(reits)))
				.andExpect(status().isNoContent())
				.andDo(document("reits-update-example",
						requestFields(
								
								fieldWithPath("name").description("The name of the reit").type(JsonFieldType.STRING).optional())));
								
	}
}
