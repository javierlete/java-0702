package com.ipartek.formacion.pruebas;

import java.math.BigInteger;

import com.ipartek.formacion.bibliotecas.Mates;

public class MatesPrueba {
	public static void main(String[] args) {
		BigInteger valor = new BigInteger("100000");
		
		BigInteger resultado = Mates.factorial(valor);
		
		System.out.println(resultado);
		System.out.println(resultado.toString().length());
		
//		System.out.println(Mates.factorialRecursivo(valor));
	}
}
