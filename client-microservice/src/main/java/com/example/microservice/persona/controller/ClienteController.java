package com.example.microservice.persona.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.persona.dto.ClienteDto;
import com.example.microservice.persona.model.Cliente;
import com.example.microservice.persona.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    
    

    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteN) {
       
        Cliente cliente = clienteService.createCliente(clientDtoToClient(clienteN));
        return new ResponseEntity<>(clientToClientDto(cliente), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        ClienteDto cliente =clientToClientDto(clienteService.getClientById(id));
        return  new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> result = 
        clienteService.getAllClient().stream().map(this :: clientToClientDto).collect(Collectors.toList());
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteActualizado) {
        Cliente updatedCliente = clienteService.updateClient(id, clientDtoToClient(clienteActualizado));
        if(updatedCliente != null) {
            
            return new ResponseEntity<>(clientToClientDto(updatedCliente), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

     private ClienteDto clientToClientDto(Cliente cliente){
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setContrasenha(cliente.getContrasenha());
            clienteDto.setGenero(cliente.getGenero());
            clienteDto.setDireccion(cliente.getDireccion());
            clienteDto.setEdad(cliente.getEdad());
            clienteDto.setEstado(cliente.isEstado());
            clienteDto.setIdentificacion(cliente.getIdentificacion());
            clienteDto.setTelefono(cliente.getTelefono());
            clienteDto.setId(cliente.getId());
            clienteDto.setNombre(cliente.getNombre());
            return clienteDto;}
        
            private Cliente clientDtoToClient(ClienteDto clienteDto){
            Cliente cliente = new Cliente();
            cliente.setContrasenha(clienteDto.getContrasenha());
            cliente.setDireccion(clienteDto.getDireccion());
            cliente.setGenero(clienteDto.getGenero());
            cliente.setEdad(clienteDto.getEdad());
            cliente.setEstado(clienteDto.isEstado());
            cliente.setIdentificacion(clienteDto.getIdentificacion());
            cliente.setTelefono(clienteDto.getTelefono());
            cliente.setId(clienteDto.getId());
            cliente.setNombre(clienteDto.getNombre());
            return cliente;
        }

    
}
