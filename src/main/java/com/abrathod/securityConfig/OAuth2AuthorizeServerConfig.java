package com.abrathod.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizeServerConfig extends AuthorizationServerConfigurerAdapter{

	private static String REALM="my_oauth_realm";
	
	@Autowired
	TokenStore tokenStore;
	
	@Autowired
	UserApprovalHandler approvalHandler;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	AuthenticationManager authenticationManager;
	
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer client) throws Exception{
		
		client.inMemory()
			.withClient("my-trusted-client")
			.authorizedGrantTypes("password","authorization_code","refresh_token","implicit")
			.authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
			.scopes("read","write","trust")
			.secret("secret")
			.accessTokenValiditySeconds(0)
			.refreshTokenValiditySeconds(0);
					
	}
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer outh) {
		
		outh.realm(REALM+"/client");
		
	}
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore).userApprovalHandler(approvalHandler).authenticationManager(authenticationManager);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*private static String REALM= "MY_OAUTH_REALM";
	
	
	@Autowired
	TokenStore tokenStore;
	
	@Autowired
	UserApprovalHandler approvalHandler ;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	AuthenticationManager authManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer client) throws Exception{
		
		client.inMemory()
			.withClient("trusted-client")
			.authorizedGrantTypes("password","authorization_code" , "refresh_token" ,"implicit")
			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
			.scopes("read", "write", "trust")
			.secret("secret")
			.accessTokenValiditySeconds(300)  // invalid after 5 min
			.refreshTokenValiditySeconds(600); // refresh after 10 min
					
	}
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		
		endpoints.tokenStore(tokenStore).userApprovalHandler(approvalHandler).authenticationManager(authManager);
		
	}
	
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception{
		
		oauthServer.realm(REALM+"/client");
	}*/
	
}
