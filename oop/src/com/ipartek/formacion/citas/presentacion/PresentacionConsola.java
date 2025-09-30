package com.ipartek.formacion.citas.presentacion;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import java.time.LocalDateTime;
import java.util.Collection;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;
import com.ipartek.formacion.citas.entidades.Cita;

public class PresentacionConsola {
	private static final boolean CON_ID = true;
	private static final boolean SIN_ID = false;

	private static final String FORMATO_LINEA = "| %4s | %-50s | %20s | %20s |";

	private static final int SALIR = 0;

	private static final DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");

	public static void main(String[] args) {
		// PARA PRUEBAS
		dao.insertar(
				new Cita("Cita pasada", LocalDateTime.of(2025, 9, 24, 15, 0), LocalDateTime.of(2025, 9, 24, 20, 0)));
		dao.insertar(new Cita("Esta cita también es pasada", LocalDateTime.of(2025, 9, 25, 15, 0),
				LocalDateTime.of(2025, 9, 25, 20, 0)));
		dao.insertar(
				new Cita("Cita futura", LocalDateTime.of(2025, 9, 26, 15, 0), LocalDateTime.of(2025, 9, 26, 20, 0)));
		// FIN PARA PRUEBAS

		int opcion;

		do {
			mostrarMenu();
			opcion = elegirOpcion();
			procesarOpcion(opcion);
		} while (opcion != SALIR);
	}

	private static void mostrarMenu() {
		pl("""
				1. Listado
				2. Buscar por id
				3. Buscar por texto
				4. Añadir
				5. Modificar
				6. Eliminar

				0. Salir
				""");
	}

	private static int elegirOpcion() {
		return leerInt("Elige una opción");
	}

	private static void procesarOpcion(int opcion) {
		switch (opcion) {
		case 1 -> listado();
		case 2 -> buscarPorId();
		case 3 -> buscarPorTexto();
		case 4 -> anyadir();
		case 5 -> modificar();
		case 6 -> borrar();
		case 0 -> salir();
		default -> pl("Opción no reconocida");
		}
	}

	private static void listado() {
		Collection<Cita> citas = dao.obtenerTodos();
		mostrarListado(citas);
	}

	private static void buscarPorId() {
		Long id = leerLong("Id");

		dao.obtenerPorId(id).ifPresentOrElse(PresentacionConsola::mostrarCitaFicha, () -> pl("No se ha encontrado"));
	}

	private static void buscarPorTexto() {
		String texto = leerString("Dime el texto a buscar");
		Collection<Cita> citas = dao.buscarPorTexto(texto);
		mostrarListado(citas);
	}

	private static void anyadir() {
		var cita = pedirCita(SIN_ID);

		dao.insertar(cita);
	}

	private static void modificar() {
		var cita = pedirCita(CON_ID);

		dao.modificar(cita);
	}

	private static void borrar() {
		var id = leerLong("Id");

		dao.borrar(id);
	}

	private static void salir() {
		pl("Gracias por usar este programa");
	}

	private static Cita pedirCita(boolean conId) {
		Long id = null;

		if (conId) {
			id = leerLong("Id");
		}

		var texto = leerString("Texto");
		var inicio = leerLocalDateTime("Inicio");
		var fin = leerLocalDateTime("Fin");

		return new Cita(id, texto, inicio, fin);
	}

	private static void mostrarListado(Collection<Cita> citas) {
		if (citas.isEmpty()) {
			pl("No se ha encontrado ninguna cita");
		} else {
			mostrarCabeceraLinea();
			citas.forEach(PresentacionConsola::mostrarCitaLinea);
		}
	}

	private static void mostrarCabeceraLinea() {
		pfl(FORMATO_LINEA, "Id", "Texto", "Inicio", "Fin");
	}

	private static void mostrarCitaLinea(Cita cita) {
		pfl(FORMATO_LINEA, cita.getId(), cita.getTexto(), cita.getInicio(), cita.getFin());
	}

	private static void mostrarCitaFicha(Cita cita) {
		pfl("""
				Id:     %s
				Texto:  %s
				Inicio: %s
				Fin:    %s
				""", cita.getId(), cita.getTexto(), cita.getInicio(), cita.getFin());
	}
}
