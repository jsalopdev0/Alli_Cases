package com.shalom.etiquetas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtiquetaRequestDTO {
    private RemitenteDTO remitente; // Opcional (puede ser null)
    private DestinatarioDTO destinatario;

    // Getters y setters
}

