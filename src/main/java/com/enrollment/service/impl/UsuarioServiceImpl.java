package com.enrollment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enrollment.model.Usuario;
import com.enrollment.repo.IGenericRepo;
import com.enrollment.repo.IRolRepo;
import com.enrollment.repo.IUsuarioRepo;
import com.enrollment.security.User;
import com.enrollment.service.IUsuarioService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, String> implements IUsuarioService{

	@Autowired
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private IRolRepo rolRepo;
	
	@Override
	protected IGenericRepo<Usuario, String> getRepo() {
		return usuarioRepo;
	}
	
	@Override
	public Mono<User> buscarPorUsuario(String usuario) {
		Mono<Usuario> monoUsuario = usuarioRepo.findOneByUsuario(usuario);
		List<String> roles = new ArrayList<String>();
		
		return monoUsuario.flatMap(usu -> {
			return Flux.fromIterable(usu.getRoles())
					.flatMap(rol -> {
						return rolRepo.findById(rol.getId())
								.map(r -> {
									roles.add(r.getNombre());
									return r;
								});
					}).collectList().flatMap(list -> {
						usu.setRoles(list);
						return Mono.just(usu);
					});
		}).flatMap(usu -> {
			return Mono.just(new User(usu.getUsuario(), usu.getClave(), usu.getEstado(), roles));
		});
	}

}
