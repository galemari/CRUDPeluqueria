package com.mascotas.PeluqueriaMascotas.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String valor;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<OrdenDeServicio> ordenDeServicios;

    public Servicio(){}

    public Servicio(Integer id, String nombre, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public Servicio(Integer id, String nombre, String valor, List<OrdenDeServicio> ordenDeServicios) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.ordenDeServicios = ordenDeServicios;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
            case "valor":
                this.valor = (String) newValue;
                break;
        }
    }
}
