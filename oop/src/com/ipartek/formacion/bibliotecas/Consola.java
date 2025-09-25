package com.ipartek.formacion.bibliotecas;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Consola {
	private static Scanner sc = new Scanner(System.in);
	
	public static void pl(Object o) {
		System.out.println(o);
	}
	
	public static void p(Object o) {
		System.out.print(o);
	}
	
	public static void pfl(String formato, Object... args) {
		System.out.printf(formato + "\n", args);
	}
	
	public static String leerString(String mensaje) {
		p(mensaje + ": ");
		
		return sc.nextLine();
	}
	
	public static int leerInt(String mensaje) {
		String texto = leerString(mensaje);
		
		return Integer.parseInt(texto);
	}

	public static long leerLong(String mensaje) {
		String texto = leerString(mensaje);
		
		return Long.parseLong(texto);
	}
	
	public static LocalDateTime leerLocalDateTime(String mensaje) {
		String texto = leerString(mensaje);
		
		return LocalDateTime.parse(texto);
	}
}
