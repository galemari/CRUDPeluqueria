package com.mascotas.PeluqueriaMascotas.repository;

import com.mascotas.PeluqueriaMascotas.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}
