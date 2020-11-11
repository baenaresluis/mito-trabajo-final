package com.enrollment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enrollment.model.Estudiante;
import com.enrollment.repo.IEstudianteRepo;
import com.enrollment.repo.IGenericRepo;
import com.enrollment.service.IEstudianteService;

import reactor.core.publisher.Flux;


@Service
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, String> implements IEstudianteService{

	@Autowired
	private IEstudianteRepo repo;
	
	protected IGenericRepo<Estudiante, String> getRepo() {
		return repo;
	}

	@Override
	public Flux<Estudiante> listaEstOrdenada() {
		return repo.findAll(Sort.by(Sort.Direction.DESC, "edad"));
	}

}
