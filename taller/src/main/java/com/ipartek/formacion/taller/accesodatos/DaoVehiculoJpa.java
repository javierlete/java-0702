package com.ipartek.formacion.taller.accesodatos;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJpa;
import com.ipartek.formacion.taller.modelos.Vehiculo;

public class DaoVehiculoJpa extends DaoJpa<Vehiculo> implements DaoVehiculo {

	public DaoVehiculoJpa() {
		super("com.ipartek.formacion.taller.modelos", Vehiculo.class);
	}

	@Override
	public Collection<Vehiculo> obtenerTodosConPropietario() {
		return ejecutarJpa(
				em -> em.createQuery("from Vehiculo v left join fetch v.propietario", Vehiculo.class).getResultList());
	}

	@Override
	public Optional<Vehiculo> buscarPorMatricula(String matricula) {
		return ejecutarJpa(em -> Optional.ofNullable(
				em.createQuery("from Vehiculo v left join fetch v.propietario where v.matricula=:matricula", Vehiculo.class)
						.setParameter("matricula", matricula).getSingleResultOrNull()));
	}

}
