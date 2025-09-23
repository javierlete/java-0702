package com.ipartek.formacion.pruebas;

import java.util.function.BinaryOperator;

public class CalculadoraPrueba {
	public static void main(String[] args) {
		int a, b;
		BinaryOperator<Integer> operacion;
		
		a = 2;
		operacion = (x, y) -> x + y;
		b = 5;
		
		System.out.println(operacion.apply(a, b));

		a = 2;
		operacion = (x, y) -> x * y;
		b = 5;
		
		System.out.println(operacion.apply(a, b));
	}
}
