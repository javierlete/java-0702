package com.ipartek.formacion.pruebas;

import com.ipartek.formacion.pojos.Impresora;
import com.ipartek.formacion.pojos.Imprimible;
import com.ipartek.formacion.pojos.Persona;

public class ImpresoraPrueba {
	private static final int NUMERO_GUIONES = 50;
	
	public static void main(String[] args) {
		
		Impresora impresora = new Impresora();
		
		impresora.agregarACola(() -> "PRINCIPIO");
		
		impresora.agregarACola(new Persona(1L, "Javier"));
		impresora.agregarACola(new Separador(NUMERO_GUIONES));
		
		impresora.agregarACola(new Persona(1L, "Javier"));
		impresora.agregarACola(new SeparadorClaseInterna());
		
		impresora.agregarACola(new Persona(1L, "Javier"));
		impresora.agregarACola(new Imprimible() {
			@Override
			public String imprimir() {
				return "+".repeat(NUMERO_GUIONES);
			}
		});
		
		impresora.agregarACola(new Persona(1L, "Javier"));
		impresora.agregarACola(() -> "=".repeat(NUMERO_GUIONES));
		
		impresora.agregarACola(() -> "FIN");
		
		impresora.imprimir();
	}
	
	static class SeparadorClaseInterna implements Imprimible {
		@Override
		public String imprimir() {
			return "*".repeat(NUMERO_GUIONES);
		}
		
	}
}

class Separador implements Imprimible {
	private int numeroGuiones;
	
	public Separador(int numeroGuiones) {
		this.numeroGuiones = numeroGuiones;
	}
	
	@Override
	public String imprimir() {
		return "-".repeat(numeroGuiones);
	}
	
}