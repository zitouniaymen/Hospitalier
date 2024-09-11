package com.bezkoder.spring.jpa.h2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Table(name = "users")
@Entity
@Builder
@Getter
@Setter
public class User implements UserDetails {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "lastname")
	private String lastname;
	@NotNull(message = "L'email est obligatoire")
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "password")
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;
	
	public User() {
	}
	
	public User(String firstname, String lastname, String email, String password, Role role){
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(role.name());
		//return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
