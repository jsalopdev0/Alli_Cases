package com.shalom.etiquetas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioDTO {
    private String dni;
    private String telefono;
    private String destino; // Ejemplo: "Lima/Surco"
    private String agencia;

    // Getters y setters
}

