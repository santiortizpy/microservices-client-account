package com.example.microservice.persona.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto extends  PersonaDto {


    private String contrasenha;
    private boolean estado;

   
}
