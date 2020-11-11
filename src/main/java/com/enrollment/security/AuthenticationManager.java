package com.enrollment.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		
		String token = authentication.getCredentials().toString();
		
		String usuario;
		try {
			usuario = jwtUtil.getUsernameFromToken(token);
		} catch (Exception e) {
			usuario = null;
		}
		if (usuario != null && jwtUtil.validateToken(token)) {
			Claims claims = jwtUtil.getAllClaimsFromToken(token);
			
			List<String> rolesMap = claims.get("roles", List.class);
			
			UsernamePasswordAuthenticationToken authent = new UsernamePasswordAuthenticationToken(
					usuario,  null, 
					rolesMap.stream().map(authrity -> new SimpleGrantedAuthority(authrity)).collect(Collectors.toList())
					);
			return Mono.just(authent);
		} else {
			return Mono.error(new InterruptedException("Token no valido o ha expirado"));
		}
	}

}
