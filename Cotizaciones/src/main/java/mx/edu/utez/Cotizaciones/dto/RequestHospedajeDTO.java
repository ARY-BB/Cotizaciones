package mx.edu.utez.Cotizaciones.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHospedajeDTO {

    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Min(value = 1, message = "Debe ser al menos 1 noche")
    @Max(value = 30, message = "El número de noches no puede superar los 30 días")
    private Integer numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    @Min(value = 1, message = "Debe ser al menos 1 huésped")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    private boolean incluyeDesayuno;
    private boolean incluyeEstacionamiento;
}