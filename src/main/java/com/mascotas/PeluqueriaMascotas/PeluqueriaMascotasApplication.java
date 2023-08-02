package com.mascotas.PeluqueriaMascotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PeluqueriaMascotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeluqueriaMascotasApplication.class, args);
	}

}

/*
	Endpoints Mascotas / Servicios
	-  POST    localhost:8080/mascotas **** Crea una nueva mascota
		{
			"id": "12344",
			"nombre": "Mateo",
			"tamanio": "grande"
		}
	- GET      localhost:8080/mascotas *** todas las mascotas
	- PUT      localhost:8080/mascotas/{idMascota} *** modificar un registro de mascota
		{
				"nombre": "Mateo II",
				"tamanio": "grande"
		}
	- PATCH   localhost:8080/mascotas/{idMascota} *** modificar algunos campos
		{
			recibe los que uno considere
		}
	- DELETE localhost:8080/mascotas/{idMascota} *** elimina un registro




	Endpoints orden de servicio
	- POST localhost:8080/mascotas/{idMascota}/{idServicio}/orden-de-servicio *** crea una orden de servicio
		{
			"nombreContacto": "carlos aarias",
			"telefonoContacto": "31241241",
			"fechaServicio": "5/04/2022"
		}
	- GET localhost:8080/mascotas/{idMascota}/{idServicio}/orden-de-servicio/{idOrden} *** crea una orden de servicio
 		** hay una oportunidad de mejora que es traerse todos los registros
 */
