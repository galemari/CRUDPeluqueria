package com.mascotas.PeluqueriaMascotas.controller;

import com.mascotas.PeluqueriaMascotas.dto.MascotaDTO;
import com.mascotas.PeluqueriaMascotas.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO createdMascotaDTO = mascotaService.create(mascotaDTO);

        return new ResponseEntity(mascotaDTO.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve() {
        return new ResponseEntity(mascotaService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{mascotaId}")
    public ResponseEntity retrieveById(@PathVariable Integer mascotaId) {
        MascotaDTO mascotaDTO = mascotaService.retrieveById(mascotaId);

        return new ResponseEntity(mascotaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{mascotaId}")
    public ResponseEntity delete(@PathVariable Integer mascotaId) {
        mascotaService.delete(mascotaId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{mascotaId}")
    public ResponseEntity replace(@PathVariable Integer mascotaId, @RequestBody MascotaDTO mascotaDTO) {
        mascotaService.replace(mascotaId, mascotaDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{mascotaId}")
    public ResponseEntity modify(@PathVariable Integer mascotaId, @RequestBody Map<String, Object> fieldsToModify) {
        mascotaService.modify(mascotaId, fieldsToModify);

        return new ResponseEntity(HttpStatus.OK);
    }


}


