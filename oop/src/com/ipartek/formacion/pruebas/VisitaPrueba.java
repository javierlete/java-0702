package com.ipartek.formacion.pruebas;

import com.ipartek.formacion.pojos.Persona;
import com.ipartek.formacion.pojos.Visita;

public class VisitaPrueba {
	public static void main(String[] args) {
		Visita visita = new Visita(new Persona("Pepe"));
		
		System.out.println("VISITA: " + visita);

		Persona persona = visita;

		System.out.println("PERSONA: " + persona);
//		System.out.println(persona.getEntrada());

		if (persona instanceof Visita v) {
//			Visita v = (Visita) persona;

			System.out.println("VISITA: " + v);
		} else {
			System.out.println("NO ES UNA VISITA");
		}

		Persona p = new Persona();

		if (p instanceof Visita v2) {
//			Visita v2 = (Visita) p;

			System.out.println("VISITA NUEVA: " + v2);
		} else {
			System.out.println("NO ES UNA VISITA");
		}
	}
}
