package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import static capital.scalable.restdocs.misc.AuthorizationSnippet.documentAuthorization;
import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.tests.dto.AccessTokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import capital.scalable.restdocs.response.ResponseModifyingPreprocessors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LivedocumentationAutoDocsApplicationTests {
	@Test
	public void contextLoads() {
	
	}


	@Autowired
	private WebApplicationContext context;
    @Autowired
    private Filter springSecurityFilterChain;
	
	
	
	@Autowired
	protected ObjectMapper objectMapper;

	protected MockMvc mockMvc;

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	
	
	
	@Before
	public void setUp() throws Exception {
	    this.mockMvc = MockMvcBuilders
	            .webAppContextSetup(context)
	            .addFilters(springSecurityFilterChain)
	            .alwaysDo(JacksonResultHandlers.prepareJackson(objectMapper))
	            .alwaysDo(MockMvcRestDocumentation.document("{class-name}/{method-name}",
	                    Preprocessors.preprocessRequest(),
	                    Preprocessors.preprocessResponse(
	                            ResponseModifyingPreprocessors.replaceBinaryContent(),
	                            ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
	                            Preprocessors.prettyPrint())))
	            .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
	                    .uris()
	                    .withScheme("http")
	                    .withHost("localhost")
	                    .withPort(8080)
	                    .and().snippets()
	                    .withDefaults(CliDocumentation.curlRequest(),
	                            HttpDocumentation.httpRequest(),
	                            HttpDocumentation.httpResponse(),
	                            AutoDocumentation.requestFields(),
	                            AutoDocumentation.responseFields(),
	                            AutoDocumentation.pathParameters(),
	                            AutoDocumentation.requestParameters(),
	                            AutoDocumentation.description(),
	                            AutoDocumentation.methodAndPath(),
	                            AutoDocumentation.section()))
	            .build();
	}




	 protected RequestPostProcessor userToken() {
	        return new RequestPostProcessor() {
	            @Override
	            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
	                  String accessToken;
	                try {
	                    accessToken = getAccessToken("admin", "admin");
	                } catch (Exception e) {
	                    throw new RuntimeException(e);
	                }
	                System.err.println(accessToken);
	                request.addHeader("Authorization", "Bearer " + accessToken);
	                return documentAuthorization(request, "User access token required.");
	            }
	        };
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

	 



}

