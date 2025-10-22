package com.ipartek.formacion.taller.modelos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
	private Long id;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String password;
	
	@NotBlank
	@Size(min = 2, max = 15)
	private String nombre;
}
