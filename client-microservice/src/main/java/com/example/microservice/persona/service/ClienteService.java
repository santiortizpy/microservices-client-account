package com.example.microservice.persona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.persona.exception.ResourceNotFoundException;
import com.example.microservice.persona.model.Cliente;
import com.example.microservice.persona.repository.ClienteRepository;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

     public Cliente createCliente(Cliente cliente){

             return clienteRepository.save(cliente);


    }
    public Cliente getClientById(Long id) {
        return clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cliente no encontrado con Id: "+ id));
    }

    public List<Cliente> getAllClient() {
        return clienteRepository.findAll();
    }

    public Cliente updateClient(Long id, Cliente clienteActualizado) {
        Optional<Cliente> clientOpt = clienteRepository.findById(id);
        if(clientOpt.isPresent()){
            Cliente cliente = clientOpt.get();
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setEdad(clienteActualizado.getEdad());
            cliente.setDireccion(clienteActualizado.getDireccion());
            cliente.setTelefono(clienteActualizado.getTelefono());
            cliente.setGenero(clienteActualizado.getGenero());
            cliente.setEstado(clienteActualizado.isEstado());
            cliente.setContrasenha(clienteActualizado.getContrasenha());
            cliente.setIdentificacion(clienteActualizado.getIdentificacion());
            return clienteRepository.save(cliente);
        } 
        return null;
    }

    public void deleteClientById(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente getClientByIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion);
    }
}
