package com.enrollment.repo;

import com.enrollment.model.Usuario;

import reactor.core.publisher.Mono;

public interface IUsuarioRepo extends IGenericRepo<Usuario, String>{

	Mono<Usuario> findOneByUsuario(String usuario);
}
