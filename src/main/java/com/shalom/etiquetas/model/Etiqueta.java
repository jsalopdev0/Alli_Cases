package com.shalom.etiquetas.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Etiqueta.java
public class Etiqueta {
    private String remitenteNombre;
    private String remitenteDni;
    private String remitenteTelefono;

    private String destinatarioNombre;
    private String destinatarioDni;
    private String destinatarioTelefono;

    private String destino; // cadena única concatenada
    // getters y setters
}
