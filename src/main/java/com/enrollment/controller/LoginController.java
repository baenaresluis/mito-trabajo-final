package com.enrollment.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.enrollment.security.AuthRequest;
import com.enrollment.security.AuthResponse;
import com.enrollment.security.ErrorLogin;
import com.enrollment.security.JWTUtil;
import com.enrollment.service.IUsuarioService;

import reactor.core.publisher.Mono;

@RestController
public class LoginController {

	@Autowired
	private JWTUtil jwtUtil;
		
	@Autowired
	private IUsuarioService service;
	
	@PostMapping("/login")
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest autReq){
		return service.buscarPorUsuario(autReq.getUsername())
				.map((userDetails) -> {
					if(BCrypt.checkpw(autReq.getPassword(), userDetails.getPassword())) {
						String token = jwtUtil.generateToken(userDetails);
						Date expiration = jwtUtil.getExpirationDateFromToken(token);
						return ResponseEntity.ok(new AuthResponse(token, expiration));
					} else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorLogin("Credenciales incorrectas", new Date()));
					}
				}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/v2/login")
	public Mono<ResponseEntity<?>> login(@RequestHeader("usuario") String usuario, @RequestHeader("clave") String clave){
		return service.buscarPorUsuario(usuario)
				.map((userDetails) -> {
					if(BCrypt.checkpw(clave, userDetails.getPassword())) {
						String token = jwtUtil.generateToken(userDetails);
						Date expiration = jwtUtil.getExpirationDateFromToken(token);
						return ResponseEntity.ok(new AuthResponse(token, expiration));
					} else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorLogin("Credenciales incorrectas", new Date()));
					}
				}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}
