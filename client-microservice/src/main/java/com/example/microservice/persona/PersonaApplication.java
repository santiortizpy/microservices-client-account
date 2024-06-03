package com.example.microservice.persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.microservice.persona"})
public class PersonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaApplication.class, args);
	}

}
