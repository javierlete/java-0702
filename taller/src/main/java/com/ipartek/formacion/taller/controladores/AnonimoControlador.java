package com.ipartek.formacion.taller.controladores;

import java.util.Map;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.bibliotecas.validaciones.ValidacionException;
import com.ipartek.formacion.taller.logicanegocio.AnonimoNegocio;
import com.ipartek.formacion.taller.modelos.Rol;
import com.ipartek.formacion.taller.modelos.Usuario;

public class AnonimoControlador {
	private static final AnonimoNegocio ANONIMO_NEGOCIO = (AnonimoNegocio) Fabrica.obtenerObjeto("negocio.anonimo");
	
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
			} catch (ValidacionException e) {
				salida.put("email", entrada.get("email"));
				salida.put("nombre", entrada.get("nombre"));
				salida.put("errores", e.getErrores());

				salida.put("error", "Hay errores de validación en los campos");

				return "login";
			} catch (Exception e) {
				salida.put("email", entrada.get("email"));
				salida.put("nombre", entrada.get("nombre"));
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

		var usuario = Usuario.builder().email(email).password(password).nombre(nombre).rol(Rol.builder().id(2L).build()).build();

		ANONIMO_NEGOCIO.registrar(usuario);
	}

	private String autenticar(Map<String, String> entrada, Map<String, Object> salida) {
		String email = entrada.get("email");
		String password = entrada.get("password");

		Optional<Usuario> autenticado = ANONIMO_NEGOCIO.autenticar(email, password);

		if (autenticado.isPresent()) {
			salida.put("sesion.usuario", autenticado.get());

			return "perfil";
		}

		salida.put("email", email);
		salida.put("error", "Usuario o contraseña incorrectos");

		return "login";
	}
}
