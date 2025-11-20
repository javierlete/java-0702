package com.ipartek.formacion.amazonia.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ipartek.formacion.amazonia.modelos.Rol;
import com.ipartek.formacion.amazonia.modelos.Usuario;

public class UsuarioLogin extends Usuario implements UserDetails {

	private static final long serialVersionUID = 677749961506043139L;

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public Rol getRol() {
		return super.getRol() != null ? super.getRol() : new Rol();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return super.getRol() != null ? List.of(() -> "ROLE_" + getRol().getNombre()) : List.of();
	}
}
