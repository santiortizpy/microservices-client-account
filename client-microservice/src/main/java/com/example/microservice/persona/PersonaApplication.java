package com.example.microservice.persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.microservice.persona"})
@EnableJpaRepositories("com.example.microservice.persona.repository")
public class PersonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaApplication.class, args);
	}

}
