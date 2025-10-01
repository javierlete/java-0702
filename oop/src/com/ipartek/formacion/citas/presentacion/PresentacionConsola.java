package com.ipartek.formacion.citas.presentacion;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;
import com.ipartek.formacion.citas.accesodatos.DaoUsuario;
import com.ipartek.formacion.citas.entidades.Cita;
import com.ipartek.formacion.citas.entidades.Usuario;

public class PresentacionConsola {
	private static final boolean CON_ID = true;
	private static final boolean SIN_ID = false;

	private static final String FORMATO_LINEA = "| %4s | %-10s | %-30s | %20s | %20s |";

	private static final int SALIR = 0;

	private static final DaoCita DAO_CITA = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");

	public static void main(String[] args) {
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
		Collection<Cita> citas = DAO_CITA.obtenerTodos();
		mostrarListado(citas);
	}

	private static void buscarPorId() {
		Long id = leerLong("Id");

		DAO_CITA.obtenerPorId(id).ifPresentOrElse(PresentacionConsola::mostrarCitaFicha,
				() -> pl("No se ha encontrado"));
	}

	private static void buscarPorTexto() {
		String texto = leerString("Dime el texto a buscar");
		Collection<Cita> citas = DAO_CITA.buscarPorTexto(texto);
		mostrarListado(citas);
	}

	private static void anyadir() {
		var cita = pedirCita(SIN_ID);

		DAO_CITA.insertar(cita);
	}

	private static void modificar() {
		var cita = pedirCita(CON_ID);

		DAO_CITA.modificar(cita);
	}

	private static void borrar() {
		Long id = pedirIdExistente();

		DAO_CITA.borrar(id);
	}

	private static void salir() {
		pl("Gracias por usar este programa");
	}

	private static Cita pedirCita(boolean conId) {
		Long id = null;

		if (conId) {
			id = pedirIdExistente();
		}

		listadoUsuarios();

		Long idUsuario = pedirIdUsuario();
		String texto = pedirTexto();
		
		LocalDateTime inicio;
		LocalDateTime fin;
		
		do {
			inicio = leerLocalDateTime("Inicio");
			fin = leerLocalDateTime("Fin");
			
			if(inicio.isAfter(fin)) {
				pl("El inicio no puede ser posterior al fin");
			}
		} while (inicio.isAfter(fin));
		
		return new Cita(id, idUsuario, texto, inicio, fin);
	}

	private static Long pedirIdExistente() {
		Long id;
		Optional<Cita> cita;
		
		do {
			id = leerLong("Id");
			cita = DAO_CITA.obtenerPorId(id);
			
			if(cita.isEmpty()) {
				pl("La cita no existe");
			}
		} while (cita.isEmpty());
		
		return id;
	}

	private static Long pedirIdUsuario() {
		Long idUsuario;
		Optional<Usuario> usuario;
		do {
			idUsuario = leerLong("Id de usuario");
			usuario = DAO_USUARIO.obtenerPorId(idUsuario);
			
			if (usuario.isEmpty()) {
				pl("Ese usuario no existe");
			}
		} while (usuario.isEmpty());
		return idUsuario;
	}

	private static String pedirTexto() {
		String texto;
		do {
			texto = leerString("Texto");
			
			if(texto.isBlank()) {
				pl("Debes especificar algún texto para la cita");
			}
		} while (texto.isBlank());
		return texto;
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
		pfl(FORMATO_LINEA, "Id", "Usuario", "Texto", "Inicio", "Fin");
	}

	private static void mostrarCitaLinea(Cita cita) {
		pfl(FORMATO_LINEA, cita.getId(), cita.getUsuario() != null ? cita.getUsuario().getNombre() : "",
				cita.getTexto(), cita.getInicio(), cita.getFin());
	}

	private static void mostrarCitaFicha(Cita cita) {
		pfl("""
				Id:      %s
				Usuario: %s
				Texto:   %s
				Inicio:  %s
				Fin:     %s
				""", cita.getId(), cita.getUsuario() != null ? cita.getUsuario().getNombre() : "", cita.getTexto(),
				cita.getInicio(), cita.getFin());
	}

	private static void listadoUsuarios() {
		DAO_USUARIO.obtenerTodos().forEach(u -> pfl("%4s %s", u.getId(), u.getNombre()));
	}
}
