package com.ipartek.formacion.citas.accesodatos;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {
	Collection<T> obtenerTodos();
	Optional<T> obtenerPorId(Long id);
	
	T insertar(T objeto);
	T modificar(T objeto);
	void borrar(Long id);
}
