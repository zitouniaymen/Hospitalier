package com.bezkoder.spring.jpa.h2.controller;


import com.bezkoder.spring.jpa.h2.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public record DemoController() {


//    @GetMapping
//    public String sayHello(Authentication authentication) {
//        return """
//                Hello %s 🥳 !
//                Welcome to a very secured page  😱
//                """.formatted(getName(authentication));
//    }
//
//    private String getName(Authentication authentication) {
//        return Optional.of(authentication)
//                .filter(User.class::isInstance)
//                .map(User.class::cast)
//                .map(User::getEmail)
//                .orElseGet(authentication::getName);
//    }
	
	@GetMapping("/me")
	public ResponseEntity<User> authenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();

		return ResponseEntity.ok(currentUser);
	}

}
