package mx.edu.utez.Cotizaciones.service;

import mx.edu.utez.Cotizaciones.exception.customExceptions.BadRequestException;
import mx.edu.utez.Cotizaciones.dto.RequestRentaVehiculoDTO;
import mx.edu.utez.Cotizaciones.dto.ResponseRentaVehiculoDTO;
import org.springframework.stereotype.Service;

@Service
public class RentaVehiculoService {

    public ResponseRentaVehiculoDTO calcularRenta(RequestRentaVehiculoDTO payload) {
        String tipo = payload.getTipoVehiculo().toUpperCase();

        if (tipo.equals("CAMIONETA") && payload.getEdadConductor() < 25) {
            throw new BadRequestException("No se acepta la renta cuando se solicita una CAMIONETA y el conductor tiene menos de 25 años");
        }

        double costoDiario;
        switch (tipo) {
            case "COMPACTO" -> costoDiario = 550.0;
            case "SEDAN" -> costoDiario = 700.0;
            case "SUV" -> costoDiario = 950.0;
            case "CAMIONETA" -> costoDiario = 1200.0;
            default -> throw new BadRequestException("Los tipos de vehículo permitidos son: COMPACTO, SEDAN, SUV, CAMIONETA");
        }

        double costoRentaBase = costoDiario * payload.getDiasRenta();
        double costoRenta = costoRentaBase;

        if (payload.getDiasRenta() >= 7) {
            costoRenta = costoRentaBase * 0.90;
        }

        int kmIncluidos = payload.getDiasRenta() * 100;
        double cargoKm = 0.0;
        if (payload.getKilometrosEstimados() > kmIncluidos) {
            cargoKm = (payload.getKilometrosEstimados() - kmIncluidos) * 4.0;
        }

        double cargoEdad = 0.0;
        if (payload.getEdadConductor() >= 18 && payload.getEdadConductor() <= 24) {
            cargoEdad = (costoRenta + cargoKm) * 0.15;
        }

        double cargoSeguro = 0.0;
        if (payload.isSeguroCompleto()) {
            cargoSeguro = 180.0 * payload.getDiasRenta();
        }

        double total = costoRenta + cargoKm + cargoEdad + cargoSeguro;

        return new ResponseRentaVehiculoDTO(
                payload.getNombreCliente(),
                costoRenta,
                cargoKm,
                cargoEdad,
                cargoSeguro,
                total
        );
    }
}