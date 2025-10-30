package com.ipartek.formacion.taller.controladores;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.bibliotecas.etiquetas.Opcion;
import com.ipartek.formacion.bibliotecas.validaciones.ValidacionException;
import com.ipartek.formacion.taller.logicanegocio.UsuarioNegocio;
import com.ipartek.formacion.taller.modelos.Usuario;
import com.ipartek.formacion.taller.modelos.Vehiculo;
import com.ipartek.formacion.taller.modelos.Vehiculo.EstadoReparacion;

public class AdminControlador {
	private static final UsuarioNegocio USUARIO_NEGOCIO = (UsuarioNegocio) Fabrica.obtenerObjeto("negocio.usuario");

	@Ruta("/admin")
	public String listado(Map<String, String> entrada, Map<String, Object> salida) {
		var vehiculos = USUARIO_NEGOCIO.listadoVehiculos();

		salida.put("vehiculos", vehiculos);

		return "admin/listado";
	}

	@Ruta("/admin/formulario")
	public String formulario(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");

		Long id = sId == null ? null : Long.parseLong(sId);

		if (id != null) {
			Optional<Vehiculo> oUsuario = USUARIO_NEGOCIO.detalleVehiculo(id);
			salida.put("vehiculo", oUsuario.orElse(Vehiculo.builder().build()));
		}

		datosFormulario(salida);

		return "admin/formulario";
	}

	private void datosFormulario(Map<String, Object> salida) {
		var usuarios = USUARIO_NEGOCIO.listadoUsuarios();
		
		salida.put("propietarios", usuarios);
		salida.put("estados",
				List.of(EstadoReparacion.values()).stream().map(e -> new Opcion(e.toString(), e.toString())).toList());
	}

	@Ruta("/admin/formulariopost")
	public String formularioPost(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");
		String matricula = entrada.get("matricula");
		String marca = entrada.get("marca");
		String modelo = entrada.get("modelo");
		String bastidor = entrada.get("bastidor");
		String sEstado = entrada.get("estado");
		String sPropietario = entrada.get("propietario");

		Long id = !sId.isBlank() ? Long.parseLong(sId) : null;
		EstadoReparacion estado = EstadoReparacion.valueOf(sEstado);
		Usuario propietario = Usuario.builder().id(Long.parseLong(sPropietario)).build();

		Vehiculo vehiculo = Vehiculo.builder().id(id).matricula(matricula).marca(marca).modelo(modelo)
				.bastidor(bastidor).estadoReparacion(estado).propietario(propietario).build();

		try {
			if (vehiculo.getId() == null) {
				USUARIO_NEGOCIO.altaVehiculo(vehiculo);
			} else {
				USUARIO_NEGOCIO.modificacionVehiculo(vehiculo);
			}
		} catch (ValidacionException e) {
			salida.put("vehiculo", vehiculo);
			datosFormulario(salida);
			salida.put("errores", e.getErrores());

			return "admin/formulario";
		}

		return "redirect:/admin";
	}

	@Ruta("/admin/borrar")
	public String borrar(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");

		Long id = Long.parseLong(sId);

		USUARIO_NEGOCIO.bajaVehiculo(id);

		return "redirect:/admin";
	}

}
