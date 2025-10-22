package com.ipartek.formacion.taller.logicanegocio;

import java.util.Optional;

import com.ipartek.formacion.taller.modelos.Usuario;

public interface AnonimoNegocio {
	Optional<Usuario> autenticar(String email, String password);
	Usuario registrar(Usuario usuario);
}
