package com.shalom.etiquetas.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.*;

@Repository
public class UbigeoRepository {

    private final JsonNode data;

    public UbigeoRepository() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/agencias_shalom.json");
            data = mapper.readTree(is);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando JSON de agencias", e);
        }
    }

    public boolean isDestinoValido(String path) {
        String[] partes = path.split("/");
        if (partes.length != 4) return false;

        String departamento = partes[0];
        String provincia = partes[1];
        String distrito = partes[2];
        String agencia = partes[3];

        JsonNode nodeDept = data.get(departamento);
        if (nodeDept == null) return false;

        JsonNode nodeProv = nodeDept.get(provincia);
        if (nodeProv == null) return false;

        JsonNode nodeDist = nodeProv.get(distrito);
        if (nodeDist == null || !nodeDist.isArray()) return false;

        for (JsonNode ag : nodeDist) {
            if (agencia.equalsIgnoreCase(ag.asText())) {
                return true;
            }
        }

        return false;
    }

    public Set<String> findDepartamentos() {
        Iterator<String> fields = data.fieldNames();
        Set<String> result = new TreeSet<>();
        while (fields.hasNext()) {
            result.add(fields.next());
        }
        return result;
    }

    public Set<String> findProvincias(String departamento) {
        JsonNode node = data.get(departamento);
        if (node == null) return Collections.emptySet();
        Iterator<String> fields = node.fieldNames();
        Set<String> result = new TreeSet<>();
        while (fields.hasNext()) {
            result.add(fields.next());
        }
        return result;
    }

    public Set<String> findDistritos(String departamento, String provincia) {
        JsonNode node = data.path(departamento).path(provincia);
        if (node == null) return Collections.emptySet();
        Iterator<String> fields = node.fieldNames();
        Set<String> result = new TreeSet<>();
        while (fields.hasNext()) {
            result.add(fields.next());
        }
        return result;
    }

    public List<String> findAgencias(String departamento, String provincia, String distrito) {
        JsonNode node = data.path(departamento).path(provincia).path(distrito);
        if (node == null || !node.isArray()) return Collections.emptyList();
        List<String> result = new ArrayList<>();
        node.forEach(ag -> result.add(ag.asText()));
        return result;
    }

    public List<String> buscarAgencias(String departamento, String provincia, String distrito) {
        List<String> resultado = new ArrayList<>();

        JsonNode nodeDept = data.get(departamento);
        if (nodeDept == null) return resultado;

        if (provincia == null) {
            // Buscar todas las agencias del departamento
            nodeDept.fields().forEachRemaining(provEntry ->
                    provEntry.getValue().fields().forEachRemaining(distEntry ->
                            distEntry.getValue().forEach(ag -> resultado.add(ag.asText()))
                    )
            );
        } else {
            JsonNode nodeProv = nodeDept.get(provincia);
            if (nodeProv == null) return resultado;

            if (distrito == null) {
                // Buscar todas las agencias del provincia
                nodeProv.fields().forEachRemaining(distEntry ->
                        distEntry.getValue().forEach(ag -> resultado.add(ag.asText()))
                );
            } else {
                // Buscar agencias específicas del distrito
                JsonNode nodeDist = nodeProv.get(distrito);
                if (nodeDist != null && nodeDist.isArray()) {
                    nodeDist.forEach(ag -> resultado.add(ag.asText()));
                }
            }
        }

        return resultado;
    }


}
