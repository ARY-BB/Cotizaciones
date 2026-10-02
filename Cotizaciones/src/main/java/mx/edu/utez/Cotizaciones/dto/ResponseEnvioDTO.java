package mx.edu.utez.Cotizaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEnvioDTO {
    private String codigoPostal;
    private double volumenCm3;
    private double costoTotal;
}