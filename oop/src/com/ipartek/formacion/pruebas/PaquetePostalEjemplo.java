package com.ipartek.formacion.pruebas;

import com.ipartek.formacion.pojos.PaquetePostal;

public class PaquetePostalEjemplo {
	public static void main(String[] args) {
		PaquetePostal paquetePostal = new PaquetePostal("Mi dirección", "12345", "Bilbao", "Bizkaia");
		
		System.out.println(paquetePostal);
		
		System.out.println(paquetePostal.direccion());
		System.out.println(paquetePostal.codigoPostal());
		System.out.println(paquetePostal.poblacion());
		System.out.println(paquetePostal.provincia());
		
		PaquetePostal paquetePostal2 = new PaquetePostal("Mi dirección", "12345", "Bilbao", "Bizkaia");
		
		System.out.println(paquetePostal.equals(paquetePostal2));
		System.out.println(paquetePostal.hashCode());
	}
}
