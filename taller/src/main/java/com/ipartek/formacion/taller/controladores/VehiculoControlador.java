package com.ipartek.formacion.taller.controladores;

import java.util.Map;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.bibliotecas.accesodatos.AccesoDatosException;
import com.ipartek.formacion.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.taller.accesodatos.DaoUsuario;
import com.ipartek.formacion.taller.accesodatos.DaoVehiculo;
import com.ipartek.formacion.taller.modelos.Usuario;

public class VehiculoControlador {
	private static final DaoVehiculo DAO_VEHICULO = (DaoVehiculo) Fabrica.obtenerObjeto("dao.vehiculo");
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");

	@Ruta("/listado")
	public String listado(Map<String, String> entrada, Map<String, Object> salida) {
		var vehiculos = DAO_VEHICULO.obtenerTodos();

		salida.put("vehiculos", vehiculos);

		return "listado";
	}

	@Ruta("/detalle")
	public String detalle(Map<String, String> entrada, Map<String, Object> salida) {
		String matricula = entrada.get("matricula");

		var vehiculo = DAO_VEHICULO.buscarPorMatricula(matricula);

		salida.put("vehiculo", vehiculo.orElse(null));

		return "detalle";
	}

	@Ruta("/login")
	public String login() {
		return "login";
	}

	@Ruta("/logout")
	public String logout(Map<String, Object> salida) {
		salida.put("sesion", "invalidar");
		return "login";
	}

	@Ruta("/formulariopost")
	public String formularioPost(Map<String, String> entrada, Map<String, Object> salida) {
		if (entrada.get("registro") != null) {
			try {
				registrar(entrada, salida);
			} catch (AccesoDatosException e) {
				salida.put("email", entrada.get("email"));
				salida.put("error", "El usuario ya existe");
				return "login";
			}
		}

		return autenticar(entrada, salida);
	}

	private void registrar(Map<String, String> entrada, Map<String, Object> salida) {
		String email = entrada.get("email");
		String password = entrada.get("password");
		String nombre = entrada.get("nombre");

		var usuario = new Usuario(null, email, password, nombre);

		DAO_USUARIO.insertar(usuario);
	}

	private String autenticar(Map<String, String> entrada, Map<String, Object> salida) {
		String email = entrada.get("email");
		String password = entrada.get("password");

		Optional<Usuario> autenticado = autenticar(email, password);

		if (autenticado.isPresent()) {
			salida.put("sesion.usuario", autenticado.get());

			return "perfil";
		}

		salida.put("email", email);
		salida.put("error", "Usuario o contraseña incorrectos");

		return "login";
	}

	private Optional<Usuario> autenticar(String email, String password) {
		var usuario = DAO_USUARIO.obtenerPorEmail(email);

		if (usuario.isPresent() && !usuario.get().getPassword().equals(password)) {
			return Optional.empty();
		}

		return usuario;
	}

}
