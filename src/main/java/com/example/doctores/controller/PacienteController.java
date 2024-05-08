package com.example.doctores.controller;

import com.example.doctores.exception.ResourceNotFoundException;
import com.example.doctores.model.Paciente;
import com.example.doctores.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @PutMapping("/{id}")
    public Paciente updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetails) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el id: " + id));

        paciente.setNombre(pacienteDetails.getNombre());
        paciente.setEdad(pacienteDetails.getEdad());
        paciente.setGenero(pacienteDetails.getGenero());
        paciente.setDireccion(pacienteDetails.getDireccion());
        paciente.setTelefono(pacienteDetails.getTelefono());

        return pacienteRepository.save(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el id: " + id));

        pacienteRepository.delete(paciente);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el id: " + id));
        return ResponseEntity.ok().body(paciente);
    }
}
