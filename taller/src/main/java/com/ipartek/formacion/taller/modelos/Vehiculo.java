package com.ipartek.formacion.taller.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehiculo {
	private Long id;
	private String matricula;
	private String bastidor;
	private String modelo;
	private String marca;

	@Builder.Default
	private EstadoReparacion estadoReparacion = EstadoReparacion.RECIBIDO;

	public enum EstadoReparacion {
		RECIBIDO, PRESUPUESTO, NO_REPARABLE, PENDIENTE_ACEPTACION, EN_CURSO, REPARADO, FACTURADO, ENTREGADO
	}
}
