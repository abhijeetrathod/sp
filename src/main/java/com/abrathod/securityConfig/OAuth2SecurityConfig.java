package com.abrathod.securityConfig;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	ClientDetailsService clientDetailsService;
	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	
	
	
	/*@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.inMemoryAuthentication()
		.withUser("abhijeet").password("abhijeet123").roles("ADMIN")
		.and()
		.withUser("newuser").password("user123").roles("USER");
		
	}*/
	
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity
				.anonymous().disable()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/auth/token").permitAll();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	
	
	@Bean
	@Primary
	public TokenStore tokenStore() {
		
		return new InMemoryTokenStore();
	}
	
	@Autowired
	@Bean
	public TokenStoreUserApprovalHandler tokenStoreApprovalHandler(TokenStore tokenStore) {
		
		TokenStoreUserApprovalHandler storeUserApprovalHandler = new TokenStoreUserApprovalHandler();
		storeUserApprovalHandler.setTokenStore(tokenStore);
		storeUserApprovalHandler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		storeUserApprovalHandler.setClientDetailsService(clientDetailsService);
		
		return storeUserApprovalHandler;
		
	}
	
	
	@Autowired
	@Bean
	public ApprovalStore tokenApprovalStore(TokenStore tokenStore) {
		
		TokenApprovalStore tokenApprovalStore = new TokenApprovalStore();
		tokenApprovalStore.setTokenStore(tokenStore);
		return tokenApprovalStore;
		
	}
	
	/*@Bean
	public ApplicationWebSecurity applicationWebSecurity() {
		return new ApplicationWebSecurity();
	}

	protected static class ApplicationWebSecurity extends HdivWebSecurityConfigurerAdapter {

		@Override
		public void addExclusions(ExclusionRegistry registry) {
			registry.addUrlExclusions("/");
		}
		@Override
		public void addRules(RuleRegistry registry) {
			registry.addRule("safeText").acceptedPattern("^[a-zA-Z0-9 :@.\\-_+#]*$").rejectedPattern("(\\s|\\S)*(--)(\\s|\\S)*]");
			registry.addRule("numbers").acceptedPattern("^[1-9]\\d*$");
		}

		@Override
		public void configureEditableValidation(ValidationConfigurer validationConfigurer) {
			validationConfigurer.addValidation(".*").rules("safeText");
			validationConfigurer.addValidation(".*").rules("numbers");
		}
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Autowired
    private ClientDetailsService clientDetailsService;
     
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("bill").password("abc123").roles("ADMIN").and()
        .withUser("bob").password("abc123").roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .anonymous().disable()
        .authorizeRequests()
        .antMatchers("/oauth/token").permitAll();
    }
 
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
 
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
 
    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }
     
    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }*/
	
}
