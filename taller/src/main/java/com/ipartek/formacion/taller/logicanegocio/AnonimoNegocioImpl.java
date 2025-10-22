package com.ipartek.formacion.taller.logicanegocio;

import java.util.Optional;
import java.util.Set;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.bibliotecas.validaciones.Errores;
import com.ipartek.formacion.bibliotecas.validaciones.ValidacionException;
import com.ipartek.formacion.taller.accesodatos.DaoUsuario;
import com.ipartek.formacion.taller.modelos.Usuario;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");

	private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = validatorFactory.getValidator();
	
	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		var usuario = DAO_USUARIO.obtenerPorEmail(email);

		if (usuario.isPresent() && !usuario.get().getPassword().equals(password)) {
			return Optional.empty();
		}

		return usuario;
	}

	@Override
	public Usuario registrar(Usuario usuario) {
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		
		if(violations.size() > 0) {
			var errores = Errores.violationsAErrores(violations);
			
			throw ValidacionException.builder().errores(errores).build();
		}
		
		return DAO_USUARIO.insertar(usuario);
	}

}
