package com.ipartek.formacion.taller.logicanegocio;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.taller.accesodatos.DaoVehiculo;
import com.ipartek.formacion.taller.modelos.Vehiculo;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	private static final DaoVehiculo DAO_VEHICULO = (DaoVehiculo) Fabrica.obtenerObjeto("dao.vehiculo");

	@Override
	public Collection<Vehiculo> listadoVehiculos() {
		return DAO_VEHICULO.obtenerTodosConPropietario();
	}

	@Override
	public Optional<Vehiculo> detalleVehiculo(Long id) {
		return DAO_VEHICULO.obtenerPorIdConPropietario(id);
	}

	@Override
	public Optional<Vehiculo> detalleVehiculo(String matricula) {
		return DAO_VEHICULO.buscarPorMatricula(matricula);
	}

	@Override
	public Vehiculo altaVehiculo(Vehiculo vehiculo) {
		return DAO_VEHICULO.insertar(vehiculo);
	}

	@Override
	public Vehiculo modificacionVehiculo(Vehiculo vehiculo) {
		return DAO_VEHICULO.modificar(vehiculo);
	}

	@Override
	public void bajaVehiculo(Long id) {
		DAO_VEHICULO.borrar(id);
	}

}
