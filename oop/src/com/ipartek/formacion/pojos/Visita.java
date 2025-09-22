package com.ipartek.formacion.pojos;

import java.time.LocalDateTime;

public class Visita extends Persona {
	private LocalDateTime entrada = LocalDateTime.now();
	private LocalDateTime salida;

	public Visita(Persona persona, LocalDateTime entrada) {
		super(persona);

		setEntrada(entrada);
		setSalida(null);
	}

	public Visita(Persona persona) {
		this(persona, LocalDateTime.now());
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

	void setSalida(LocalDateTime salida) {
		this.salida = salida;
	}

	@Override
	public String toString() {
		return String.format("Visita [id=%s, nombre=%s, entrada=%s, salida=%s], %s", id, nombre, entrada, salida,
				super.toString());
	}

}
