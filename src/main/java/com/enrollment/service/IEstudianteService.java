package com.enrollment.service;

import com.enrollment.model.Estudiante;

import reactor.core.publisher.Flux;

public interface IEstudianteService extends ICRUD<Estudiante, String>{

	Flux<Estudiante> listaEstOrdenada();
}
