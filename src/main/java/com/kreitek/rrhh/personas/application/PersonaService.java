package com.kreitek.rrhh.personas.application;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    List<PersonaDTO> getAllPersons();
    PersonaDTO createPersona(PersonaDTO persona);
    void deletePersona(Integer id);
    Optional<PersonaDTO> getPersona(Integer id);
}
