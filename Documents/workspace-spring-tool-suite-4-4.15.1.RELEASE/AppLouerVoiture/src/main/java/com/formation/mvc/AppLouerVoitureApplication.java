package com.formation.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppLouerVoitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppLouerVoitureApplication.class, args);
	}
	

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	
	return new BCryptPasswordEncoder();
	}
	@Bean
	public SpringApplicationContext springApplicationContext() {
		
		return new SpringApplicationContext();
	}

}