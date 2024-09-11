package com.bezkoder.spring.jpa.h2.service;


import com.bezkoder.spring.jpa.h2.dtos.jwt.AuthenticationRequest;
import com.bezkoder.spring.jpa.h2.dtos.jwt.AuthenticationResponse;
import com.bezkoder.spring.jpa.h2.dtos.jwt.RegisterRequest;
import com.bezkoder.spring.jpa.h2.model.Role;
import com.bezkoder.spring.jpa.h2.model.User;
import com.bezkoder.spring.jpa.h2.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record AuthenticationService(UserRepository userRepository,
				    PasswordEncoder passwordEncoder,
				    AuthenticationManager authenticationManager) {
    public AuthenticationResponse register(RegisterRequest request) {
        final var user = new User(request.firstname(),request.lastname(),  request.email(),  passwordEncoder.encode(request.password()), request.role()); userRepository.save(user);
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        final var user = userRepository.findByEmail(request.email()).orElseThrow();
        final var token = JwtService.generateToken(user);
        return new AuthenticationResponse(token);

    }
}
