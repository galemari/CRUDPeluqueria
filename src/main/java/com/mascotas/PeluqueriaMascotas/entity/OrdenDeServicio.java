package com.mascotas.PeluqueriaMascotas.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "orden_servicio")
public class OrdenDeServicio {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "nombre_contacto", nullable = false)
    private String nombreContacto;

    @Column(name= "telefono_contacto", nullable = false)
    private String telefonoContacto;

    @Column(name="fecha_servicio", nullable = false)
    private LocalDate fechaServicio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_mascota", nullable = false)
    private Mascota mascota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_servicio", nullable = false)
    private Servicio servicio;

    public OrdenDeServicio() {}

    public OrdenDeServicio(String nombreContacto, String telefonoContacto, LocalDate fechaServicio, Mascota mascota, Servicio servicio) {
        this.nombreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.fechaServicio = fechaServicio;
        this.mascota = mascota;
        this.servicio = servicio;
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

    public LocalDate getFechaServicio() {
        return fechaServicio;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Servicio getServicio() {
        return servicio;
    }
}
