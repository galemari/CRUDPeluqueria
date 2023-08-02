package com.mascotas.PeluqueriaMascotas.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tamanio;

    @OneToMany(mappedBy = "mascota", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<OrdenDeServicio> ordenDeServicios;

    public Mascota(){}

    public Mascota(Integer id, String nombre, String tamanio) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
    }

    public Mascota(Integer id, String nombre, String tamanio, List<OrdenDeServicio> ordenDeServicios) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.ordenDeServicios = ordenDeServicios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public List<OrdenDeServicio> getOrdenDeServicios() {
        if (ordenDeServicios == null) {
            ordenDeServicios = new ArrayList<>();
        }
        return ordenDeServicios;
    }

    public void setOrdenDeServicios(List<OrdenDeServicio> ordenDeServicios) {
        this.ordenDeServicios = ordenDeServicios;
    }

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "nombre":
                this.nombre = (String) newValue;
                break;
            case "tamanio":
                this.tamanio = (String) newValue;
                break;
        }
    }
}
