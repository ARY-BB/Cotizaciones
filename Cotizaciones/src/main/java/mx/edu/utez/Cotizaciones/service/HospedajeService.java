package mx.edu.utez.Cotizaciones.service;

import mx.edu.utez.Cotizaciones.exception.customExceptions.BadRequestException;
import mx.edu.utez.Cotizaciones.dto.RequestHospedajeDTO;
import mx.edu.utez.Cotizaciones.dto.ResponseHospedajeDTO;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public ResponseHospedajeDTO calcularHospedaje(RequestHospedajeDTO payload) {
        String hab = payload.getTipoHabitacion().toUpperCase();
        String temp = payload.getTemporada().toUpperCase();

        double costoNoche;
        int maxCapacidad;

        switch (hab) {
            case "INDIVIDUAL" -> {
                costoNoche = 700.0;
                maxCapacidad = 1;
            }
            case "DOBLE" -> {
                costoNoche = 1100.0;
                maxCapacidad = 2;
            }
            case "SUITE" -> {
                costoNoche = 1800.0;
                maxCapacidad = 4;
            }
            default -> throw new BadRequestException("Los tipos de habitación permitidos son: INDIVIDUAL, DOBLE, SUITE");
        }

        if (payload.getNumeroHuespedes() > maxCapacidad) {
            throw new BadRequestException("El número de huéspedes (" + payload.getNumeroHuespedes() + ") supera la capacidad de la habitación " + hab + " (" + maxCapacidad + ")");
        }

        double costoHospedajeBase = costoNoche * payload.getNumeroNoches();
        double costoHospedaje = costoHospedajeBase;

        if (temp.equals("BAJA")) {
            costoHospedaje -= costoHospedajeBase * 0.10;
        } else if (temp.equals("ALTA")) {
            costoHospedaje += costoHospedajeBase * 0.25;
        } else if (!temp.equals("REGULAR")) {
            throw new BadRequestException("Las temporadas permitidas son: BAJA, REGULAR, ALTA");
        }

        if (payload.getNumeroNoches() >= 7) {
            costoHospedaje -= costoHospedajeBase * 0.08;
        }

        double costoDesayuno = 0.0;
        if (payload.isIncluyeDesayuno()) {
            costoDesayuno = payload.getNumeroHuespedes() * payload.getNumeroNoches() * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (payload.isIncluyeEstacionamiento()) {
            costoEstacionamiento = payload.getNumeroNoches() * 100.0;
        }

        double subtotal = costoHospedaje + costoDesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        return new ResponseHospedajeDTO(
                payload.getNombreHuesped(),
                costoHospedaje,
                costoDesayuno,
                costoEstacionamiento,
                subtotal,
                impuesto,
                total
        );
    }
}