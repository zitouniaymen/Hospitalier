package com.bezkoder.spring.jpa.h2.controller;


import com.bezkoder.spring.jpa.h2.dtos.jwt.AuthenticationRequest;
import com.bezkoder.spring.jpa.h2.dtos.jwt.AuthenticationResponse;
import com.bezkoder.spring.jpa.h2.dtos.jwt.RegisterRequest;
import com.bezkoder.spring.jpa.h2.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public record AuthController(AuthenticationService authenticationService) {
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authenticationService.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
}
