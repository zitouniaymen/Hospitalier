package com.bezkoder.spring.jpa.h2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();
		
		UserDetails admin = User.withDefaultPasswordEncoder()
			.username("admin")
			.password("adminpassword")
			.roles("ADMIN")
			.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
}
