package mx.edu.utez.Cotizaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRentaVehiculoDTO {
    private String nombreCliente;
    private double costoRenta;
    private double cargoKmAdicionales;
    private double cargoEdad;
    private double cargoSeguro;
    private double total;
}