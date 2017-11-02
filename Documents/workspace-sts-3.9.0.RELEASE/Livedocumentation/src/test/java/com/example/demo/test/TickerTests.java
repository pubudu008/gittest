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
import com.example.demo.models.Ticker;
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
public class TickerTests {
	
	
	
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
=========================Ticker entity==============================
  
  @author Pubudu Sanadaruwan
  @throws Exception
 


 List Ticker example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */


@Test
public void tickerListExample() throws Exception {
	this.tickerRepository.deleteAll();

	createTicker("panadura");
	createTicker("moratuwa");

	
	
	this.mockMvc.perform(get("/api/tickers"))
		.andExpect(status().isOk())
		.andDo(document("tickers-list-example",
				links(
						linkWithRel("self").description("Canonical link for this resource"),
						linkWithRel("profile").description("The ALPS profile for this resource")),
				responseFields(
						subsectionWithPath("_embedded.tickers").description("An array of <<resources-tickers, Ticker resources>>"),
						subsectionWithPath("page").description("pagination"),
						subsectionWithPath("_links").description("<<resources-tickers-list-links, Links>> to other resources"))));
}

private void createTicker(String name) {
	Ticker ticker=new Ticker();
	ticker.setName(name);
	
	this.tickerRepository.save(ticker);
	
}


/*
  Creating Ticker example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */



@Test
public void tickerCreateExample() throws Exception {
	
		
			Map<String, Object> tickers = new HashMap<String, Object>();
	
			tickers.put("name", "thunmulle");
			            
	this.mockMvc.perform(
			post("/api/tickers").contentType(MediaTypes.HAL_JSON).content(
					this.objectMapper.writeValueAsString(tickers))).andExpect(
			status().isCreated())
			.andDo(document("tickers-create-example",
					requestFields(
							fieldWithPath("name").description("The name of the tickers"))));

}


/*
  Getting Ticker example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */


@Test
public void tickerGetExample() throws Exception {
	Map<String, Object> tickers = new HashMap<String, Object>();

	tickers.put("name", "mathugama");

String tickerLocation = this.mockMvc
.perform(
		post("/api/tickers").contentType(MediaTypes.HAL_JSON).content(
				this.objectMapper.writeValueAsString(tickers)))
.andExpect(status().isCreated()).andReturn().getResponse()
.getHeader("Location");

	this.mockMvc.perform(get(tickerLocation)).andExpect(status().isOk())
	.andExpect(jsonPath("name", is(tickers.get("name"))))
	.andExpect(jsonPath("_links.self.href", is(tickerLocation)))
		.andDo(print())
		.andDo(document("tickers-get-example",
				links(
						linkWithRel("self").description("Canonical link for this <<resources-ticker,ticker>>"),
						linkWithRel("ticker").description("This <<resources-ticker,ticker>>")),
						responseFields(
						fieldWithPath("name").description("The name of the ticker"),
						subsectionWithPath("_links").description("<<resources-ticker-links,Links>> to other resources"))));
}




/*
  update Ticker example
  
  @author Pubudu Sanadaruwan
  @throws Exception
 */



@Test
public void tickerUpdateExample() throws Exception {

	Map<String, Object> tickers = new HashMap<String, Object>();
	tickers.put("name","maxxa");
     
	String tickerLocation = this.mockMvc
			.perform(
					post("/api/tickers").contentType(MediaTypes.HAL_JSON).content(
							this.objectMapper.writeValueAsString(tickers)))
			.andExpect(status().isCreated()).andReturn().getResponse()
			.getHeader("Location");

	this.mockMvc.perform(get(tickerLocation)).andExpect(status().isOk())
			.andExpect(jsonPath("name", is(tickers.get("name"))))
			.andExpect(jsonPath("_links.self.href", is(tickerLocation)));

			
	this.mockMvc.perform(
			patch(tickerLocation).contentType(MediaTypes.HAL_JSON).content(
					this.objectMapper.writeValueAsString(tickers)))
			.andExpect(status().isNoContent())
			.andDo(document("tickers-update-example",
					requestFields(
							
							fieldWithPath("name").description("The name of the ticker").type(JsonFieldType.STRING).optional())));
							
}





}
