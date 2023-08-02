package com.mascotas.PeluqueriaMascotas.service;

import com.mascotas.PeluqueriaMascotas.dto.MascotaDTO;
import com.mascotas.PeluqueriaMascotas.entity.Mascota;
import com.mascotas.PeluqueriaMascotas.exceptions.ExistingResourceException;
import com.mascotas.PeluqueriaMascotas.exceptions.ResourceNotFoundException;
import com.mascotas.PeluqueriaMascotas.repository.MascotaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaService {
    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public MascotaDTO create(MascotaDTO mascotaDTO) {
        Mascota mascota = mapToEntity(mascotaDTO);
        // verifica que la mascota con el ID no este creada
        checkForExistingMascota(mascota.getId());
        mascotaRepository.save(mascota);

        return mascotaDTO;
    }

    public List<MascotaDTO> retrieveAll() {
        List<Mascota> mascotas = mascotaRepository.findAll();

        return mascotas.stream()
                .map(mascota -> mapToDTO(mascota))
                .collect(Collectors.toList());
    }

    public MascotaDTO retrieveById(Integer id) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        if (mascota.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return mapToDTO(mascota.get());
    }

    public void delete(Integer mascotaId) {
        try {
            mascotaRepository.deleteById(mascotaId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        }
    }

    public void replace(Integer mascotaId, MascotaDTO mascotaDTO) {
        Optional<Mascota> mascota = mascotaRepository.findById(mascotaId);
        if (mascota.isEmpty()) {
            throw  new ResourceNotFoundException();
        }

        Mascota mascotaToReplace = mascota.get();
        mascotaToReplace.setNombre(mascotaDTO.getNombre());
        mascotaToReplace.setTamanio(mascotaDTO.getTamanio());
        mascotaRepository.save(mascotaToReplace);
    }

    public void modify(Integer mascotaId, Map<String, Object> fieldsToModify) {
        Optional<Mascota> mascota = mascotaRepository.findById(mascotaId);
        if (mascota.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        Mascota mascotaToModify = mascota.get();
        fieldsToModify.forEach((key, value) -> mascotaToModify.modifyAttributeValue(key, value));
        mascotaRepository.save(mascotaToModify);
    }

    private void checkForExistingMascota(Integer mascotaId) {
        if (mascotaRepository.existsById(mascotaId)) {
            throw new ExistingResourceException();
        }
    }

    public Mascota mapToEntity(MascotaDTO mascotaDTO) {
        Mascota mascota = new Mascota(mascotaDTO.getId(), mascotaDTO.getNombre(),
                mascotaDTO.getTamanio());
        return mascota;
    }

    public MascotaDTO mapToDTO(Mascota mascota) {
        MascotaDTO mascotaDTO = new MascotaDTO(mascota.getId(), mascota.getNombre(),
                mascota.getTamanio());

        return mascotaDTO;
    }
}
