package com.ipartek.formacion.springtaller.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ipartek.formacion.springtaller.entidades.Usuario;

public class UsuarioLogin extends Usuario implements UserDetails {

	private static final long serialVersionUID = 677749961506043139L;

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (getRol() != null) {
			return List.of(() -> "ROLE_" + getRol().getNombre());
		} else {
			return List.of();
		}
	}
}
