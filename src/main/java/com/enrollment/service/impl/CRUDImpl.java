package com.enrollment.service.impl;

import com.enrollment.repo.IGenericRepo;
import com.enrollment.service.ICRUD;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepo<T, ID> getRepo();
	
	public Flux<T> listar(){
		return getRepo().findAll();
	}
	
	public Mono<T> listarPorId(ID id){
		return getRepo().findById(id);
	}
	
	public Mono<T> registrar(T t){
		return getRepo().save(t);
	}
	
	public Mono<T> modificar(T t){
		return getRepo().save(t);
	}
	
	public Mono<Void> eliminar(ID id){
		return getRepo().deleteById(id);
	}
}
