package com.shalom.etiquetas.controller;

import com.shalom.etiquetas.service.UbigeoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ubigeo")
public class UbigeoController {

    private final UbigeoService service;

    public UbigeoController(UbigeoService service) {
        this.service = service;
    }

    @GetMapping("/departamentos")
    public List<String> getDepartamentos() {
        return new ArrayList<>(service.getDepartamentos());
    }

    @GetMapping("/provincias")
    public List<String> getProvincias(@RequestParam String departamento) {
        return new ArrayList<>(service.getProvincias(departamento));
    }

    @GetMapping("/distritos")
    public List<String> getDistritos(@RequestParam String departamento,
                                     @RequestParam String provincia) {
        return new ArrayList<>(service.getDistritos(departamento, provincia));
    }

    @GetMapping("/agencias")
    public List<String> getAgencias(@RequestParam String departamento,
                                    @RequestParam(required = false) String provincia,
                                    @RequestParam(required = false) String distrito) {
        return service.buscarAgencias(departamento, provincia, distrito);
    }

}
