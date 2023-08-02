package com.mascotas.PeluqueriaMascotas.service;

import com.mascotas.PeluqueriaMascotas.dto.ServicioDTO;
import com.mascotas.PeluqueriaMascotas.entity.Servicio;
import com.mascotas.PeluqueriaMascotas.exceptions.ExistingResourceException;
import com.mascotas.PeluqueriaMascotas.exceptions.ResourceNotFoundException;
import com.mascotas.PeluqueriaMascotas.repository.ServicioRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public ServicioDTO create(ServicioDTO servicioDTO) {
        Servicio servicio = mapToEntity(servicioDTO);
        checkForExistingServicio(servicio.getId());
        servicioRepository.save(servicio);

        return servicioDTO;
    }

    public List<ServicioDTO> retrieveAll() {
        List<Servicio> servicios = servicioRepository.findAll();

        return servicios.stream()
                .map(servicio -> mapToDTO(servicio))
                .collect(Collectors.toList());
    }

    public ServicioDTO retrieveById(Integer id) {
        Optional<Servicio> servicio = servicioRepository.findById(id);
        if (servicio.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return mapToDTO(servicio.get());
    }

    public void delete(Integer servicioId) {
        try {
            servicioRepository.deleteById(servicioId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    public void replace(Integer servicioId, ServicioDTO servicioDTO) {
        Optional<Servicio> servicio = servicioRepository.findById(servicioId);
        if (servicio.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        Servicio servicioToReplace = servicio.get();
        servicioToReplace.setNombre(servicioDTO.getNombre());
        servicioToReplace.setValor(servicioDTO.getValor());
        servicioRepository.save(servicioToReplace);
    }

    public void modify(Integer servicioId, Map<String, Object> fieldsToModify) {
        Optional<Servicio> servicio = servicioRepository.findById(servicioId);
        if (servicio.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        Servicio servicioToModify = servicio.get();
        fieldsToModify.forEach((key, value) -> servicioToModify.modifyAttributeValue(key, value));

        servicioRepository.save(servicioToModify);
    }

    private void checkForExistingServicio(Integer servicioId) {
        if (servicioRepository.existsById(servicioId)) {
            throw new ExistingResourceException();
        }
    }

    public Servicio mapToEntity(ServicioDTO servicioDTO) {
        Servicio servicio = new Servicio(servicioDTO.getId(), servicioDTO.getNombre(), servicioDTO.getValor());
        return servicio;
    }

    public ServicioDTO mapToDTO(Servicio servicio) {
        ServicioDTO servicioDTO = new ServicioDTO(servicio.getId(), servicio.getNombre(),
                servicio.getValor());

        return servicioDTO;
    }
}
