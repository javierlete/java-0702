package com.ipartek.formacion.pojos;

import java.util.ArrayList;
import java.util.List;

public class Impresora {
	private List<Imprimible> cola = new ArrayList<>();

	public void agregarACola(Imprimible imprimible) {
		cola.add(imprimible);
	}

	public void imprimir() {
		cola.stream().forEach(i -> System.out.println(i.imprimir()));
		
//		for (Imprimible imprimible : cola) {
//			System.out.println(imprimible.imprimir());
//		}
		
		vaciarCola();
	}
	
	public void vaciarCola() {
		cola.clear();
	}
}
