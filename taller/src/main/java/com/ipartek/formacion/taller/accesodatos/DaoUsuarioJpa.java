package com.ipartek.formacion.taller.accesodatos;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJpa;
import com.ipartek.formacion.taller.modelos.Usuario;

public class DaoUsuarioJpa implements DaoUsuario {

	private static final DaoJpa dao = new DaoJpa("com.ipartek.formacion.taller.modelos");
	
	@Override
	public Collection<Usuario> obtenerTodos() {
		return dao.ejecutarJpa(em -> em.createQuery("from Usuario", Usuario.class).getResultList());
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		return dao.ejecutarJpa(em -> Optional.ofNullable(em.find(Usuario.class, id)));
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		return dao.ejecutarJpa(em -> {
			em.persist(usuario);
			return usuario;
		});
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		return dao.ejecutarJpa(em -> {
			em.merge(usuario);
			return usuario;
		});
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarJpa(em -> {
			em.remove(em.find(Usuario.class, id));
			return null;
		});
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return dao.ejecutarJpa(em -> Optional.ofNullable(em.createQuery("from Usuario where email=:email", Usuario.class)
				.setParameter("email", email).getSingleResultOrNull()));
	}

}
