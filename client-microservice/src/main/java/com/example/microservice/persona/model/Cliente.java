package com.example.microservice.persona.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("cliente")
public class Cliente extends Persona {


    @Column(name = "contrasenha", nullable = false)
    private String contrasenha;
    @Column(name = "estado", nullable = false)
    private boolean estado;

   
}