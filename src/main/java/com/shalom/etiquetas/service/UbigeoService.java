package com.shalom.etiquetas.service;

import com.shalom.etiquetas.repository.UbigeoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UbigeoService {
    private final UbigeoRepository repo;

    public UbigeoService(UbigeoRepository repo) {
        this.repo = repo;
    }

    public void validarDestino(String path) {
        if (!repo.isDestinoValido(path)) {
            throw new IllegalArgumentException("Destino no válido: " + path);
        }
    }

    // Métodos para búsquedas dinámicas si quieres usarlos desde el Controller
    public Set<String> getDepartamentos() {
        return repo.findDepartamentos();
    }

    public Set<String> getProvincias(String departamento) {
        return repo.findProvincias(departamento);
    }

    public Set<String> getDistritos(String departamento, String provincia) {
        return repo.findDistritos(departamento, provincia);
    }

    public List<String> getAgencias(String departamento, String provincia, String distrito) {
        return repo.findAgencias(departamento, provincia, distrito);
    }

    public List<String> buscarAgencias(String departamento, String provincia, String distrito) {
        return repo.buscarAgencias(departamento, provincia, distrito);
    }

}
