package com.ipartek.formacion.pojos;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Local {
	private Long id;
	private String nombre;
	private Persona encargado;
	
	private HashSet<Persona> visitas = new HashSet<>();

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
			throw new RuntimeException("Es obligatorio tener un encargado para el local");
		}

		this.encargado = encargado;
	}

	public Set<Persona> getVisitas() {
		return Collections.unmodifiableSet(visitas);
	}
	
	public void entrar(Persona persona) {
		if(persona == null) {
			throw new RuntimeException("No tiene sentido que entre null");
		}
		
		visitas.add(persona);
	}
	
	public void salir(Persona persona) {
		if(persona == null) {
			throw new RuntimeException("No tiene sentido que salga null");
		}

		visitas.remove(persona);
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", nombre=" + nombre + ", encargado=" + encargado + "]";
	}

}
