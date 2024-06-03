package com.example.microservice.persona.service;

import java.util.List;
import java.util.Optional;

import com.example.microservice.persona.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.microservice.persona.model.Persona;
import com.example.microservice.persona.repository.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Persona no encontrada con Id: "+ id));
    }

    public List<Persona> getAllPersonas(){
        return personaRepository.findAll();
    }

    public Persona updatePersona(Persona persona){
        Optional<Persona> personaOpt = personaRepository.findById(persona.getId());
        if(personaOpt.isPresent()){
            Persona personaActualizada = personaOpt.get();
            personaActualizada.setName(persona.getName());
            personaActualizada.setEdad(persona.getEdad());
            personaActualizada.setDireccion(persona.getDireccion());
            personaActualizada.setTelefono(persona.getTelefono());
            personaActualizada.setIdentificacion(persona.getIdentificacion());
            personaActualizada.setGenero(persona.getGenero());
            return personaRepository.save(personaActualizada);
        }
        return null;
    }   
    public void deletePersonaById(Long id){ 
        personaRepository.deleteById(id);
    }


}
