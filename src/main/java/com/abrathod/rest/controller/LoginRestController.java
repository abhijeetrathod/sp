package com.abrathod.rest.controller;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.abrathod.common.CodeInter;
import com.abrathod.common.TestErrorHandler;
import com.abrathod.entity.AuthTokenInfo;
import com.abrathod.entity.User;
import com.abrathod.services.UserService;

@RestController
@RequestMapping("/login")
@PropertySource(value= {"classpath:serverurl.properties"})
public class LoginRestController implements CodeInter{

	final String restBaseUrl="/login";
	static final Logger logger=Logger.getLogger(LoginRestController.class);
	
	@Autowired
	UserService userService ;
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	MessageSource message ;
	
	
	private static String REST_SERVICE_URI;
	private static String AUTH_SERVER_URI;
	
	public static String QPM_PASSWORD_GRANT = "?grant_type=password&";
	
	
	@Autowired
	public LoginRestController(Environment env1) {
		REST_SERVICE_URI=env1.getRequiredProperty("REST_SERVICE_URI");
		AUTH_SERVER_URI=env1.getRequiredProperty("AUTH_SERVER_URI");
	}
	
	
	@RequestMapping(value ="/loginUser" , method =RequestMethod.POST ,consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> userLogin(@RequestBody String jsonString ){
		
		final String apiBaseUrl="/loginUser";
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		JSONObject newjsonObject = null;
		List<User> userList= new ArrayList<>();
		User user = new User();
		try {
			
			
			if(jsonString.length()==0) {
				jsonObject.put("code", INPUT_IS_EMPTY);
				jsonObject.put("message", message.getMessage("inputempty", null, "Default" , Locale.getDefault()));
				
				logger.info("output" + restBaseUrl + apiBaseUrl + "" +message.getMessage("inputempty", null, "Default" ,
						Locale.getDefault()) + HttpStatus.OK);
				
				return new ResponseEntity<JSONObject>(jsonObject ,HttpStatus.OK);
			}
			
			newjsonObject =(JSONObject)jsonParser.parse(jsonString);
			
			userList = userService.findByUserName(newjsonObject.get("username").toString().trim());
			
			if(userList.isEmpty()) {
				
				jsonObject.put("code", INVALIDE_USERNAME_PASSWORD);
				jsonObject.put("message", message.getMessage("login.invalidusernamepassword", null, "Default" , Locale.getDefault()));
				
				logger.info("output" + restBaseUrl + apiBaseUrl + "" + message.getMessage("login.invalidusernamepassword", null,  
						"Default" , Locale.getDefault()) + HttpStatus.OK);
				
				return new ResponseEntity<JSONObject>(jsonObject ,HttpStatus.OK);
				
			}
			
			user = userList.get(0);
			/*if(!BCrypt.checkpw(user.getPassword(), newjsonObject.get("password").toString())){
				
				jsonObject.put("code", INVALIDE_USERNAME_PASSWORD);
				jsonObject.put("message", message.getMessage("login.invalidusernamepassword", null , "Default" , Locale.getDefault()));
				logger.info("output" + restBaseUrl + apiBaseUrl + "" + message.getMessage("login.invalidusernamepassword", null,  
						"Default" , Locale.getDefault()) + HttpStatus.OK);
				return new ResponseEntity<JSONObject>(jsonObject ,HttpStatus.OK);
				
			}*/
			
			if(!user.getPassword().equals(newjsonObject.get("password").toString())){
				
				jsonObject.put("code", INVALIDE_USERNAME_PASSWORD);
				jsonObject.put("message", message.getMessage("login.invalidusernamepassword", null , "Default" , Locale.getDefault()));
				logger.info("output" + restBaseUrl + apiBaseUrl + "" + message.getMessage("login.invalidusernamepassword", null,  
						"Default" , Locale.getDefault()) + HttpStatus.OK);
				return new ResponseEntity<JSONObject>(jsonObject ,HttpStatus.OK);
				
			}
			
			/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {

				SecurityContextHolder.getContext().setAuthentication(null);
			}*/
			
			System.out.println("Username: "+user.getUserName()+ " & Password :"+user.getPassword());
			
			AuthTokenInfo tokenInfo = sendTokenRequest(user.getUserName(), user.getPassword());
			
			
			jsonObject.put("access_token", tokenInfo.getAccess_token());
		//	jsonObject.put("expire_in", tokenInfo.getExpire_in());
			jsonObject.put("refresh_token", tokenInfo.getRefresh_token());
			jsonObject.put("scope", tokenInfo.getScope());
			jsonObject.put("token_type", tokenInfo.getToken_type());
			jsonObject.put("username", user.getUserName());
			
			
		
		    
			
		} catch (ParseException e) {
			
			jsonObject.put("code", INPUT_IS_EMPTY);
			jsonObject.put("message", message.getMessage("inputempty", null, "Default" , Locale.getDefault()));
			
			logger.error("output" + "/login//loginUser" , e);
			logger.info("output" + restBaseUrl + apiBaseUrl + "" + message.getMessage("inputempty", null, "Default", 
					Locale.getDefault()) + HttpStatus.OK);
			
			return new ResponseEntity<JSONObject>(jsonObject,HttpStatus.OK);
			
			
		}
		
		return new ResponseEntity<JSONObject>(jsonObject,HttpStatus.OK);
		
		
	}
	
	
	private List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		// System.out.println(authorities.toString());
		return authorities;
	}
	
