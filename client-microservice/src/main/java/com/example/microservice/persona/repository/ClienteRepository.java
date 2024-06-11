package com.example.microservice.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservice.persona.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Cliente findByIdentificacion(String identificacion);
}
