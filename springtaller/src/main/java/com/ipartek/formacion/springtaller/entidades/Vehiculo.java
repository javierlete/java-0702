package com.ipartek.formacion.springtaller.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

@Entity
@Table(name = "vehiculos")
public class Vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Pattern(regexp = "^\\d{4}[A-Z]{3}$")
	@Size(min = 7, max = 7)
	@Column(unique = true, columnDefinition = "CHAR(7)")
	private String matricula;
	
	@NotBlank
	@Size(min = 17, max = 17)
	@Column(unique = true, columnDefinition = "CHAR(17)")
	private String bastidor;
	
	@Size(max = 50)
	private String modelo;
	
	@Size(max = 20)
	private String marca;
	
	@ManyToOne
	private Usuario propietario;

	@Builder.Default
	@Column(name = "estado_reparacion")
	private EstadoReparacion estadoReparacion = EstadoReparacion.RECIBIDO;

	public enum EstadoReparacion {
		RECIBIDO, PRESUPUESTO, NO_REPARABLE, PENDIENTE_ACEPTACION, EN_CURSO, REPARADO, FACTURADO, ENTREGADO
	}
}
