package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.models.RetailerStore;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.ReitRepository;
import com.example.demo.repository.RetailerStoreRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StateRepository;
import com.example.demo.repository.TickerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.test.dto.AccessTokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LivedocumentationApplicationTests {

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

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).addFilter(springSecurityFilterChain)
				.apply(documentationConfiguration(this.restDocumentation)).build();
	}

	@Test
	public void userData() throws Exception {
		Role role = roleRepository.findFirstByName("ROLE_ADMIN");
		Role rolle = roleRepository.findFirstByName("ROLE_USER");

		Set<Role> roles = new HashSet<>();
		Role roless = new Role();
		if (role == null) {
			roless.setName("ROLE_ADMIN");
			roleRepository.save(roless);
		}
		Role roless2 = new Role();
		if (rolle == null) {
			roless2.setName("ROLE_USER");
			roleRepository.save(roless2);
		}
		roles.add(roless);
		roles.add(roless2);

		User user = userRepository.findFirstByUserName("admin");
		if (user == null) {
			user = new User();
			user.setUserName("admin");
			user.setPassword("admin");
			user.setEmail("pubudu@gmail.com");
			user.setActivated(true);

			user.setRoles(roles);
			userRepository.save(user);
		}
		// String ACCESSTOCKEN =getAccessToken("admin", "admin");
		// System.err.println("Token : " + ACCESSTOCKEN);
	}

	private String getAccessToken(String username, String password) throws Exception {

		String authorization = "Basic " + new String(Base64Utils.encode("bull:bullPassword".getBytes()));
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";

		// @formatter:off

		String content = mockMvc
				.perform(post("/api/oauth/token").header("Authorization", authorization)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", username)
						.param("password", password).param("grant_type", "password").param("scope", "read write")
						.param("client_id", "bull").param("client_secret", "bullPassword"))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
				.andExpect(jsonPath("$.refresh_token", is(notNullValue())))
				.andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
				.andExpect(jsonPath("$.scope", is(equalTo("read write")))).andReturn().getResponse()
				.getContentAsString();

		ObjectMapper mapper = new ObjectMapper();

		AccessTokenDto token = mapper.readValue(content, AccessTokenDto.class);
		return token.getAccess_token();
	}

	/*
	 * ==============================RetailerStore
	 * entity================================ error example
	 * 
	 * @author Pubudu Sanadaruwan
	 * 
	 * @throws Exception
	 */

	@Test
	public void errorExample() throws Exception {
		this.mockMvc
				.perform(get("/error").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
						.requestAttr(RequestDispatcher.ERROR_REQUEST_URI, "/api/RetailerStores")
						.requestAttr(RequestDispatcher.ERROR_MESSAGE,
								"The city 'http://localhost:8085/api/cities/123' does not exist"))
				.andDo(print()).andExpect(status().isBadRequest()).andExpect(jsonPath("error", is("Bad Request")))
				.andExpect(jsonPath("timestamp", is(notNullValue()))).andExpect(jsonPath("status", is(400)))
				.andExpect(jsonPath("path", is(notNullValue())))
				.andDo(document("error-example",
						responseFields(
								fieldWithPath("error").description("The HTTP error that occurred, e.g. `Bad Request`"),
								fieldWithPath("message").description("A description of the cause of the error"),
								fieldWithPath("path").description("The path to which the request was made"),
								fieldWithPath("status").description("The HTTP status code, e.g. `400`"),
								fieldWithPath("timestamp")
										.description("The time, in milliseconds, at which the error occurred"))));
	}

	/*
	 * Index example
	 * 
	 * @author Pubudu Sanadaruwan
	 * 
	 * @throws Exception
	 */

	@Test
	public void indexExample() throws Exception {

		String accessToken = getAccessToken("admin", "admin");
		System.err.println("acess tOKEN ::***** " + accessToken);
		this.mockMvc.perform(get("/api/").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk())
				.andDo(document("index-example",
						links(linkWithRel("cities").description("The <<resources-cities,Cities resource>>"),
								linkWithRel("states").description("The <<resources-states,States resource>>"),
								linkWithRel("retailerStores")
										.description("The <<resources-retailerStores,RetailerStores resource>>"),
								linkWithRel("reits").description("The <<resources-reits,Reits resource>>"),
								linkWithRel("tickers").description("The <<resources-tickers,Tickers resource>>"),
								linkWithRel("roles").description("The <<resources-roles,Roles resource>>"),
								linkWithRel("users").description("The <<resources-users,Users resource>>"),

								linkWithRel("profile").description("The ALPS profile for the service")),
						responseFields(subsectionWithPath("_links")
								.description("<<resources-index-links,Links>> to other resources"))));

	}

	/*
	 * List retailerStore example
	 *
	 * @author Pubudu Sanadaruwan
	 *
	 * @throws Exception
	 */

	@Test
	public void retailerStoresListExample() throws Exception {

		this.retailerStoreRepository.deleteAll();

		createRetailerStores(1, "Rathmalana", 2L, 3, 0.1, 0.2, 4, 10, 100, "mall", "mallAddress", "mallRemark", 1,
				true);
		createRetailerStores(2, "dehiwala", 3L, 4, 0.4, 0.5, 6, 100, 1000, "mall2", "mallAddress2", "mallRemark2", 2,
				true);

		this.mockMvc.perform(get("/api/retailerStores").header("Authorization", "Bearer " + getAccessToken("admin", "admin"))).andExpect(status().isOk()).andDo(document(
				"retailerStores-list-example",
				links(linkWithRel("self").description("Canonical link for this resource"),
						linkWithRel("profile").description("The ALPS profile for this resource")),
				responseFields(
						subsectionWithPath("_embedded.retailerStores")
								.description("An array of <<resources-retailerStore, RetailerStore resources>>"),
						subsectionWithPath("page").description("pagination"), subsectionWithPath("_links")
								.description("<<resources-retailerStores-list-links, Links>> to other resources"))));
	}

	private void createRetailerStores(int storeId, String address, long zip, int storeNumber, double latitude,
			double longitude, int spaces, int minCars, int maxCars, String mallName, String mallAddress,
			String mallRemarks, int cmbx, boolean activated) {
		RetailerStore retailerStore = new RetailerStore();
		retailerStore.setActivated(activated);
		retailerStore.setAddress(address);
		retailerStore.setCmbx(cmbx);
		retailerStore.setLatitude(latitude);
		retailerStore.setLongitude(longitude);
		retailerStore.setMallAddress(mallAddress);
		retailerStore.setMallName(mallName);
		retailerStore.setMallRemarks(mallRemarks);
		retailerStore.setMaxCars(maxCars);
		retailerStore.setMinCars(minCars);
		retailerStore.setSpaces(spaces);
		retailerStore.setStoreId(storeId);
		retailerStore.setStoreNumber(storeNumber);
		retailerStore.setZip(zip);

		this.retailerStoreRepository.save(retailerStore);

	}

	/*
	 * creating example
	 *
	 * @author Pubudu Sanadaruwan
	 *
	 * @throws Exception
	 */

	@Test
	 public void retailerStoresCreateExample() throws Exception {
	
	 Map<String, String> ticker = new HashMap<String, String>();
	 ticker.put("name", "REST12");
	
	 String tickerLocation = this.mockMvc
	 .perform(post("/api/tickers").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(ticker)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> city = new HashMap<String, String>();
	 city.put("name", "REST12");
	 String cityLocation = this.mockMvc
	 .perform(post("/api/cities").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(city)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> reit = new HashMap<String, String>();
	 reit.put("name", "REST12");
	 String reitLocation = this.mockMvc
	 .perform(post("/api/reits").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(reit)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> state = new HashMap<String, String>();
	 state.put("name", "REST12");
	 String stateLocation = this.mockMvc
	 .perform(post("/api/states").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(state)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, Object> retailerStores = new HashMap<String, Object>();
	
	 retailerStores.put("storeId", 4544);
	 retailerStores.put("address", "Rathmalana PAnnipitiya");
	 retailerStores.put("zip", 2);
	 retailerStores.put("storeNumber", 3);
	 retailerStores.put("latitude", 0.1);
	 retailerStores.put("longitude", 0.2);
	 retailerStores.put("spaces", 4);
	 retailerStores.put("minCars", 10);
	 retailerStores.put("maxCars", 100);
	 retailerStores.put("comment", null);
	 retailerStores.put("mallType", null);
	 retailerStores.put("mallOnlyType", null);
	 retailerStores.put("grade", null);
	 retailerStores.put("mallName", "mall");
	 retailerStores.put("mallAddress", "mallAddress");
	 retailerStores.put("mallRemarks", "mallRemark");
	 retailerStores.put("cmbx", 1);
	 retailerStores.put("activated", true);
	 retailerStores.put("ticker", tickerLocation);
	 retailerStores.put("city", cityLocation);
	 retailerStores.put("reit", reitLocation);
	 retailerStores.put("state", stateLocation);
	 this.mockMvc
	 .perform(post("/api/retailerStores").header("Authorization", "Bearer " + getAccessToken("admin", "admin"))
	 .contentType(MediaTypes.HAL_JSON).content(this.objectMapper.writeValueAsString(retailerStores)))
	 .andExpect(status().isCreated())
	 .andDo(document("retailerStores-create-example",
			 requestFields(fieldWithPath("storeId").description("The storeId of the retailerStore"),
	 fieldWithPath("address").description("The address of the retailerStore"),
	 fieldWithPath("zip").description("The zip of the retailerStore"),
	 fieldWithPath("storeNumber").description("The storeNumber of the retailerStore"),
	 fieldWithPath("latitude").description("The latitude of the retailerStore"),
	 fieldWithPath("longitude").description("The longitude of the retailerStore"),
	 fieldWithPath("spaces").description("The spaces of the retailerStore"),
	 fieldWithPath("minCars").description("The minCars of the retailerStore"),
	 fieldWithPath("maxCars").description("The maxCars of the retailerStore"),
	 fieldWithPath("comment").description("The comment of the retailerStore"),
	 fieldWithPath("mallType").description("The mallType of the retailerStore"),
	 fieldWithPath("mallOnlyType").description("The mallOnlyType of the retailerStore"),
	 fieldWithPath("grade").description("The grade of the retailerStore"),
	 fieldWithPath("mallName").description("The mallName of the retailerStore"),
	 fieldWithPath("mallAddress").description("The mallAddress of the retailerStore"),
	 fieldWithPath("mallRemarks").description("The mallRemarks of the retailerStore"),
	 fieldWithPath("cmbx").description("The cmbx of the retailerStore"),
	 fieldWithPath("activated").description("The activated of the retailerStore"),
	
	 fieldWithPath("state").description("An object of state resource URIs"),
	 fieldWithPath("reit").description("An object of reit resource URIs"),
	 fieldWithPath("ticker").description("An object of ticker resource URIs"),
	 fieldWithPath("city").description("An object of city resource URIs"))));
	
	 }

	/*
	 * Get retailerStore example
	 *
	 * @author Pubudu Sanadaruwan
	 *
	 * @throws Exception
	 */

	@Test
	 public void retailerStoreGetExample() throws Exception {
	 Map<String, String> ticker = new HashMap<String, String>();
	 ticker.put("name", "REST13");
	
	 String tickerLocation = this.mockMvc
	 .perform(post("/api/tickers").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(ticker)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> city = new HashMap<String, String>();
	 city.put("name", "REST13");
	 String cityLocation = this.mockMvc
	 .perform(post("/api/cities").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(city)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> reit = new HashMap<String, String>();
	 reit.put("name", "REST13");
	 String reitLocation = this.mockMvc
	 .perform(post("/api/reits").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(reit)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> state = new HashMap<String, String>();
	 state.put("name", "REST13");
	 String stateLocation = this.mockMvc
	 .perform(post("/api/states").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(state)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, Object> retailerStores = new HashMap<String, Object>();
	
	 retailerStores.put("storeId", 454457);
	 retailerStores.put("address", "Rathmalana Galle mathara");
	 retailerStores.put("zip", 2);
	 retailerStores.put("storeNumber", 3);
	 retailerStores.put("latitude", 0.1);
	 retailerStores.put("longitude", 0.2);
	 retailerStores.put("spaces", 4);
	 retailerStores.put("minCars", 10);
	 retailerStores.put("maxCars", 100);
	 retailerStores.put("comment", null);
	 retailerStores.put("mallType", null);
	 retailerStores.put("mallOnlyType", null);
	 retailerStores.put("grade", null);
	 retailerStores.put("mallName", "mall");
	 retailerStores.put("mallAddress", "mallAddress");
	 retailerStores.put("mallRemarks", "mallRemark");
	 retailerStores.put("cmbx", 1);
	 retailerStores.put("activated", true);
	 retailerStores.put("tickers", tickerLocation);
	 retailerStores.put("cities", cityLocation);
	 retailerStores.put("reits", reitLocation);
	 retailerStores.put("states", stateLocation);
	
	 String retailerStoreLocation = this.mockMvc
	 .perform(post("/api/retailerStores").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(retailerStores)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 this.mockMvc.perform(get(retailerStoreLocation).header("Authorization", "Bearer " + getAccessToken("admin", "admin"))).andExpect(status().isOk())
	 .andExpect(jsonPath("storeId", is(retailerStores.get("storeId"))))
	 .andExpect(jsonPath("address", is(retailerStores.get("address"))))
	 .andExpect(jsonPath("zip", is(retailerStores.get("zip"))))
	 .andExpect(jsonPath("storeNumber", is(retailerStores.get("storeNumber"))))
	 .andExpect(jsonPath("latitude", is(retailerStores.get("latitude"))))
	 .andExpect(jsonPath("longitude", is(retailerStores.get("longitude"))))
	 .andExpect(jsonPath("spaces", is(retailerStores.get("spaces"))))
	 .andExpect(jsonPath("minCars", is(retailerStores.get("minCars"))))
	 .andExpect(jsonPath("maxCars", is(retailerStores.get("maxCars"))))
	 .andExpect(jsonPath("mallName", is(retailerStores.get("mallName"))))
	 .andExpect(jsonPath("mallAddress", is(retailerStores.get("mallAddress"))))
	 .andExpect(jsonPath("mallRemarks", is(retailerStores.get("mallRemarks"))))
	 .andExpect(jsonPath("cmbx", is(retailerStores.get("cmbx"))))
	 .andExpect(jsonPath("grade", is(retailerStores.get("grade"))))
	 .andExpect(jsonPath("mallOnlyType", is(retailerStores.get("mallOnlyType"))))
	 .andExpect(jsonPath("mallType", is(retailerStores.get("mallType"))))
	 .andExpect(jsonPath("comment", is(retailerStores.get("comment"))))
	 .andExpect(jsonPath("activated", is(retailerStores.get("activated"))))
	 .andExpect(jsonPath("_links.self.href", is(retailerStoreLocation)))
	 .andExpect(jsonPath("_links.city", is(notNullValue())))
	 .andExpect(jsonPath("_links.reit", is(notNullValue())))
	 .andExpect(jsonPath("_links.state", is(notNullValue())))
	 .andExpect(jsonPath("_links.ticker", is(notNullValue()))).andDo(print())
	 .andDo(document("retailerStores-get-example", links(
	 linkWithRel("self")
	 .description("Canonical link for this<<resources-retailerStore,retailerStore>>"),
	 linkWithRel("retailerStore").description("This<<resources-retailerStore,retailerStore>>"),
	 linkWithRel("city").description("This retailerStore's cities"),
	 linkWithRel("reit").description("This retailerStore's reits"),
	 linkWithRel("state").description("This retailerStore's states"),
	 linkWithRel("ticker").description("This retailerStore's tickers")),
	 responseFields(fieldWithPath("storeId").description("The storeId of the retailerStore"),
	 fieldWithPath("address").description("The address of the retailerStore"),
	 fieldWithPath("zip").description("The zip of the retailerStore"),
	 fieldWithPath("storeNumber").description("The storeNumber of the retailerStore"),
	 fieldWithPath("latitude").description("The latitude of the retailerStore"),
	 fieldWithPath("longitude").description("The longitude of the retailerStore"),
	 fieldWithPath("spaces").description("The spaces of the retailerStore"),
	 fieldWithPath("minCars").description("The minCars of the retailerStore"),
	 fieldWithPath("maxCars").description("The maxCars of the retailerStore"),
	 fieldWithPath("mallName").description("The mallName of the retailerStore"),
	 fieldWithPath("mallAddress").description("The mallAddress of the retailerStore"),
	 fieldWithPath("mallRemarks").description("The mallRemarks of the retailerStore"),
	 fieldWithPath("cmbx").description("The cmbx of the retailerStore"),
	 fieldWithPath("grade").description("The grade of the retailerStore"),
	 fieldWithPath("mallOnlyType").description("The mallOnlyType of the retailerStore"),
	 fieldWithPath("mallType").description("The mallType of the retailerStore"),
	 fieldWithPath("comment").description("The comment of the retailerStore"),
	 fieldWithPath("activated").description("The activated of the retailerStore"),
	 subsectionWithPath("_links")
	 .description("<<resources-retailerStore-links,Links>> to other resources"))));
	 }

	/*
	 * Update example
	 *
	 * @author Pubudu Sanadaruwan
	 *
	 * @throws Exception
	 */

	@Test
	 public void retailerStoreUpdateExample() throws Exception {
	
	 Map<String, Object> retailerStores = new HashMap<String, Object>();
	 retailerStores.put("storeId", 1);
	 retailerStores.put("address", "Rathmalana");
	 retailerStores.put("zip", 2);
	 retailerStores.put("storeNumber", 3);
	 retailerStores.put("latitude", 0.1);
	 retailerStores.put("longitude", 0.2);
	 retailerStores.put("spaces", 4);
	 retailerStores.put("minCars", 10);
	 retailerStores.put("maxCars", 100);
	 retailerStores.put("comment", null);
	 retailerStores.put("mallType", null);
	 retailerStores.put("mallOnlyType", null);
	 retailerStores.put("grade", null);
	 retailerStores.put("mallName", "mall");
	 retailerStores.put("mallAddress", "mallAddress");
	 retailerStores.put("mallRemarks", "mallRemark");
	 retailerStores.put("cmbx", 1);
	 retailerStores.put("activated", true);
	
	 String retailerStoreLocation = this.mockMvc
	 .perform(post("/api/retailerStores").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(retailerStores)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 this.mockMvc.perform(get(retailerStoreLocation).header("Authorization", "Bearer " + getAccessToken("admin", "admin"))).andExpect(status().isOk())
	 .andExpect(jsonPath("storeId", is(retailerStores.get("storeId"))))
	 .andExpect(jsonPath("address", is(retailerStores.get("address"))))
	 .andExpect(jsonPath("zip", is(retailerStores.get("zip"))))
	 .andExpect(jsonPath("storeNumber", is(retailerStores.get("storeNumber"))))
	 .andExpect(jsonPath("latitude", is(retailerStores.get("latitude"))))
	 .andExpect(jsonPath("longitude", is(retailerStores.get("longitude"))))
	 .andExpect(jsonPath("spaces", is(retailerStores.get("spaces"))))
	 .andExpect(jsonPath("minCars", is(retailerStores.get("minCars"))))
	 .andExpect(jsonPath("maxCars", is(retailerStores.get("maxCars"))))
	 .andExpect(jsonPath("mallName", is(retailerStores.get("mallName"))))
	 .andExpect(jsonPath("mallAddress", is(retailerStores.get("mallAddress"))))
	 .andExpect(jsonPath("mallRemarks", is(retailerStores.get("mallRemarks"))))
	 .andExpect(jsonPath("cmbx", is(retailerStores.get("cmbx"))))
	 .andExpect(jsonPath("grade", is(retailerStores.get("grade"))))
	 .andExpect(jsonPath("mallOnlyType", is(retailerStores.get("mallOnlyType"))))
	 .andExpect(jsonPath("mallType", is(retailerStores.get("mallType"))))
	 .andExpect(jsonPath("comment", is(retailerStores.get("comment"))))
	
	 .andExpect(jsonPath("activated", is(retailerStores.get("activated"))))
	 .andExpect(jsonPath("_links.self.href", is(retailerStoreLocation)))
	 .andExpect(jsonPath("_links.city", is(notNullValue())))
	 .andExpect(jsonPath("_links.reit", is(notNullValue())))
	 .andExpect(jsonPath("_links.state", is(notNullValue())))
	 .andExpect(jsonPath("_links.ticker", is(notNullValue())));
	
	 Map<String, String> ticker = new HashMap<String, String>();
	 ticker.put("name", "REST14");
	
	 String tickerLocation = this.mockMvc
	 .perform(post("/api/tickers").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(ticker)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> city = new HashMap<String, String>();
	 city.put("name", "REST14");
	 String cityLocation = this.mockMvc
	 .perform(post("/api/cities").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(city)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> reit = new HashMap<String, String>();
	 reit.put("name", "REST14");
	 String reitLocation = this.mockMvc
	 .perform(post("/api/reits").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(reit)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> state = new HashMap<String, String>();
	 state.put("name", "REST14");
	 String stateLocation = this.mockMvc
	 .perform(post("/api/states").header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(state)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, Object> retailerStoresUpdate = new HashMap<String, Object>();
	 retailerStoresUpdate.put("ticker", Arrays.asList(tickerLocation));
	 retailerStoresUpdate.put("city", Arrays.asList(cityLocation));
	 retailerStoresUpdate.put("reit", Arrays.asList(reitLocation));
	 retailerStoresUpdate.put("state", Arrays.asList(stateLocation));
	 this.mockMvc
	 .perform(patch(retailerStoreLocation).header("Authorization", "Bearer " + getAccessToken("admin", "admin")).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(retailerStoresUpdate)))
	 .andExpect(status().isNoContent()).andDo(document("retailerStores-update-example",
	 requestFields(
	 fieldWithPath("storeId").description("The storeId of the retailerStore")
	 .type(JsonFieldType.NUMBER).optional(),
	 fieldWithPath("address").description("The address of the retailerStore")
	 .type(JsonFieldType.STRING).optional(),
	 fieldWithPath("zip").description("The zip of the retailerStore").type(JsonFieldType.NUMBER)
	 .optional(),
	 fieldWithPath("storeNumber").description("The storeNumber of the retailerStore")
	 .type(JsonFieldType.NUMBER).optional(),
	 fieldWithPath("latitude").description("The latitude of the retailerStore")
	 .type(JsonFieldType.NUMBER).optional(),
	 fieldWithPath("longitude").description("The longitude of the retailerStore")
	 .type(JsonFieldType.NUMBER).optional(),
	 fieldWithPath("spaces").description("The spaces of the retailerStore")
	 .type(JsonFieldType.NUMBER).optional(),
	 fieldWithPath("minCars").description("The minCars of the retailerStore")
	 .type(JsonFieldType.NUMBER).optional(),
	 fieldWithPath("maxCars").description("The maxCars of the retailerStore")
	 .type(JsonFieldType.NUMBER).optional(),
	 fieldWithPath("mallName").description("The mallName of the retailerStore")
	 .type(JsonFieldType.STRING).optional(),
	 fieldWithPath("mallAddress").description("The mallAddress of the retailerStore")
	 .type(JsonFieldType.STRING).optional(),
	 fieldWithPath("mallRemarks").description("The mallRemarks of the retailerStore")
	 .type(JsonFieldType.STRING).optional(),
	 fieldWithPath("cmbx").description("The cmbx of the retailerStore").type(JsonFieldType.NUMBER)
	 .optional(),
	 fieldWithPath("grade").description("The grade of the retailerStore").type(JsonFieldType.STRING)
	 .optional(),
	 fieldWithPath("mallOnlyType").description("The mallOnlyType of the retailerStore")
	 .type(JsonFieldType.STRING).optional(),
	 fieldWithPath("mallType").description("The mallType of the retailerStore")
	 .type(JsonFieldType.STRING).optional(),
	 fieldWithPath("comment").description("The comment of the retailerStore")
	 .type(JsonFieldType.STRING).optional(),
	 fieldWithPath("activated").description("The activated of the retailerStore")
	 .type(JsonFieldType.BOOLEAN).optional(),
	 fieldWithPath("state").description("An object of state resource URIs").optional(),
	 fieldWithPath("reit").description("An object of reit resource URIs").optional(),
	 fieldWithPath("ticker").description("An object of ticker resource URIs").optional(),
	 fieldWithPath("city").description("An object of city resource URIs").optional())));
	
	 }

}
