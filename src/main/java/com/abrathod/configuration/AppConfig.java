package com.abrathod.configuration;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;




@Configuration
@EnableWebMvc
@ComponentScan( value= {"com.abrathod"})
public class AppConfig {
	
	
	/*@Bean
	@SuppressWarnings("unchecked")
	public DefaultAnnotationHandlerMapping handlerMapping() {
		
		DefaultAnnotationHandlerMapping handlerMapping =new DefaultAnnotationHandlerMapping();
		return handlerMapping;
		
	}*/
	
	
	
	
	
	
	
	/*@Bean
	public InternalResourceViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("");
		viewResolver.setSuffix("");
		return viewResolver;
		
	}*/

	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource =new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource ;
	}
	
	
	
	
}
