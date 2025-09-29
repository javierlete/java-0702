package com.ipartek.formacion.citas.accesodatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import com.ipartek.formacion.citas.entidades.Cita;

public class DaoCitaArrayList implements DaoCita {

	private final ArrayList<Cita> citas = new ArrayList<>();

	@Override
	public Collection<Cita> obtenerTodos() {
		return Collections.unmodifiableCollection(citas);
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return citas.stream().filter(c -> c.getId() == id).findFirst();
	}

	@Override
	public Cita insertar(Cita cita) {
		Long id = citas.stream().map(c -> c.getId()).max(Long::compare).orElse(0L) + 1L;
		
		cita.setId(id);
		
		citas.add(cita);
		
		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		return buscarPorIdYEjecutar(cita, i -> citas.set(i, cita));
	}

	@Override
	public void borrar(Long id) {
		buscarPorIdYEjecutar(id, i -> { 
			citas.remove((int)i);
			return null;
		});
	}
	
	private Cita buscarPorIdYEjecutar(Long id, Function<Integer, Cita> ejecutar) {
		Cita cita = new Cita(id, null, null, null);
		return buscarPorIdYEjecutar(cita, ejecutar);
	}
	
	private Cita buscarPorIdYEjecutar(Cita cita, Function<Integer, Cita> ejecutar) {
		if(cita.getId() == null) {
			throw new AccesoDatosException("Debes proporcionar un id");
		}
		
		for(int i = 0; i < citas.size(); i++) {
			if(cita.getId() == citas.get(i).getId()) {
				return ejecutar.apply(i);
			}
		}
		
		throw new AccesoDatosException("No se ha encontrado el registro");
	}

	@Override
	public Collection<Cita> buscarPorTexto(String texto) {
		return Collections.unmodifiableCollection(citas.stream().filter(c -> c.getTexto().contains(texto)).toList());
	}

}
