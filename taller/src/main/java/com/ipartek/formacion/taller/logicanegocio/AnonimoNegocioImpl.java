package com.ipartek.formacion.taller.logicanegocio;

import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.taller.accesodatos.DaoUsuario;
import com.ipartek.formacion.taller.modelos.Usuario;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");

	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		var usuario = DAO_USUARIO.obtenerPorEmail(email);

		if (usuario.isPresent() && !usuario.get().getPassword().equals(password)) {
			return Optional.empty();
		}

		return usuario;
	}

	@Override
	public Usuario registrar(Usuario usuario) {
		return DAO_USUARIO.insertar(usuario);
	}

}
