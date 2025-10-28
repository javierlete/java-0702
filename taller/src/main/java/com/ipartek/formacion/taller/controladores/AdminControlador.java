package com.ipartek.formacion.taller.controladores;

import java.util.Map;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.taller.logicanegocio.UsuarioNegocio;

public class AdminControlador {
	private static final UsuarioNegocio USUARIO_NEGOCIO = (UsuarioNegocio) Fabrica.obtenerObjeto("negocio.usuario");

	@Ruta("/admin")
	public String listado(Map<String, String> entrada, Map<String, Object> salida) {
		var vehiculos = USUARIO_NEGOCIO.listadoVehiculos();

		salida.put("vehiculos", vehiculos);

		return "admin/listado";
	}
}
