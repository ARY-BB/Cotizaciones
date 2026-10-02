package mx.edu.utez.Cotizaciones.service;

import mx.edu.utez.Cotizaciones.exception.customExceptions.BadRequestException;
import mx.edu.utez.Cotizaciones.dto.RequestEnvioDTO;
import mx.edu.utez.Cotizaciones.dto.ResponseEnvioDTO;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    public ResponseEnvioDTO calcularEnvio(RequestEnvioDTO payload) {
        double volumen = payload.getLargoCm() * payload.getAnchoCm() * payload.getAltoCm();

        if (volumen > 1000000) {
            throw new BadRequestException("No se aceptan paquetes con un volumen superior a 1,000,000 cm³");
        }

        String tipo = payload.getTipoEnvio().toUpperCase();
        if (!tipo.equals("ESTANDAR") && !tipo.equals("EXPRESS") && !tipo.equals("MISMO_DIA")) {
            throw new BadRequestException("Los tipos de envío permitidos son: ESTANDAR, EXPRESS, MISMO_DIA");
        }

        double costo = 80.0;
        costo += payload.getPesoKg() * 12.0;

        if (volumen > 50000) {
            costo += 100.0;
        }

        if (tipo.equals("EXPRESS")) {
            costo *= 1.40;
        } else if (tipo.equals("MISMO_DIA")) {
            costo *= 1.70;
        }

        if (payload.getValorDeclarado() > 10000) {
            costo += payload.getValorDeclarado() * 0.02;
        }

        return new ResponseEnvioDTO(payload.getCodigoPostal(), volumen, costo);
    }
}