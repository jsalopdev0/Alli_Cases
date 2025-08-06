package com.shalom.etiquetas.service;

import com.shalom.etiquetas.dto.EtiquetaRequestDTO;
import com.shalom.etiquetas.dto.RemitenteDTO;
import com.shalom.etiquetas.model.Etiqueta;
import org.springframework.stereotype.Service;

@Service
public class EtiquetaService {

    private final ReniecClient reniec;
    private final UbigeoService ubigeo;

    public EtiquetaService(ReniecClient reniec, UbigeoService ubigeo) {
        this.reniec = reniec;
        this.ubigeo = ubigeo;
    }

    public String obtenerNombrePorDni(String dni) {
        return reniec.obtenerNombrePorDni(dni);
    }

    public Etiqueta generarEtiqueta(EtiquetaRequestDTO request) {
        RemitenteDTO remitente = request.getRemitente();
        if (remitente == null) {
            remitente = new RemitenteDTO();
            remitente.setNombre("ALLISON MILAGROS RIVERA TORRES");
            remitente.setDni("75961070");
            remitente.setTelefono("981406535");
        }

        String destino = request.getDestinatario().getDestino();
        ubigeo.validarDestino(destino);

        String nombreDestinatario = reniec.obtenerNombrePorDni(request.getDestinatario().getDni());

        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setRemitenteNombre(remitente.getNombre());
        etiqueta.setRemitenteDni(remitente.getDni());
        etiqueta.setRemitenteTelefono(remitente.getTelefono());

        etiqueta.setDestinatarioNombre(nombreDestinatario);
        etiqueta.setDestinatarioDni(request.getDestinatario().getDni());
        etiqueta.setDestinatarioTelefono(request.getDestinatario().getTelefono());

        etiqueta.setDestino(destino);

        return etiqueta;
    }
}
