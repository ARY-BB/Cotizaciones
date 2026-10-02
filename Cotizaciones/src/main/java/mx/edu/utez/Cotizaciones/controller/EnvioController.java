package mx.edu.utez.Cotizaciones.controller;

import jakarta.validation.Valid;
import mx.edu.utez.Cotizaciones.dto.RequestEnvioDTO;
import mx.edu.utez.Cotizaciones.dto.ResponseEnvioDTO;
import mx.edu.utez.Cotizaciones.service.EnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseEnvioDTO> cotizarEnvio(@RequestBody @Valid RequestEnvioDTO payload) {
        return ResponseEntity.ok(envioService.calcularEnvio(payload));
    }
}