package com.ipartek.formacion.taller.accesodatos;

import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJpa;
import com.ipartek.formacion.taller.modelos.Vehiculo;

public class DaoVehiculoJpa extends DaoJpa<Vehiculo> implements DaoVehiculo {

	public DaoVehiculoJpa() {
		super("com.ipartek.formacion.taller.modelos", Vehiculo.class);
	}
	
	@Override
	public Optional<Vehiculo> buscarPorMatricula(String matricula) {
		return ejecutarJpa(em -> Optional.ofNullable(em.createQuery("from Vehiculo where matricula=:matricula", Vehiculo.class)
				.setParameter("matricula", matricula).getSingleResultOrNull()));
	}

}
