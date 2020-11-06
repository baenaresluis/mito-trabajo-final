package com.enrollment.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "estudiantes")
public class Estudiante {

	@Id
	private String Id;
	
	@NotEmpty
	@Field(name = "nombres")
	private String nombres;
	
	@NotEmpty
	@Field(name = "apellidos")
	private String apellidos;
	
	@NotEmpty
	@Field(name = "dni")
	private String DNI;
	
	@Field(name = "edad")
	private int edad;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}
