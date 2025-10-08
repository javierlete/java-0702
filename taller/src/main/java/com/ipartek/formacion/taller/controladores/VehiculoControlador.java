package com.ipartek.formacion.taller.controladores;

import java.util.Map;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.taller.accesodatos.DaoVehiculo;

public class VehiculoControlador {
	private static final DaoVehiculo DAO = (DaoVehiculo) Fabrica.obtenerObjeto("dao.vehiculo");

	@Ruta("/listado")
	public String listado(Map<String, String> entrada, Map<String, Object> salida) {
		var vehiculos = DAO.obtenerTodos();

		salida.put("vehiculos", vehiculos);

		return "listado";
	}

	@Ruta("/detalle")
	public String detalle(Map<String, String> entrada, Map<String, Object> salida) {
		String matricula = entrada.get("matricula");

		var vehiculo = DAO.buscarPorMatricula(matricula);

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
	
	@Ruta("/autenticar")
	public String autenticar(Map<String, String> entrada, Map<String, Object> salida) {
		String email = entrada.get("email");
		String password = entrada.get("password");
		
		if("javier@email.net".equals(email) && "javier".equals(password)) {
			salida.put("sesion.email", email);
			
			return "perfil";
		}
		
		salida.put("email", email);
		salida.put("error", "Usuario o contraseña incorrectos");
		
		return "login";
	}

}
