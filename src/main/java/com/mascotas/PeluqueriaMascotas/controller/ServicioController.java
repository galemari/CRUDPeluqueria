package com.mascotas.PeluqueriaMascotas.controller;

import com.mascotas.PeluqueriaMascotas.dto.ServicioDTO;
import com.mascotas.PeluqueriaMascotas.service.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ServicioDTO servicioDTO) {
        ServicioDTO createdServicioDTO = servicioService.create(servicioDTO);

        return new ResponseEntity(servicioDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve() {
        return new ResponseEntity(servicioService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{servicioId}")
    public ResponseEntity retrieveById(@PathVariable Integer servicioId) {
        ServicioDTO servicioDTO = servicioService.retrieveById(servicioId);
        return new ResponseEntity(servicioDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{servicioId}")
    public ResponseEntity delete(@PathVariable Integer servicioId) {
        servicioService.delete(servicioId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{servicioId}")
    public ResponseEntity replace(@PathVariable Integer servicioId, @RequestBody ServicioDTO servicioDTO) {
        servicioService.replace(servicioId, servicioDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{servicioId}")
    public ResponseEntity modify(@PathVariable Integer servicioId, @RequestBody Map<String, Object> fieldsToModify) {
        servicioService.modify(servicioId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }
}
