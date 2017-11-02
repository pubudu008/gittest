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

import com.example.demo.models.Role;
import com.example.demo.models.State;
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
public class StateTests {

	
	
	
	
	
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
  =========================State entity==============================
  
  @author Pubudu Sanadaruwan
  @throws Exception
 


  List State example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */


@Test
public void stateListExample() throws Exception {
	this.stateRepository.deleteAll();

	createState("aasds","aaaa");
	createState("yyyyy","wwwww");

	
	
	this.mockMvc.perform(get("/api/states"))
		.andExpect(status().isOk())
		.andDo(document("states-list-example",
				links(
						linkWithRel("self").description("Canonical link for this resource"),
						linkWithRel("profile").description("The ALPS profile for this resource")),
				responseFields(
						subsectionWithPath("_embedded.states").description("An array of <<resources-states, Ticker resources>>"),
						subsectionWithPath("page").description("pagination"),
						subsectionWithPath("_links").description("<<resources-states-list-links, Links>> to other resources"))));
}

private void createState(String name,String abbr) {
	State state=new State();
	state.setName(name);
	state.setAbbr(abbr);
	
	this.stateRepository.save(state);
	
}


/*
  Creating State example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */



@Test
public void stateCreateExample() throws Exception {
	
		
			Map<String, Object> states = new HashMap<String, Object>();
	
			states.put("name", "zzzzzz");
			states.put("abbr", "xxxxx");
			
			            
	this.mockMvc.perform(
			post("/api/states").contentType(MediaTypes.HAL_JSON).content(
					this.objectMapper.writeValueAsString(states))).andExpect(
			status().isCreated())
			.andDo(document("states-create-example",
					requestFields(
							fieldWithPath("name").description("The name of the states"),
					fieldWithPath("abbr").description("The abbr of the states"))));

}


/*
  Getting State example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */


@Test
public void stateGetExample() throws Exception {
	Map<String, Object> states = new HashMap<String, Object>();

	states.put("name", "mathugamaaaaaa");
	states.put("abbr", "cccccccc");

String stateLocation = this.mockMvc
.perform(
		post("/api/states").contentType(MediaTypes.HAL_JSON).content(
				this.objectMapper.writeValueAsString(states)))
.andExpect(status().isCreated()).andReturn().getResponse()
.getHeader("Location");

	this.mockMvc.perform(get(stateLocation)).andExpect(status().isOk())
	.andExpect(jsonPath("name", is(states.get("name"))))
	.andExpect(jsonPath("abbr", is(states.get("abbr"))))
	.andExpect(jsonPath("_links.self.href", is(stateLocation)))
		.andDo(print())
		.andDo(document("states-get-example",
				links(
						linkWithRel("self").description("Canonical link for this <<resources-state,state>>"),
						linkWithRel("state").description("This <<resources-state,state>>")),
						responseFields(
						fieldWithPath("name").description("The name of the state"),
						fieldWithPath("abbr").description("The abbr of the state"),
						subsectionWithPath("_links").description("<<resources-state-links,Links>> to other resources"))));
}




/*
  update State example
  
  @author Pubudu Sanadaruwan
 @throws Exception
 */



@Test
public void stateUpdateExample() throws Exception {

	Map<String, Object> states = new HashMap<String, Object>();
	states.put("name","mmmmmmm");
	states.put("abbr","okkkkk"); 
	String stateLocation = this.mockMvc
			.perform(
					post("/api/states").contentType(MediaTypes.HAL_JSON).content(
							this.objectMapper.writeValueAsString(states)))
			.andExpect(status().isCreated()).andReturn().getResponse()
			.getHeader("Location");

	this.mockMvc.perform(get(stateLocation)).andExpect(status().isOk())
			.andExpect(jsonPath("name", is(states.get("name"))))
			.andExpect(jsonPath("abbr", is(states.get("abbr"))))
			.andExpect(jsonPath("_links.self.href", is(stateLocation)));

			
	this.mockMvc.perform(
			patch(stateLocation).contentType(MediaTypes.HAL_JSON).content(
					this.objectMapper.writeValueAsString(states)))
			.andExpect(status().isNoContent())
			.andDo(document("states-update-example",
					requestFields(
							
							fieldWithPath("name").description("The name of the state").type(JsonFieldType.STRING).optional(),
	fieldWithPath("abbr").description("The abbr of the state").type(JsonFieldType.STRING).optional())));
	
}

		

}
