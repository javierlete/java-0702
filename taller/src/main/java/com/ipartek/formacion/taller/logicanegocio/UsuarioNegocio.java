package com.ipartek.formacion.taller.logicanegocio;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.taller.modelos.Vehiculo;

public interface UsuarioNegocio {
	Collection<Vehiculo> listadoVehiculos();
	Optional<Vehiculo> detalleVehiculo(Long id);
	Optional<Vehiculo> detalleVehiculo(String matricula);
}
