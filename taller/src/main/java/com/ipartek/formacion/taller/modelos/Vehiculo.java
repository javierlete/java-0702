package com.ipartek.formacion.taller.modelos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank
	@Pattern(regexp = "^\\d{4}[A-Z]{3}$")
	@Size(min = 7, max = 7)
	private String matricula;
	
	@NotBlank
	@Size(min = 17, max = 17)
	private String bastidor;
	
	@Size(max = 50)
	private String modelo;
	
	@Size(max = 20)
	private String marca;

	@Builder.Default
	private EstadoReparacion estadoReparacion = EstadoReparacion.RECIBIDO;

	public enum EstadoReparacion {
		RECIBIDO, PRESUPUESTO, NO_REPARABLE, PENDIENTE_ACEPTACION, EN_CURSO, REPARADO, FACTURADO, ENTREGADO
	}
}
