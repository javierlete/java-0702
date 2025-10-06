package com.ipartek.formacion.taller.modelos;

import java.util.Objects;

public class Vehiculo {
	private Long id;
	private String matricula;
	private String bastidor;
	private String modelo;
	private String marca;

	private EstadoReparacion estadoReparacion = EstadoReparacion.RECIBIDO;

	public Vehiculo(Long id, String matricula, String bastidor, String modelo, String marca,
			EstadoReparacion estadoReparacion) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.bastidor = bastidor;
		this.modelo = modelo;
		this.marca = marca;
		this.estadoReparacion = estadoReparacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getBastidor() {
		return bastidor;
	}

	public void setBastidor(String bastidor) {
		this.bastidor = bastidor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public EstadoReparacion getEstadoReparacion() {
		return estadoReparacion;
	}

	public void setEstadoReparacion(EstadoReparacion estadoReparacion) {
		this.estadoReparacion = estadoReparacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bastidor, estadoReparacion, id, marca, matricula, modelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(bastidor, other.bastidor) && estadoReparacion == other.estadoReparacion
				&& Objects.equals(id, other.id) && Objects.equals(marca, other.marca)
				&& Objects.equals(matricula, other.matricula) && Objects.equals(modelo, other.modelo);
	}

	@Override
	public String toString() {
		return String.format("Vehiculo [id=%s, matricula=%s, bastidor=%s, modelo=%s, marca=%s, estadoReparacion=%s]",
				id, matricula, bastidor, modelo, marca, estadoReparacion);
	}

	public enum EstadoReparacion {
		RECIBIDO, PRESUPUESTO, NO_REPARABLE, PENDIENTE_ACEPTACION, EN_CURSO, REPARADO, FACTURADO, ENTREGADO
	}
}
