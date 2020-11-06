package com.enrollment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enrollment.model.Matricula;
import com.enrollment.repo.IGenericRepo;
import com.enrollment.repo.IMatriculaRepo;
import com.enrollment.service.IMatriculaService;


@Service
public class MatriculaServiceImpl extends CRUDImpl<Matricula, String> implements IMatriculaService{

	@Autowired
	private IMatriculaRepo repo;
	
	protected IGenericRepo<Matricula, String> getRepo() {
		return repo;
	}

}
