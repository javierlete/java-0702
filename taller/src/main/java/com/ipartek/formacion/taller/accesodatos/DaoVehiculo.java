package com.ipartek.formacion.taller.accesodatos;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.AccesoDatosException;
import com.ipartek.formacion.bibliotecas.accesodatos.Dao;
import com.ipartek.formacion.taller.modelos.Vehiculo;

public interface DaoVehiculo extends Dao<Vehiculo> {
	default Collection<Vehiculo> obtenerTodosConPropietario() {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
	
	default Optional<Vehiculo> obtenerPorIdConPropietario(Long id) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
	
	Optional<Vehiculo> buscarPorMatricula(String matricula);
}
