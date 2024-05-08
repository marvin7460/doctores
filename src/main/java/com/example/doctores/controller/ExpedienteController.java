package com.example.doctores.controller;

import com.example.doctores.exception.ResourceNotFoundException;
import com.example.doctores.model.Expediente;
import com.example.doctores.repository.ExpedienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expedientes")
public class ExpedienteController {
    @Autowired
    private ExpedienteRepository expedienteRepository;

    @GetMapping
    public List<Expediente> getAllExpedientes() {
        return expedienteRepository.findAll();
    }

    @PostMapping
    public Expediente createExpediente(@RequestBody Expediente expediente) {
        return expedienteRepository.save(expediente);
    }

    @PutMapping("/{id}")
    public Expediente updateExpediente(@PathVariable Long id, @RequestBody Expediente expedienteDetails) {
        Expediente expediente = expedienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expediente no encontrado con el id: " + id));

        expediente.setSintomas(expedienteDetails.getSintomas());
        expediente.setDiagnostico(expedienteDetails.getDiagnostico());
        expediente.setTratamiento(expedienteDetails.getTratamiento());
        expediente.setFechaConsulta(expedienteDetails.getFechaConsulta());

        return expedienteRepository.save(expediente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpediente(@PathVariable Long id) {
        Expediente expediente = expedienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expediente no encontrado con el id: " + id));

        expedienteRepository.delete(expediente);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Expediente> getExpedienteById(@PathVariable Long id) {
        Expediente expediente = expedienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expediente no encontrado con el id: " + id));
        return ResponseEntity.ok().body(expediente);
    }

}
