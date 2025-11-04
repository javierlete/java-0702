package com.ipartek.formacion.springtaller.servicios;

import java.util.Optional;

import com.ipartek.formacion.springtaller.entidades.Usuario;

public interface AnonimoService {
	Optional<Usuario> autenticar(String email, String password);
	Usuario registrar(Usuario usuario);
}
