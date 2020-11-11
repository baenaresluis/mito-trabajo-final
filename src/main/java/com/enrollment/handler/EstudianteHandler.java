package com.enrollment.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.enrollment.model.Estudiante;
import com.enrollment.service.IEstudianteService;
import com.enrollment.validator.RequestValidator;

import reactor.core.publisher.Mono;

@Component
public class EstudianteHandler {

	@Autowired
	private IEstudianteService service;
	
	@Autowired
	private RequestValidator validatorGeneral;
	
	public Mono<ServerResponse> listar(ServerRequest req){
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listar(), Estudiante.class);
	}
	
	public Mono<ServerResponse> listarPorId(ServerRequest req){
		String id = req.pathVariable("id");
		return service.listarPorId(id)
				.flatMap(e -> ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(e))
				)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> listaEstOrdenada(){
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listaEstOrdenada(), Estudiante.class);
	}
	
	public Mono<ServerResponse> registrar(ServerRequest req){
		Mono<Estudiante> monoEstudiante = req.bodyToMono(Estudiante.class);
		return monoEstudiante
				.flatMap(validatorGeneral::validate)
				.flatMap(service::registrar)
				.flatMap(e -> ServerResponse.created(URI.create(req.uri().toString().concat("/").concat(e.getId())))
				.body(fromValue(e))
				);
	}
	
	public Mono<ServerResponse> modificar(ServerRequest req){
		Mono<Estudiante> monoEstudiante = req.bodyToMono(Estudiante.class);
		return monoEstudiante
				.flatMap(validatorGeneral::validate)
				.flatMap(service::modificar)
				.flatMap(e -> ServerResponse.created(URI.create(req.uri().toString().concat("/").concat(e.getId())))
				.body(fromValue(e))
				);
	}
	
	public Mono<ServerResponse> eliminar(ServerRequest req){
		String id = req.pathVariable("id");
		return service.listarPorId(id)
				.flatMap(e -> service.eliminar(e.getId())
						.then(ServerResponse.noContent().build())
				)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}
