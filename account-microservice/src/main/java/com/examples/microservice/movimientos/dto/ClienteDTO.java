package com.examples.microservice.movimientos.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO extends PersonaDTO {
    private String clienteId;
    private String contrasenha;
    private boolean estado;

}
