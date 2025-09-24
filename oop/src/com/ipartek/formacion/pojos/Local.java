package com.ipartek.formacion.pojos;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Local implements Imprimible {
	private Long id;
	private String nombre;
	private Persona encargado;

	private HashSet<Visita> visitas = new HashSet<>();

	public Local(Long id, String nombre, Persona encargado) {
		setId(id);
		setNombre(nombre);
		setEncargado(encargado);
	}

	public Local(String nombre, Persona encargado) {
		this(null, nombre, encargado);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Persona getEncargado() {
		return encargado;
	}

	public void setEncargado(Persona encargado) {
		if (encargado == null) {
			throw new PojosException("Es obligatorio tener un encargado para el local");
		}

		this.encargado = encargado;
	}

	public Set<Visita> getVisitas() {
		return Collections.unmodifiableSet(visitas);
	}

	public Visita entrar(Persona persona) {
		if (persona == null) {
			throw new PojosException("No tiene sentido que entre null");
		}

		var visita = new Visita(persona);
		
		visitas.add(visita);
		
		return visita;
	}

	public void salir(Visita visita) {
		if (visita == null) {
			throw new PojosException("No tiene sentido que salga null");
		}
		
		visita.setSalida(LocalDateTime.now());
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", nombre=" + nombre + ", encargado=" + encargado + "]";
	}

	@Override
	public String imprimir() {
		return String.format("""
				Id:        %s
				Nombre:    %s
				Encargado: %s
				""", id, nombre, encargado);
	}

}
