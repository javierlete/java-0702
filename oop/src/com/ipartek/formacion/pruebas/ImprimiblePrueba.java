package com.ipartek.formacion.pruebas;

import java.util.ArrayList;

import com.ipartek.formacion.pojos.Imprimible;
import com.ipartek.formacion.pojos.Local;
import com.ipartek.formacion.pojos.Persona;

public class ImprimiblePrueba {
	public static void main(String[] args) {
		ArrayList<Imprimible> imprimibles = new ArrayList<>();
		
		Persona javier = new Persona(1L, "Javier");
		
		imprimibles.add(javier);
		imprimibles.add(new Local("Bilbao", javier));
		
		for(Imprimible imprimible: imprimibles) {
			System.out.println(imprimible.imprimir());
		}
	}
}
