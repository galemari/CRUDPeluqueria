package com.mascotas.PeluqueriaMascotas.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.mascotas.PeluqueriaMascotas.entity.OrdenDeServicio;

public class OrdenDeServicioDTO {
    private Integer id;
    @JsonAlias("nombre_contacto")
    private String nombreContacto;
    @JsonAlias("telefono_contacto")
    private String telefonoContacto;
    @JsonAlias("fecha_servicio")
    private String fechaServicio;

    private MascotaDTO mascotaDTO;


    private ServicioDTO servicioDTO;

    public OrdenDeServicioDTO(String nombreContacto, String telefonoContacto, String fechaServicio, MascotaDTO mascotaDTO, ServicioDTO servicioDTO) {
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.fechaServicio = fechaServicio;
        this.mascotaDTO = mascotaDTO;
        this.servicioDTO = servicioDTO;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public String getFechaServicio() {
        return fechaServicio;
    }

    public MascotaDTO getMascotaDTO() {
        return mascotaDTO;
    }

    public ServicioDTO getServicioDTO() {
        return servicioDTO;
    }
}
