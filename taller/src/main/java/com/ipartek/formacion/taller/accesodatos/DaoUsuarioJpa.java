package com.ipartek.formacion.taller.accesodatos;

import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJpa;
import com.ipartek.formacion.taller.modelos.Usuario;

public class DaoUsuarioJpa extends DaoJpa<Usuario> implements DaoUsuario {

	public DaoUsuarioJpa() {
		super("com.ipartek.formacion.taller.modelos", Usuario.class);
	}
	
	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return ejecutarJpa(em -> Optional.ofNullable(em.createQuery("from Usuario u left join fetch u.rol where u.email=:email", Usuario.class)
				.setParameter("email", email).getSingleResultOrNull()));
	}

}
