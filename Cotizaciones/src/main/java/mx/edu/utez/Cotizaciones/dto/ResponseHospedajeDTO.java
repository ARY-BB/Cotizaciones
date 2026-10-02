package mx.edu.utez.Cotizaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHospedajeDTO {
    private String nombreHuesped;
    private double costoHospedaje;
    private double costoDesayuno;
    private double costoEstacionamiento;
    private double subtotal;
    private double impuestoHospedaje;
    private double total;
}