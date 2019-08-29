package com.spring.spring.domain.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.spring.spring.service.DBService;
import com.spring.spring.service.EmailService;
import com.spring.spring.service.MockEmailService;

@Configuration
@Profile("teste")
public class testeConfig {
	
	@Autowired
	private DBService dbservice;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		dbservice.instantiateTestDataBase();
		
		return true;
	}
	

}
