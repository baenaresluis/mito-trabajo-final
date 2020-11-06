package com.enrollment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enrollment.model.Curso;
import com.enrollment.repo.ICursoRepo;
import com.enrollment.repo.IGenericRepo;
import com.enrollment.service.ICursoService;


@Service
public class CursoServiceImpl extends CRUDImpl<Curso, String> implements ICursoService{

	@Autowired
	private ICursoRepo repo;
	
	protected IGenericRepo<Curso, String> getRepo() {
		return repo;
	}

}
