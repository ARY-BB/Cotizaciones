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
public class RequestEnvioDTO {

    @NotBlank(message = "El código postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a 0")
    @Max(value = 50, message = "No se aceptan paquetes que pesen más de 50 kg")
    private Double pesoKg;

    @NotNull(message = "El largo es obligatorio")
    @Positive(message = "El largo debe ser mayor a 0")
    @Max(value = 150, message = "El largo no puede ser superior a 150 cm")
    private Double largoCm;

    @NotNull(message = "El ancho es obligatorio")
    @Positive(message = "El ancho debe ser mayor a 0")
    @Max(value = 150, message = "El ancho no puede ser superior a 150 cm")
    private Double anchoCm;

    @NotNull(message = "El alto es obligatorio")
    @Positive(message = "El alto debe ser mayor a 0")
    @Max(value = 150, message = "El alto no puede ser superior a 150 cm")
    private Double altoCm;

    @NotBlank(message = "El tipo de envío es obligatorio")
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio")
    @PositiveOrZero(message = "El valor declarado no puede ser negativo")
    private Double valorDeclarado;
}