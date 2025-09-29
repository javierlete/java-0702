package com.ipartek.formacion.citas.accesodatos;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.TreeMap;

import com.ipartek.formacion.citas.entidades.Cita;

public class DaoCitaTreeMap implements DaoCita {

	private TreeMap<Long, Cita> citas = new TreeMap<>();

	public DaoCitaTreeMap() {
		insertar(new Cita("Cita TREEMAP", LocalDateTime.of(2025, 9, 24, 15, 0), LocalDateTime.of(2025, 9, 24, 20, 0)));
	}

	@Override
	public Collection<Cita> obtenerTodos() {
		return Collections.unmodifiableCollection(citas.values());
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return Optional.of(citas.get(id));
	}

	@Override
	public Cita insertar(Cita cita) {
		Long id = citas.isEmpty() ? 1L : citas.lastKey() + 1L;

		cita.setId(id);

		citas.put(id, cita);

		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		citas.put(cita.getId(), cita);

		return cita;
	}

	@Override
	public void borrar(Long id) {
		citas.remove(id);
	}

	@Override
	public Collection<Cita> buscarPorTexto(String texto) {
		return citas.values().stream().filter(c -> c.getTexto().contains(texto)).toList();
	}
}
