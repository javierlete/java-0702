package com.ipartek.formacion.pruebas;

import java.util.Arrays;

import com.ipartek.formacion.pojos.Color;

public class ColorPrueba {
	public static void main(String[] args) {
		Color color;

		color = Color.AZUL;

		color = Color.valueOf("ROJO");
		
		String aviso = switch (color) {
		case ROJO -> "Cuidado";
		case VERDE -> "Pase";
		case AZUL -> "Disfrute";
		default -> "DESCONOCIDO";
		};
		
		System.out.println(color);
		System.out.println(aviso);
		
		System.out.println(Arrays.toString(Color.values()));
		
		System.out.println(color.name());
		System.out.println(color.ordinal());
	}
}
