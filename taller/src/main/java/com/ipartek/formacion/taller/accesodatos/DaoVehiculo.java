package com.ipartek.formacion.taller.accesodatos;

import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.Dao;
import com.ipartek.formacion.taller.modelos.Vehiculo;

public interface DaoVehiculo extends Dao<Vehiculo> {
	Optional<Vehiculo> buscarPorMatricula(String matricula);
}
