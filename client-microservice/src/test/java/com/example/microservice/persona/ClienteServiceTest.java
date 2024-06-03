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
        cliente.setName("Juan");
        cliente.setEdad(25);
        cliente.setDireccion("Calle 123");
        cliente.setTelefono("123456");
        Cliente newClient = service.createCliente(cliente);
        assertNotNull(newClient);
        assertEquals("Juan", newClient.getName());
        assertEquals(25, newClient.getEdad());
        assertEquals("Calle 123", newClient.getDireccion());
        assertEquals("123456", newClient.getTelefono());
    }
}
