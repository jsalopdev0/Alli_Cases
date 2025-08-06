package com.shalom.etiquetas.controller;

import com.shalom.etiquetas.dto.EtiquetaRequestDTO;
import com.shalom.etiquetas.model.Etiqueta;
import com.shalom.etiquetas.service.EtiquetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    private final EtiquetaService service;

    public EtiquetaController(EtiquetaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Etiqueta> generarEtiqueta(@RequestBody EtiquetaRequestDTO request) {
        return ResponseEntity.ok(service.generarEtiqueta(request));
    }

    @GetMapping("/nombre")
    public ResponseEntity<String> obtenerNombrePorDni(@RequestParam String dni) {
        try {
            String nombre = service.obtenerNombrePorDni(dni);
            return ResponseEntity.ok(nombre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo obtener nombre: " + e.getMessage());
        }
    }

}

