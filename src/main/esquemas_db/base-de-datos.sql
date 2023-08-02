CREATE DATABASE IF NOT EXISTS peluqueria_mascotas;

USE peluqueria_mascotas;

CREATE TABLE IF NOT EXISTS mascota (
	id VARCHAR(10) NOT NULL UNIQUE,
	nombre VARCHAR(100) NOT NULL,
	tamanio VARCHAR(10) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS servicio (
	id VARCHAR(10) NOT NULL UNIQUE,
	nombre VARCHAR(100) NOT NULL,
	valor INT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS orden_servicio (
	id INT NOT NULL AUTO_INCREMENT,
	nombre_contacto VARCHAR(100) NOT NULL,
	telefono_contacto VARCHAR(100) NOT NULL,
	fecha_servicio DATE NOT NULL,
	id_mascota VARCHAR(10) NOT NULL,
    id_servicio VARCHAR(10) NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_id_mascota_idx
		FOREIGN KEY (id_mascota) REFERENCES mascota (id),
	CONSTRAINT fk_id_servicio_idx
		FOREIGN KEY (id_servicio) REFERENCES servicio (id)
);