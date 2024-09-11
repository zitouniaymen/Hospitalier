package com.bezkoder.spring.jpa.h2.dtos.jwt;

public record AuthenticationRequest(String email, String password) {
}
