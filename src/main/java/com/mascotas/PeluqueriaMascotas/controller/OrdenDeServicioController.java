package com.mascotas.PeluqueriaMascotas.controller;

import com.mascotas.PeluqueriaMascotas.dto.OrdenDeServicioDTO;
import com.mascotas.PeluqueriaMascotas.service.OrdenDeServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mascotas/{mascotaId}/{servicioId}/orden-de-servicio")
public class OrdenDeServicioController {
    private final OrdenDeServicioService ordenDeServicioService;

    public OrdenDeServicioController(OrdenDeServicioService ordenDeServicioService) {
        this.ordenDeServicioService = ordenDeServicioService;
    }

    @PostMapping
    public ResponseEntity create(@PathVariable Integer mascotaId, @PathVariable Integer servicioId, @RequestBody OrdenDeServicioDTO ordenDeServicioDTO) {
        ordenDeServicioService.create(ordenDeServicioDTO, mascotaId, servicioId);

        return new ResponseEntity(ordenDeServicioDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{ordenId}")
    public ResponseEntity retrieveById(@PathVariable Integer mascotaId,
                                       @PathVariable Integer ordenId) {
        OrdenDeServicioDTO ordenDeServicioDTO = ordenDeServicioService.retrieveById(mascotaId, ordenId);

        return new ResponseEntity(ordenDeServicioDTO, HttpStatus.OK);
    }
}
