package com.ipartek.formacion.taller.accesodatos;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.ipartek.formacion.taller.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoUsuarioJpa implements DaoUsuario {
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.taller.modelos");

	@Override
	public Collection<Usuario> obtenerTodos() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();

		t.commit();

		em.close();

		return usuarios;
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		Optional<Usuario> usuario = Optional.ofNullable(em.find(Usuario.class, id));

		t.commit();

		em.close();

		return usuario;
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.persist(usuario);

		t.commit();

		em.close();

		return usuario;
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.merge(usuario);

		t.commit();

		em.close();

		return usuario;
	}

	@Override
	public void borrar(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.remove(em.find(Usuario.class, id));

		t.commit();

		em.close();
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		Optional<Usuario> usuario = Optional.ofNullable(em.createQuery("from Usuario where email=:email", Usuario.class)
				.setParameter("email", email).getSingleResultOrNull());

		t.commit();

		em.close();

		return usuario;
	}

}
