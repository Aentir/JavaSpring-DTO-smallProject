package com.kreitek.rrhh.personas.infraestructure;

import com.kreitek.rrhh.personas.application.PersonaDTO;
import com.kreitek.rrhh.personas.domain.Persona;
import com.kreitek.rrhh.personas.application.PersonaService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonaController {

    private final HttpServletRequest request;
    private final PersonaService personaService;

    public PersonaController(HttpServletRequest request, PersonaService personaService) {
        this.request = request;
        this.personaService = personaService;
    }

    @GetMapping("/persona")
    public ResponseEntity<List<PersonaDTO>> getPersonas(){
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<PersonaDTO> personas = personaService.getAllPersons();
            return new ResponseEntity<>(personas, HttpStatus.OK);
        }
        return new ResponseEntity<List<PersonaDTO>>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<PersonaDTO> getPersona(@PathVariable Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            Optional<PersonaDTO> persona = personaService.getPersona(id);
            if (persona.isPresent())
                return new ResponseEntity<>(persona.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/persona")
    public ResponseEntity<PersonaDTO> postPersona(@RequestBody PersonaDTO nuevaPersona) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            PersonaDTO persona = personaService.createPersona(nuevaPersona);
            return new ResponseEntity<>(persona, HttpStatus.OK);
        }
        return new ResponseEntity<PersonaDTO>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<Persona> deletePersona(@PathVariable Integer id){
        try {
            personaService.deletePersona(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
