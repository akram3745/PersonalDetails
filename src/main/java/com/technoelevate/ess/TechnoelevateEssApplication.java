package com.technoelevate.ess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
//@EnableConfigurationProperties({
//    FileStorageProperties.class
//})
public class TechnoelevateEssApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnoelevateEssApplication.class, args);
	}
	@Bean
	public ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		return mapper;
	}
}
