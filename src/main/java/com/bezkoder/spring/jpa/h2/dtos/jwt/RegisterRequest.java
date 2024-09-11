package com.bezkoder.spring.jpa.h2.dtos.jwt;

import com.bezkoder.spring.jpa.h2.model.Role;

public record RegisterRequest(String firstname, String lastname, String email, String password, Role role){
}
