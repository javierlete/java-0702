package com.ipartek.formacion.bibliotecas.validaciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class Errores {
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
}