	/*private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Content-Type", "application/json");
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
		
		
		
		 * 
		 * HttpHeaders headers = new HttpHeaders();
headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
headers.setContentType(MediaType.APPLICATION_JSON);
headers.set("X-TP-DeviceID", "your value");
		 
	}
	
	
	private HttpHeaders getHeadersWithClientCredentials() {
		
		String plainClineCredentials = "my-trusted-client:secret" ;
		String base64ClientCredentials = new String(org.apache.commons.codec.binary.Base64.encodeBase64(plainClineCredentials.getBytes()));
		
		HttpHeaders headers = getHeaders();
		
		headers.add("Authorization", "Basic " + base64ClientCredentials);
		
		System.out.println("headers : "+headers);
		return headers ;
		
	}*/
	
	
	/*
     * Prepare HTTP Headers.
     */
    private static HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
     
    /*
     * Add HTTP Authorization header, using Basic-Authentication to send client-credentials.
     */
    private static HttpHeaders getHeadersWithClientCredentials(){
        String plainClientCredentials="my-trusted-client:secret";
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
         
        HttpHeaders headers = getHeaders();
        headers.add("Authorization", "Basic " + base64ClientCredentials);
        return headers;
    }
	
    
    
	@SuppressWarnings("unchecked")
	private AuthTokenInfo sendTokenRequest(String username , String password) {
		
		/*
		 * 
		 * HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<String> request = new HttpEntity<String>(headers);
    ResponseEntity<String> response =
            restTemplate.exchange(DOODAD_URL, HttpMethod.GET, request, String.class);
    String responseBody = response.getBody();
		 */
		
		/*ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpCl);*/   
			      
		/*RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		System.out.println("request : " + request);
		ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI + QPM_PASSWORD_GRANT + "username=" + username + "&password=" +password
				, HttpMethod.POST, request, Object.class);
		
		System.out.println("response : " + response);
		
		LinkedHashMap< String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		
		AuthTokenInfo authTokenInfo=null ;
		
		if(map!=null) {
			authTokenInfo= new AuthTokenInfo();
			authTokenInfo.setAccess_token((String)map.get("access_token"));
			authTokenInfo.setToken_type((String)map.get("token_type"));
			//authTokenInfo.setExpire_in((Integer)map.get("expire_in"));
			authTokenInfo.setScope((String)map.get("scope"));
			authTokenInfo.setRefresh_token((String)map.get("refresh_token"));
		}else {
			// System.out.println("No user exist----------");

		}
		
		return authTokenInfo ;	*/
		
		/*
		 * 
		 * 	headers.add("Accept", "application/json"); 

			But the RequestMapping for createSpitter() specifies headers = "Content-Type=application/json" 
			
			So alter the client call as: 
			
			MultiValueMap<String, String> headers = 
			new LinkedMultiValueMap<String, String>(); 
			headers.add("Content-Type", "application/json"); //Note Content-Type as opposed to Accept 
			
			HttpEntity<Object> entity = new HttpEntity<Object>(spitter, headers); 
			
			ResponseEntity<Spitter> response = rest.exchange( 
			"http://localhost:8084/spitter-web/spitters", 
			HttpMethod.POST, entity, Spitter.class); 
		 * 
		 * 
		 */
		
		RestTemplate restTemplate = new RestTemplate(); 
        restTemplate.setErrorHandler(new TestErrorHandler());
        HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
        ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI+QPM_PASSWORD_GRANT +"username=" + username + "&password=" +password, HttpMethod.POST, request, Object.class);
        
        System.out.println("Response Body : "+response.getBody());
        
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)response.getBody();
        AuthTokenInfo tokenInfo = null;
         
        if(map!=null){
            tokenInfo = new AuthTokenInfo();
            tokenInfo.setAccess_token((String)map.get("access_token"));
            tokenInfo.setToken_type((String)map.get("token_type"));
            tokenInfo.setRefresh_token((String)map.get("refresh_token"));
            /*tokenInfo.setExpires_in((int)map.get("expires_in"));*/
            tokenInfo.setScope((String)map.get("scope"));
            System.out.println(tokenInfo);
            //System.out.println("access_token ="+map.get("access_token")+", token_type="+map.get("token_type")+", refresh_token="+map.get("refresh_token")
            //+", expires_in="+map.get("expires_in")+", scope="+map.get("scope"));;
        }else{
            System.out.println("No user exist----------");
             
        }
        return tokenInfo;
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	private AuthTokenInfo sendAccessTokenFromRefreshToken(String refreshToken) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response =restTemplate.exchange(AUTH_SERVER_URI + "?grant_type=refresh_token&refresh_token=" + refreshToken, HttpMethod.POST, request,Object.class);
		LinkedHashMap<String, Object> map =(LinkedHashMap<String, Object>)response.getBody();
		
		AuthTokenInfo authTokenInfo=null ;
		
		if(map!=null) {
			authTokenInfo= new AuthTokenInfo();
			authTokenInfo.setAccess_token((String)map.get("access_token"));
			authTokenInfo.setToken_type((String)map.get("token_type"));
			//authTokenInfo.setExpire_in((Integer)map.get("expire_in"));
			authTokenInfo.setScope((String)map.get("scope"));
			authTokenInfo.setRefresh_token((String)map.get("refresh_token"));
		}else {
			// System.out.println("No user exist----------");

		}
		return authTokenInfo ;
	}
	
	
	
	
	
	
	
	
}
