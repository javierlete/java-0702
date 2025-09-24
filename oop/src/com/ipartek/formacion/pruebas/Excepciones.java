package com.ipartek.formacion.pruebas;

public class Excepciones {
	private static String prueba = null;
	
	public static void main(String[] args) {
//		throw new RuntimeException("Error creado por mí");
		
		int div, a = 2, b = 1;
		
		int[] arr = new int[2];
		
		
		try {
			arr[1] = 5;
			
			div = a / b;
			
			prueba.toUpperCase();
			
			System.out.println(div);
		} catch (ArithmeticException e) {
			System.out.println("Ha habido un error aritmético en la división");
			System.out.println(e.getMessage());
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Error en el Array");
		} finally {
			System.out.println("Me ejecuto POR MIS NARICES");
		}
		
		System.out.println("FIN DEL PROGRAMA");
	}
}
