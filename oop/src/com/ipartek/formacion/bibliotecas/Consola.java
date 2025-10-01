package com.ipartek.formacion.bibliotecas;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.Function;

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
		return leer(mensaje, texto -> Integer.parseInt(texto));
	}

	public static long leerLong(String mensaje) {
		return leer(mensaje, texto -> Long.parseLong(texto));
	}

	public static LocalDateTime leerLocalDateTime(String mensaje) {
		return leer(mensaje, texto -> LocalDateTime.parse(texto));
	}

	private static <T> T leer(String mensaje, Function<String, T> convertir) {
		do {
			String texto = leerString(mensaje);
			try {
				return convertir.apply(texto);
			} catch (Exception e) {
				pl("El dato que has introducido no es válido");
			} 
		} while (true);
	}
}
