package com.yainnixdev.springleaf.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



	@Bean
	public ObjectMapper includeTransientObjectMapper() {
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(hibernate5Module);
		return mapper;
	}
}
