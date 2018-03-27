package com.abrathod.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter{

	
	private static final String RESOURCE_ID="my_rest_api";
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		
    httpSecurity.
			    anonymous().disable()
			    .requestMatchers().antMatchers("/employee/**")
			    .and().authorizeRequests()
			    .antMatchers("/employee/**").authenticated()/*access("hasRole('ADMIN')")*/
			    .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
				
		
	}
	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer securityConfigurer) {
		securityConfigurer.resourceId(RESOURCE_ID).stateless(false);
	}
	
	
	
}
