package com.example.microservice.persona;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.microservice.persona.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.microservice.persona.service.ClienteService;

@SpringBootTest
public class ClienteServiceTest {
    @Autowired
    private ClienteService service;

    @Test
    public void testCreateClient(){

        Cliente cliente = new Cliente();
        cliente.setNombre("Santiago");
        cliente.setEdad(25);
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("123456");
        cliente.setIdentificacion("123456987");
        cliente.setContrasenha("123456");
        cliente.setEstado(true);
        cliente.setGenero("Masculino");
        cliente.setId(1L);
        Cliente newClient = service.createCliente(cliente);
        assertNotNull(newClient);
        assertEquals("Santiago", newClient.getNombre());
        assertEquals(25, newClient.getEdad());
        assertEquals("Calle 123", newClient.getDireccion());
        assertEquals("123456", newClient.getTelefono());
    }
}
