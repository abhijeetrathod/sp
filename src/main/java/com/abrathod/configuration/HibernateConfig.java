package com.abrathod.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value= {"classpath:application.properties"})
public class HibernateConfig {

	@Autowired
	Environment environment;
	
	
	@Bean
	public DataSource dataSource() {
		
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		
		return dataSource ;
	}
	
	
	
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] {"com.abrathod.dao","com.abrathod.entity"});
	      //em.setPackagesToScan(environment.getRequiredProperty("entitymanager.packagesToScan"),);
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	
	
	
	Properties additionalProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		jpaProperties.setProperty("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		jpaProperties.setProperty("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
		return jpaProperties;
	}
	
	
	
	 @Bean
	   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(emf);
	 
	      return transactionManager;
	   }
	
	
	 /**
		 * PersistenceExceptionTranslationPostProcessor is a bean post processor
		 * which adds an advisor to any bean annotated with Repository so that any
		 * platform-specific exceptions are caught and then rethrown as one Spring's
		 * unchecked data access exceptions (i.e. a subclass of
		 * DataAccessException).
		 */
	 
	@Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
}
