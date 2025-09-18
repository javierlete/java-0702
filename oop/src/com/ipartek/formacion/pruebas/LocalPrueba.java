package com.ipartek.formacion.pruebas;

import com.ipartek.formacion.pojos.Local;
import com.ipartek.formacion.pojos.Persona;

public class LocalPrueba {
	public static void main(String[] args) {
		Local local = new Local("Bilbao", new Persona("Javier"));

		System.out.println(local);

		System.out.println(local.getEncargado().getNombre());

		Persona pepe = new Persona("Pepe");
		local.entrar(pepe);
		local.entrar(pepe); // No entra duplicado porque las visitas son un Set
		local.entrar(new Persona("Juan"));

//		local.getVisitas().add(null); // No se puede hacer add porque la colección es inmodificable

		for (Persona visita : local.getVisitas()) {
			System.out.println("foreach normal: " + visita);
		}

		local.getVisitas().stream().forEach(System.out::println);

		local.salir(pepe);

		local.getVisitas().stream().forEach(v -> System.out.println("Después de Pepe: " + v));
	}
}
