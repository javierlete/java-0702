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

}
