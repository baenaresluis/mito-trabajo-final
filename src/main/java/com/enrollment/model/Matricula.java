package com.enrollment.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection = "matriculas")
public class Matricula {
	
	@Id
	private String id;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaMatricula;
	
	private Estudiante estudiante;
	
	private List<Curso> listCursos;
	
	private boolean estado;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getFechaMatricula() {
		return fechaMatricula;
	}

	public void setFechaMatricula(LocalDateTime fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public List<Curso> getListCursos() {
		return listCursos;
	}

	public void setListCursos(List<Curso> listCursos) {
		this.listCursos = listCursos;
	}
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
