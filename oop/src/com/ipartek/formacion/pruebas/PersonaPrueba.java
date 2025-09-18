package com.ipartek.formacion.pruebas;

import com.ipartek.formacion.pojos.Persona;

public class PersonaPrueba {
	public static void main(String[] args) {
		var persona1 = new Persona();
		
		System.out.println("#" + persona1.getId() + ", " + persona1.getNombre() + "#");
		
		persona1.setId(1L);
		persona1.setNombre("    Javier      ");
		
		System.out.println(persona1.toString());
		
		var persona2 = new Persona(2L, "Pepe");
		
		System.out.println(persona2);

		var persona3 = new Persona("Juan");
		
		System.out.println(persona3);
		
		var persona4 = new Persona(persona3);
		
		persona4.setId(5L);
		
		System.out.println(persona3);
		System.out.println(persona4);
	}
}
