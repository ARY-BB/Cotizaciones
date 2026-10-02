package mx.edu.utez.Cotizaciones.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Cotizaciones.dto.RequestHospedajeDTO;
import mx.edu.utez.Cotizaciones.dto.ResponseHospedajeDTO;
import mx.edu.utez.Cotizaciones.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/hospedaje")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseHospedajeDTO> cotizarHospedaje(@RequestBody @Valid RequestHospedajeDTO payload) {
        return ResponseEntity.ok(hospedajeService.calcularHospedaje(payload));
    }
}