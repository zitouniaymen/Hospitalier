package com.bezkoder.spring.jpa.h2.Enum;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
@Getter
public enum Role implements GrantedAuthority {
ROLE_USER("USER"),
ROLE_ADMIN("ADMIN");

private String value;
	
	Role(String value) {
		this.value = value;
	}
	
	@Override
	public String getAuthority() {
		return name();
	}
}