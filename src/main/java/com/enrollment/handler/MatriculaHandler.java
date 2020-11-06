package com.enrollment.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.enrollment.model.Matricula;
import com.enrollment.service.IMatriculaService;
import com.enrollment.validator.RequestValidator;

import reactor.core.publisher.Mono;

@Component
public class MatriculaHandler {

	@Autowired
	private IMatriculaService service;
	
	@Autowired
	private RequestValidator validatorGeneral;
	
	public Mono<ServerResponse> listar(ServerRequest req){
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listar(), Matricula.class);
	}
	
	public Mono<ServerResponse> listarPorId(ServerRequest req){
		String id = req.pathVariable("id");
		return service.listarPorId(id)
				.flatMap(m -> ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(m))
				)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> registrar(ServerRequest req){
		Mono<Matricula> monoMatricula = req.bodyToMono(Matricula.class);
		return monoMatricula
				.flatMap(validatorGeneral::validate)
				.flatMap(service::registrar)
				.flatMap(m -> ServerResponse.created(URI.create(req.uri().toString().concat("/").concat(m.getId())))
				.body(fromValue(m))
				);
	}
	
	public Mono<ServerResponse> modificar(ServerRequest req){
		Mono<Matricula> monoMatricula = req.bodyToMono(Matricula.class);
		return monoMatricula
				.flatMap(validatorGeneral::validate)
				.flatMap(service::modificar)
				.flatMap(m -> ServerResponse.created(URI.create(req.uri().toString().concat("/").concat(m.getId())))
				.body(fromValue(m))
				);
	}
	
	public Mono<ServerResponse> eliminar(ServerRequest req){
		String id = req.pathVariable("id");
		return service.listarPorId(id)
				.flatMap(m -> service.eliminar(m.getId())
						.then(ServerResponse.noContent().build())
				)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}
