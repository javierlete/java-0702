package com.ipartek.formacion.pojos;

import java.math.BigDecimal;
import java.util.Objects;

public class EmpleadoPorHoras extends Empleado {
	private Integer horasPorMes;
	private BigDecimal sueldoHora;

	public EmpleadoPorHoras(Long id, String nombre, String dni, String nss, Integer horasPorMes,
			BigDecimal sueldoHora) {
		super(id, nombre, dni, nss);
		this.horasPorMes = horasPorMes;
		this.sueldoHora = sueldoHora;
	}

	public Integer getHorasPorMes() {
		return horasPorMes;
	}

	public void setHorasPorMes(Integer horasPorMes) {
		this.horasPorMes = horasPorMes;
	}

	public BigDecimal getSueldoHora() {
		return sueldoHora;
	}

	public void setSueldoHora(BigDecimal sueldoHora) {
		this.sueldoHora = sueldoHora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(horasPorMes, sueldoHora);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpleadoPorHoras other = (EmpleadoPorHoras) obj;
		return Objects.equals(horasPorMes, other.horasPorMes) && Objects.equals(sueldoHora, other.sueldoHora);
	}

	@Override
	public String toString() {
		return String.format("EmpleadoPorHoras [horasPorMes=%s, sueldoHora=%s, dni=%s, nss=%s, id=%s, nombre=%s]",
				horasPorMes, sueldoHora, dni, nss, id, nombre);
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return sueldoHora.multiply(new BigDecimal(horasPorMes));
	}

}
