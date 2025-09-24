package com.ipartek.formacion.bibliotecas;

import java.math.BigInteger;

public class Mates {
	/*
	 * Si le damos un 5 hace lo siguiente 5! = 5 * 4 * 3 * 2 * 1
	 */
	public static BigInteger factorial(BigInteger numero) {
		BigInteger resultado = BigInteger.ONE;

		for (BigInteger n = numero; n.compareTo(BigInteger.ONE) >= 0; n = n.subtract(BigInteger.ONE)) {
			resultado = resultado.multiply(n);
		}

		return resultado;
	}

	/*
	 * 5! = 5 * 4! 4! = 4 * 3! 3! = 3 * 2! 2! = 2 * 1! 1! = 1
	 */
	public static BigInteger factorialRecursivo(BigInteger numero) {
		if (numero.compareTo(BigInteger.ONE) == 0) {
			return BigInteger.ONE;
		}

		return numero.multiply(factorialRecursivo(numero.subtract(BigInteger.ONE)));
	}
}
