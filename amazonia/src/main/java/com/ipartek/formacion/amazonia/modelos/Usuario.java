package com.ipartek.formacion.amazonia.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String nombre;

	@NotBlank
	@Size(max = 100)
	private String apellidos;

	@Size(min = 9, max = 9)
	@Pattern(regexp = "\\d{9}")
	private String telefono;

	@NotBlank
	@Size(max = 255)
	@Column(unique = true)
	@Email
	private String email;

	@NotBlank
	@Size(max = 60)
	private String password;
	
	@NotNull
	@ManyToOne
	private Rol rol;
	
	@Embedded
	private Direccion direccion;
}
