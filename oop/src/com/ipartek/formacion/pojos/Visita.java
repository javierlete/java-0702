package com.ipartek.formacion.pojos;

import java.time.LocalDateTime;

public class Visita {
	private Long id;
	private Persona persona;
	private LocalDateTime entrada = LocalDateTime.now();
	private LocalDateTime salida;
	
	public Visita(Long id, Persona persona, LocalDateTime entrada) {
		setId(id);
		setPersona(persona);
		setEntrada(entrada);
		setSalida(null);
	}

	public Visita(Persona persona, LocalDateTime entrada) {
		this(null, persona, entrada);
	}

	public Visita(Persona persona) {
		this(null, persona, LocalDateTime.now());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	private void setPersona(Persona persona) {
		this.persona = persona;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	private void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSalida() {
		return salida;
	}

	public void setSalida(LocalDateTime salida) {
		this.salida = salida;
	}

	@Override
	public String toString() {
		return "Visita [id=" + id + ", persona=" + persona + ", entrada=" + entrada + ", salida=" + salida + "]";
	}
	
	
}
