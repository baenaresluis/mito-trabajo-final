package com.enrollment.service;

import com.enrollment.model.Usuario;
import com.enrollment.security.User;

import reactor.core.publisher.Mono;

public interface IUsuarioService extends ICRUD<Usuario, String> {

	Mono<User> buscarPorUsuario(String usuario);
}
