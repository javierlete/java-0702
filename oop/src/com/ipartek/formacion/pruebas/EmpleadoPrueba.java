package com.ipartek.formacion.pruebas;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.ipartek.formacion.pojos.Empleado;
import com.ipartek.formacion.pojos.EmpleadoIndefinido;
import com.ipartek.formacion.pojos.EmpleadoPorHoras;

public class EmpleadoPrueba {
	public static void main(String[] args) {
		ArrayList<Empleado> empleados = new ArrayList<>();
		
		EmpleadoIndefinido javier = new EmpleadoIndefinido(1L, "Javier", "12345678A", "1234123412341234", 14, new BigDecimal("12345"));
		EmpleadoPorHoras pepe = new EmpleadoPorHoras(2L, "Pepe", "87654321A", "4321432143214321", 80, new BigDecimal("30"));

		empleados.add(javier);
		empleados.add(pepe);
		
		for(Empleado empleado: empleados) {
			System.out.println(empleado.getSueldoMensual());
		}
	}
}
