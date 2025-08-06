package com.shalom.etiquetas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class ReniecClient {

    @Value("${reniec.token}")
    private String token;

    private final String BASE_URL = "https://api.decolecta.com/v1/reniec/dni?numero=";

    public String obtenerNombrePorDni(String dni) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                BASE_URL + dni,
                HttpMethod.GET,
                entity,
                String.class
        );

        JSONObject json = new JSONObject(response.getBody());
        return json.getString("full_name");
    }
}
