package com.ipartek.formacion.taller.logicanegocio;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.bibliotecas.validaciones.Errores;
import com.ipartek.formacion.bibliotecas.validaciones.ValidacionException;
import com.ipartek.formacion.taller.accesodatos.DaoUsuario;
import com.ipartek.formacion.taller.accesodatos.DaoVehiculo;
import com.ipartek.formacion.taller.modelos.Usuario;
import com.ipartek.formacion.taller.modelos.Vehiculo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	private static final DaoVehiculo DAO_VEHICULO = (DaoVehiculo) Fabrica.obtenerObjeto("dao.vehiculo");
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");

	private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = validatorFactory.getValidator();
	
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
		Set<ConstraintViolation<Vehiculo>> violations = validator.validate(vehiculo);

		if (violations.size() > 0) {
			var errores = Errores.violationsAErrores(violations);

			throw ValidacionException.builder().errores(errores).build();
		}

		return DAO_VEHICULO.insertar(vehiculo);
	}

	@Override
	public Vehiculo modificacionVehiculo(Vehiculo vehiculo) {
		Set<ConstraintViolation<Vehiculo>> violations = validator.validate(vehiculo);

		if (violations.size() > 0) {
			var errores = Errores.violationsAErrores(violations);

			throw ValidacionException.builder().errores(errores).build();
		}
		
		return DAO_VEHICULO.modificar(vehiculo);
	}

	@Override
	public void bajaVehiculo(Long id) {
		DAO_VEHICULO.borrar(id);
	}

	@Override
	public Collection<Usuario> listadoUsuarios() {
		return DAO_USUARIO.obtenerTodos();
	}

}
