package com.ipartek.formacion.bibliotecas.validaciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class Errores {
	private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = validatorFactory.getValidator();
	
	public static <T> Map<Object, Object> violationsAErrores(Set<ConstraintViolation<T>> violations) {
//		return violations.stream()
//				.collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));

		var errores = new HashMap<Object, Object>();

		for (var violation : violations) {
			var clave = violation.getPropertyPath().toString();
			var valor = violation.getMessage();

			if (errores.containsKey(clave)) {
				valor = errores.get(clave) + ", " + valor;
			}
			
			errores.put(clave, valor);
		}

		return errores;
	}

	public static <T> void validar(T objeto) {
		Set<ConstraintViolation<T>> violations = validator.validate(objeto);
	
		if (violations.size() > 0) {
			var errores = violationsAErrores(violations);
	
			throw ValidacionException.builder().errores(errores).build();
		}
	}
}
