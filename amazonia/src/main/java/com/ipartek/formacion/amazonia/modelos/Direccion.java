package com.ipartek.formacion.amazonia.modelos;

import jakarta.persistence.Embeddable;
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

@Embeddable
public class Direccion {
	@NotBlank
	@Size(max = 255)
	private String calle;
	
	@NotBlank
	@Size(max = 100)
	private String localidad;
	
	@NotBlank
	@Size(min = 5, max = 5, message = "deben ser 5 caracteres")
	@Pattern(regexp = "\\d{5}", message = "todos los caracteres deben ser dígitos")
	private String codigoPostal;
	
	@NotBlank
	@Size(max = 30)
	private String provincia;
}
