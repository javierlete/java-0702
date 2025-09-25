package com.ipartek.formacion.citas.pruebas;

import java.time.LocalDateTime;

import com.ipartek.formacion.citas.accesodatos.DaoCita;
import com.ipartek.formacion.citas.accesodatos.DaoCitaArrayList;
import com.ipartek.formacion.citas.entidades.Cita;

public class DaoCitaPrueba {
	public static void main(String[] args) {
		DaoCita dao = new DaoCitaArrayList();

		dao.insertar(
				new Cita("Cita pasada", LocalDateTime.of(2025, 9, 24, 15, 0), LocalDateTime.of(2025, 9, 24, 20, 0)));
		dao.insertar(new Cita("Esta cita también es pasada", LocalDateTime.of(2025, 9, 25, 15, 0),
				LocalDateTime.of(2025, 9, 25, 20, 0)));
		dao.insertar(
				new Cita("Cita futura", LocalDateTime.of(2025, 9, 26, 15, 0), LocalDateTime.of(2025, 9, 26, 20, 0)));
		
		dao.obtenerTodos().forEach(System.out::println);

		System.out.println();
		
		dao.modificar(new Cita(2L, "Esta cita también es UNA pasada", LocalDateTime.of(2025, 9, 25, 15, 0),
				LocalDateTime.of(2025, 9, 25, 20, 0)));

		dao.obtenerPorId(2L).ifPresentOrElse(System.out::println, () -> System.out.println("No existe"));
		
		System.out.println();
		
		dao.buscarPorTexto("pasada").forEach(System.out::println);
		
		System.out.println();
		
		dao.borrar(1L);
		
		dao.obtenerTodos().forEach(System.out::println);
	}
}
