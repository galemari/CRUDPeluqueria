package com.mascotas.PeluqueriaMascotas.service;

import com.mascotas.PeluqueriaMascotas.dto.OrdenDeServicioDTO;
import com.mascotas.PeluqueriaMascotas.entity.Mascota;
import com.mascotas.PeluqueriaMascotas.entity.OrdenDeServicio;
import com.mascotas.PeluqueriaMascotas.entity.Servicio;
import com.mascotas.PeluqueriaMascotas.exceptions.ResourceNotFoundException;
import com.mascotas.PeluqueriaMascotas.repository.MascotaRepository;
import com.mascotas.PeluqueriaMascotas.repository.OrdenDeServicioRepository;
import com.mascotas.PeluqueriaMascotas.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class OrdenDeServicioService {
    private final OrdenDeServicioRepository ordenDeServicioRepository;
    private final MascotaRepository mascotaRepository;
    private final ServicioRepository servicioRepository;

    private final MascotaService mascotaService;
    private final ServicioService servicioService;


    public OrdenDeServicioService(OrdenDeServicioRepository ordenDeServicioRepository, MascotaRepository mascotaRepository, ServicioRepository servicioRepository, MascotaService mascotaService, ServicioService servicioService) {
        this.mascotaRepository = mascotaRepository;
        this.servicioRepository = servicioRepository;
        this.ordenDeServicioRepository = ordenDeServicioRepository;
        this.mascotaService = mascotaService;
        this.servicioService = servicioService;
    }

    public void create(OrdenDeServicioDTO ordenDeServicioDTO, Integer mascotaId, Integer servicioId) {
        Optional<Mascota> mascota = mascotaRepository.findById(mascotaId);

        if (mascota.isEmpty()) {
            throw new ResourceNotFoundException("La mascota que está asociando no existe.");
        }

        Optional<Servicio> servicio = servicioRepository.findById(servicioId);

        if (servicio.isEmpty()) {
            throw new ResourceNotFoundException("El servicio que está referenciando no existe.");
        }

        OrdenDeServicio ordenDeServicio = mapToEntity(ordenDeServicioDTO, mascota.get(), servicio.get());
        ordenDeServicio = ordenDeServicioRepository.save(ordenDeServicio);
        ordenDeServicioDTO.setId(ordenDeServicio.getId());
    }

    public OrdenDeServicioDTO retrieveById(Integer mascotaId, Integer ordenId) {
        if (!mascotaRepository.existsById(mascotaId)) {
            throw new ResourceNotFoundException("La mascota no existe.");
        }

        Optional<OrdenDeServicio> ordenDeServicio = ordenDeServicioRepository.findById(ordenId.toString());
        if (ordenDeServicio.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return mapToDTO(ordenDeServicio.get());
    }

    private OrdenDeServicio mapToEntity(OrdenDeServicioDTO ordenDeServicioDTO, Mascota mascota, Servicio servicio) {
        OrdenDeServicio ordenDeServicio = new OrdenDeServicio(ordenDeServicioDTO.getNombreContacto(),
                ordenDeServicioDTO.getTelefonoContacto(),
                LocalDate.parse(ordenDeServicioDTO.getFechaServicio(), DateTimeFormatter.ofPattern("d/MM/yyyy")),
                mascota, servicio);
        return ordenDeServicio;
    }

    private OrdenDeServicioDTO mapToDTO(OrdenDeServicio ordenDeServicio) {
        OrdenDeServicioDTO ordenDeServicioDTO = new OrdenDeServicioDTO(ordenDeServicio.getNombreContacto(),
                ordenDeServicio.getTelefonoContacto(),
                ordenDeServicio.getFechaServicio().toString(),
                mascotaService.mapToDTO(ordenDeServicio.getMascota()),
                servicioService.mapToDTO(ordenDeServicio.getServicio())
                );
        ordenDeServicioDTO.setId(ordenDeServicio.getId());
        return ordenDeServicioDTO;
    }
}
