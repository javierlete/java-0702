package com.ipartek.formacion.springtaller.servicios;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.springtaller.entidades.Usuario;
import com.ipartek.formacion.springtaller.entidades.Vehiculo;

public interface UsuarioService {
	Collection<Vehiculo> listadoVehiculos();
	Optional<Vehiculo> detalleVehiculo(Long id);
	Optional<Vehiculo> detalleVehiculo(String matricula);
	
	Vehiculo altaVehiculo(Vehiculo vehiculo);
	Vehiculo modificacionVehiculo(Vehiculo vehiculo);
	void bajaVehiculo(Long id);
	
	Collection<Usuario> listadoUsuarios();
}
