package com.example.demo.tests;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.LivedocumentationAutoDocsApplicationTests;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailerStoresTests extends LivedocumentationAutoDocsApplicationTests {
	
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

	@Inject
	private UserRepository userRepository;

	@Inject
	private RoleRepository roleRepository;
	@Test
	public void contextLoads() {
	}

@Before
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
	/*
	 * creating example
	 *
	 * @author Pubudu Sanadaruwan
	 *
	 * @throws Exception
	 */

	@Test
	public void createRetailerStore()throws Exception {
		Map<String, String> ticker = new HashMap<String, String>();
	 ticker.put("name", "REST101");
		 String tickerLocation = this.mockMvc
		 .perform(post("/api/tickers").with(userToken()).contentType(MediaTypes.HAL_JSON)
		 .content(this.objectMapper.writeValueAsString(ticker)))
		 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
		System.err.println("okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		 Map<String, String> city = new HashMap<String, String>();
		 city.put("name", "REST101");
		 String cityLocation = this.mockMvc
		 .perform(post("/api/cities").with(userToken()).contentType(MediaTypes.HAL_JSON)
		 .content(this.objectMapper.writeValueAsString(city)))
		 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
		
		 Map<String, String> reit = new HashMap<String, String>();
		 reit.put("name", "REST101");
		 String reitLocation = this.mockMvc
		 .perform(post("/api/reits").with(userToken()).contentType(MediaTypes.HAL_JSON)
		 .content(this.objectMapper.writeValueAsString(reit)))
		 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
		
		 Map<String, String> state = new HashMap<String, String>();
		 state.put("name", "REST101");
		 String stateLocation = this.mockMvc
		 .perform(post("/api/states").with(userToken()).contentType(MediaTypes.HAL_JSON)
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
		 .perform(post("/api/retailerStores").with(userToken()).contentType(MediaTypes.HAL_JSON).content(this.objectMapper.writeValueAsString(retailerStores)))
		 .andExpect(status().isCreated());
		
	}
	
	
	
	
	
	
	@Test
	public void retailerStoresListExample() throws Exception {

		this.retailerStoreRepository.deleteAll();

		createRetailerStores(1, "Rathmalana", 2L, 3, 0.1, 0.2, 4, 10, 100, "mall", "mallAddress", "mallRemark", 1,
				true);
		createRetailerStores(2, "dehiwala", 3L, 4, 0.4, 0.5, 6, 100, 1000, "mall2", "mallAddress2", "mallRemark2", 2,
				true);

		this.mockMvc.perform(get("/api/retailerStores").with(userToken())).andExpect(status().isOk());
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
	 * Get retailerStore example
	 *
	 * @author Pubudu Sanadaruwan
	 *
	 * @throws Exception
	 */

	@Test
	 public void retailerStoreGetExample() throws Exception {
	 Map<String, String> ticker = new HashMap<String, String>();
	 ticker.put("name", "REST141");
	
	 String tickerLocation = this.mockMvc
	 .perform(post("/api/tickers").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(ticker)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> city = new HashMap<String, String>();
	 city.put("name", "REST141");
	 String cityLocation = this.mockMvc
	 .perform(post("/api/cities").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(city)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> reit = new HashMap<String, String>();
	 reit.put("name", "REST141");
	 String reitLocation = this.mockMvc
	 .perform(post("/api/reits").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(reit)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> state = new HashMap<String, String>();
	 state.put("name", "REST141");
	 String stateLocation = this.mockMvc
	 .perform(post("/api/states").with(userToken()).contentType(MediaTypes.HAL_JSON)
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
	 .perform(post("/api/retailerStores").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(retailerStores)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 this.mockMvc.perform(get(retailerStoreLocation).with(userToken())).andExpect(status().isOk())
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
	 .perform(post("/api/retailerStores").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(retailerStores)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 this.mockMvc.perform(get(retailerStoreLocation).with(userToken())).andExpect(status().isOk())
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
	 ticker.put("name", "REST121");
	
	 String tickerLocation = this.mockMvc
	 .perform(post("/api/tickers").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(ticker)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> city = new HashMap<String, String>();
	 city.put("name", "REST121");
	 String cityLocation = this.mockMvc
	 .perform(post("/api/cities").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(city)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> reit = new HashMap<String, String>();
	 reit.put("name", "REST121");
	 String reitLocation = this.mockMvc
	 .perform(post("/api/reits").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(reit)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, String> state = new HashMap<String, String>();
	 state.put("name", "REST121");
	 String stateLocation = this.mockMvc
	 .perform(post("/api/states").with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(state)))
	 .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
	
	 Map<String, Object> retailerStoresUpdate = new HashMap<String, Object>();
	 retailerStoresUpdate.put("ticker", Arrays.asList(tickerLocation));
	 retailerStoresUpdate.put("city", Arrays.asList(cityLocation));
	 retailerStoresUpdate.put("reit", Arrays.asList(reitLocation));
	 retailerStoresUpdate.put("state", Arrays.asList(stateLocation));
	 this.mockMvc
	 .perform(patch(retailerStoreLocation).with(userToken()).contentType(MediaTypes.HAL_JSON)
	 .content(this.objectMapper.writeValueAsString(retailerStoresUpdate)))
	 .andExpect(status().isNoContent());
	 }

	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void reitGetExample() throws Exception {
		Map<String, Object> reits = new HashMap<String, Object>();

		reits.put("name", "Thangalle");

	String reitLocation = this.mockMvc
	.perform(
			post("/api/reits").with(userToken()).contentType(MediaTypes.HAL_JSON).content(
					this.objectMapper.writeValueAsString(reits)))
	.andExpect(status().isCreated()).andReturn().getResponse()
	.getHeader("Location");

		this.mockMvc.perform(get(reitLocation).with(userToken())).andExpect(status().isOk())
		.andExpect(jsonPath("name", is(reits.get("name"))))
		.andExpect(jsonPath("_links.self.href", is(reitLocation)));
				}


		// String ACCESSTOCKEN =getAccessToken("admin", "admin");
		// System.err.println("Token : " + ACCESSTOCKEN);
	
}
