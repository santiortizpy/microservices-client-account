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
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona {


    @Column(name = "contrasenha")
    private String contrasenha;
    @Column(name = "estado")
    private boolean estado;

   
}