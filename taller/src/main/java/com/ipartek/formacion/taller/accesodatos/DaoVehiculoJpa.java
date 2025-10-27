package com.ipartek.formacion.taller.accesodatos;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJpa;
import com.ipartek.formacion.taller.modelos.Vehiculo;

public class DaoVehiculoJpa implements DaoVehiculo {
private static final DaoJpa dao = new DaoJpa("com.ipartek.formacion.taller.modelos");
	
	@Override
	public Collection<Vehiculo> obtenerTodos() {
		return dao.ejecutarJpa(em -> em.createQuery("from Vehiculo", Vehiculo.class).getResultList());
	}

	@Override
	public Optional<Vehiculo> obtenerPorId(Long id) {
		return dao.ejecutarJpa(em -> Optional.ofNullable(em.find(Vehiculo.class, id)));
	}

	@Override
	public Vehiculo insertar(Vehiculo vehiculo) {
		return dao.ejecutarJpa(em -> {
			em.persist(vehiculo);
			return vehiculo;
		});
	}

	@Override
	public Vehiculo modificar(Vehiculo vehiculo) {
		return dao.ejecutarJpa(em -> {
			em.merge(vehiculo);
			return vehiculo;
		});
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarJpa(em -> {
			em.remove(em.find(Vehiculo.class, id));
			return null;
		});
	}

	@Override
	public Optional<Vehiculo> buscarPorMatricula(String matricula) {
		return dao.ejecutarJpa(em -> Optional.ofNullable(em.createQuery("from Vehiculo where matricula=:matricula", Vehiculo.class)
				.setParameter("matricula", matricula).getSingleResultOrNull()));
	}

}
