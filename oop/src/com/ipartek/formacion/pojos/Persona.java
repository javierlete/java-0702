package com.ipartek.formacion.pojos;

public class Persona {
	// CONSTANTES
	private static final Long ID_POR_DEFECTO = null;
	private static final String NOMBRE_POR_DEFECTO = "ANÓNIMO";
	
	// VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;

	// CONSTRUCTORES
	public Persona(Long id, String nombre) {
		setId(id);
		setNombre(nombre);
	}
	
	public Persona(String nombre) {
		this(ID_POR_DEFECTO, nombre);
	}

	public Persona() {
		this(ID_POR_DEFECTO, NOMBRE_POR_DEFECTO);
	}
	
	// Constructor de copia
	public Persona(Persona persona) {
		this(persona.getId(), persona.getNombre());
	}

	// DESTRUCTOR
	protected void finalize() {
		System.out.println("DESTRUCTOR DE " + this);
	}
	
	// GETTERS Y SETTERS (MÉTODOS DE ACCESO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre == null || nombre.isBlank()) {
			throw new RuntimeException("El nombre es obligatorio");
		}
		
		this.nombre = nombre.trim();
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + "]";
	}
}
