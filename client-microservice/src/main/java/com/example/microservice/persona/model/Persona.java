package com.example.microservice.persona.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@DiscriminatorColumn( name = "persona_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre",nullable = false, length = 100)
    private String nombre;
    @Column(name = "genero", length = 10)
    private String genero;
    @Column(name = "edad")
    private int edad;
    @Column(name = "identificacion", unique = true, length = 50, nullable = false)
    private String identificacion;

    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono", length = 20)
    private String telefono;

    
}