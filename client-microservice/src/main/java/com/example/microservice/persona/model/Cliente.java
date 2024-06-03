package com.example.microservice.persona.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Persona {

    private String clienteId;
    private String contrasena;
    private boolean estado;

   
}