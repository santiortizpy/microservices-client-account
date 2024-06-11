package com.example.microservice.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservice.persona.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{
    
}
