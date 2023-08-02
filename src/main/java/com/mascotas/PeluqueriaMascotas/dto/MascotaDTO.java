package com.mascotas.PeluqueriaMascotas.dto;

public class MascotaDTO {
    private Integer id;

    private String nombre;

    private String tamanio;

    public MascotaDTO(Integer id, String nombre, String tamanio) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio = tamanio;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }
}
