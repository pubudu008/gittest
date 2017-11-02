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

import com.example.demo.models.City;
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
public class CityTests {
	
	
	
	@Test
	public void contextLoads() {
	}
	

	
	
	
	
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
  =========================City entity==============================
  
  @author Pubudu Sanadaruwan
  @throws Exception
 


 List City example
  
 @author Pubudu Sanadaruwan
 @throws Exception
 */


@Test
public void cityListExample() throws Exception {
	this.cityRepository.deleteAll();

	createCity("Rathmalana");
	createCity("Dehiwala");

	
	
	this.mockMvc.perform(get("/api/cities"))
		.andExpect(status().isOk())
		.andDo(document("cities-list-example",
				links(
						linkWithRel("self").description("Canonical link for this resource"),
						linkWithRel("profile").description("The ALPS profile for this resource")),
				responseFields(
						subsectionWithPath("_embedded.cities").description("An array of <<resources-cities, City resources>>"),
						subsectionWithPath("page").description("pagination"),
						subsectionWithPath("_links").description("<<resources-cities-list-links, Links>> to other resources"))));
}

private void createCity(String name) {
	City city=new City();
	city.setName(name);
	
	this.cityRepository.save(city);
	
}


/*
  Creating City example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */



@Test
public void cityCreateExample() throws Exception {
	
		
			Map<String, Object> cities = new HashMap<String, Object>();
	
			cities.put("name", "Galle");
			            
	this.mockMvc.perform(
			post("/api/cities").contentType(MediaTypes.HAL_JSON).content(
					this.objectMapper.writeValueAsString(cities))).andExpect(
			status().isCreated())
			.andDo(document("cities-create-example",
					requestFields(
							fieldWithPath("name").description("The name of the cities"))));

}


/*
 Getting City example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */


@Test
public void cityGetExample() throws Exception {
	Map<String, Object> cities = new HashMap<String, Object>();

cities.put("name", "Mathara");

String cityLocation = this.mockMvc
.perform(
		post("/api/cities").contentType(MediaTypes.HAL_JSON).content(
				this.objectMapper.writeValueAsString(cities)))
.andExpect(status().isCreated()).andReturn().getResponse()
.getHeader("Location");

	this.mockMvc.perform(get(cityLocation)).andExpect(status().isOk())
	.andExpect(jsonPath("name", is(cities.get("name"))))
	.andExpect(jsonPath("_links.self.href", is(cityLocation)))
		.andDo(print())
		.andDo(document("cities-get-example",
				links(
						linkWithRel("self").description("Canonical link for this <<resources-city,city>>"),
						linkWithRel("city").description("This <<resources-city,city>>")),
						responseFields(
						fieldWithPath("name").description("The name of the city"),
						subsectionWithPath("_links").description("<<resources-city-links,Links>> to other resources"))));
}





/*
 update City example
  
 @author Pubudu Sanadaruwan
 @throws Exception
 */



@Test
public void cityUpdateExample() throws Exception {

	Map<String, Object> cities = new HashMap<String, Object>();
	  cities.put("name","Hambanthota");
     
	String cityLocation = this.mockMvc
			.perform(
					post("/api/cities").contentType(MediaTypes.HAL_JSON).content(
							this.objectMapper.writeValueAsString(cities)))
			.andExpect(status().isCreated()).andReturn().getResponse()
			.getHeader("Location");

	this.mockMvc.perform(get(cityLocation)).andExpect(status().isOk())
			.andExpect(jsonPath("name", is(cities.get("name"))))
			.andExpect(jsonPath("_links.self.href", is(cityLocation)));

			
	this.mockMvc.perform(
			patch(cityLocation).contentType(MediaTypes.HAL_JSON).content(
					this.objectMapper.writeValueAsString(cities)))
			.andExpect(status().isNoContent())
			.andDo(document("cities-update-example",
					requestFields(
							
							fieldWithPath("name").description("The name of the city").type(JsonFieldType.STRING).optional())));
							
}









}
