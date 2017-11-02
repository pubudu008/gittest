package com.hrandika.polymer.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.hrandika.polymer.security.AjaxLogoutSuccessHandler;
import com.hrandika.polymer.security.AuthoritiesConstants;
import com.hrandika.polymer.security.Http401UnauthorizedEntryPoint;

@Configuration
public class OAuth2ServerConfiguration {

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Inject
		private Http401UnauthorizedEntryPoint authenticationEntryPoint;

		@Inject
		private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/api/**").authenticated().antMatchers("/api/oauth/**").permitAll();

			http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().logout()
					.logoutUrl("/api/logout").logoutSuccessHandler(ajaxLogoutSuccessHandler).and().csrf().disable()
					.headers().frameOptions().disable().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**")
					.permitAll().antMatchers("/api/account").permitAll().antMatchers("/api/register").permitAll()
					.antMatchers("/api/logs/**").hasAnyAuthority(AuthoritiesConstants.ADMIN)
					.antMatchers("/websocket/tracker").hasAuthority(AuthoritiesConstants.ADMIN)
					.antMatchers("/websocket/**").permitAll().antMatchers("/metrics/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/health/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/trace/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/dump/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/shutdown/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/beans/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/configprops/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/info/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/autoconfig/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/env/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/trace/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/liquibase/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/api-docs/**")
					.hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/protected/**").authenticated();

		}
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Inject
		private DataSource dataSource;

		@Bean
		public TokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
		}

		@Inject
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.pathMapping("/oauth/token", "/api/oauth/token").tokenStore(tokenStore())
					.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.allowFormAuthenticationForClients();
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("cleintID").scopes("read", "write")
					.authorities(AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER)
					.authorizedGrantTypes("password", "refresh_token", "authorization_code", "implicit")
					.secret("clientPassword").accessTokenValiditySeconds(30000);
		}
	}
}
