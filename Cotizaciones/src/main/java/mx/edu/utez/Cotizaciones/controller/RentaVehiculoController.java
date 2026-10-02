package mx.edu.utez.Cotizaciones.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Cotizaciones.dto.RequestRentaVehiculoDTO;
import mx.edu.utez.Cotizaciones.dto.ResponseRentaVehiculoDTO;
import mx.edu.utez.Cotizaciones.service.RentaVehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/vehiculos")
public class RentaVehiculoController {

    private final RentaVehiculoService rentaVehiculoService;

    public RentaVehiculoController(RentaVehiculoService rentaVehiculoService) {
        this.rentaVehiculoService = rentaVehiculoService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseRentaVehiculoDTO> cotizarRenta(@RequestBody @Valid RequestRentaVehiculoDTO payload) {
        return ResponseEntity.ok(rentaVehiculoService.calcularRenta(payload));
    }
}