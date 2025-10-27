package com.ipartek.formacion.taller.accesodatos;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.ipartek.formacion.taller.modelos.Vehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoVehiculoJpa implements DaoVehiculo {
	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.taller.modelos");

	@Override
	public Collection<Vehiculo> obtenerTodos() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		List<Vehiculo> vehiculos = em.createQuery("from Vehiculo", Vehiculo.class).getResultList();

		t.commit();

		em.close();

		return vehiculos;
	}

	@Override
	public Optional<Vehiculo> obtenerPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		Optional<Vehiculo> vehiculo = Optional.ofNullable(em.find(Vehiculo.class, id));

		t.commit();

		em.close();

		return vehiculo;
	}

	@Override
	public Vehiculo insertar(Vehiculo vehiculo) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.persist(vehiculo);

		t.commit();

		em.close();

		return vehiculo;
	}

	@Override
	public Vehiculo modificar(Vehiculo vehiculo) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.merge(vehiculo);

		t.commit();

		em.close();

		return vehiculo;
	}

	@Override
	public void borrar(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		em.remove(em.find(Vehiculo.class, id));

		t.commit();

		em.close();
	}

	@Override
	public Optional<Vehiculo> buscarPorMatricula(String matricula) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		Optional<Vehiculo> vehiculo = Optional.ofNullable(em.createQuery("from Vehiculo where matricula=:matricula", Vehiculo.class)
				.setParameter("matricula", matricula).getSingleResultOrNull());

		t.commit();

		em.close();

		return vehiculo;
	}

}
