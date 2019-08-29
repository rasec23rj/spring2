package com.spring.spring.domain.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.spring.spring.service.DBService;
import com.spring.spring.service.EmailService;
import com.spring.spring.service.SmtpMailService;

@Configuration
@Profile("dev")
public class devConfig {
	
	@Autowired
	private DBService dbservice;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		
		dbservice.instantiateTestDataBase();
		
		return true;
	}
	@Bean
	public EmailService emailService() {
		return new SmtpMailService();
	}
}
