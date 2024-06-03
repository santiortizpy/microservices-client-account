package com.example.microservice.persona.controller;

import com.example.microservice.persona.dto.PersonaDto;
import com.example.microservice.persona.model.Persona;
import com.example.microservice.persona.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaDto> createPersona(@RequestBody PersonaDto personaN) {
        Persona persona = personaService.createPersona(personaDtoToPersona(personaN));
        return new ResponseEntity<>(personaToPersonaDto(persona), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> getPersonaById(@PathVariable Long id) {
        PersonaDto persona = personaToPersonaDto(personaService.getPersonaById(id));
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PersonaDto>> getAllPersonas() {
        List<PersonaDto> result =
        personaService.getAllPersonas().stream().map(this :: personaToPersonaDto).collect(Collectors.toList());
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> updatePersona(@PathVariable Long id, @RequestBody PersonaDto personaActualizada) {
        Persona updatedPersona = personaService.updatePersona(personaDtoToPersona(personaActualizada));
        if(updatedPersona != null) {
            return new ResponseEntity<>(personaToPersonaDto(updatedPersona), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonaById(@PathVariable Long id) {
        personaService.deletePersonaById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private PersonaDto personaToPersonaDto(Persona persona) {
        PersonaDto personaDto = new PersonaDto();
        personaDto.setId(persona.getId());
        personaDto.setName(persona.getName());
        personaDto.setEdad(persona.getEdad());
        personaDto.setDireccion(persona.getDireccion());
        personaDto.setTelefono(persona.getTelefono());
        personaDto.setIdentificacion(persona.getIdentificacion());
        personaDto.setGenero(persona.getGenero());
        return personaDto;
    }
    private Persona  personaDtoToPersona(PersonaDto personaDto) {
        Persona persona = new Persona();
        persona.setId(personaDto.getId());
        persona.setName(personaDto.getName());
        persona.setEdad(personaDto.getEdad());
        persona.setDireccion(personaDto.getDireccion());
        persona.setTelefono(personaDto.getTelefono());
        persona.setIdentificacion(personaDto.getIdentificacion());
        persona.setGenero(personaDto.getGenero());
        return persona;
    }
}
