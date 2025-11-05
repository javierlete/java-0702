package com.ipartek.formacion.springtaller.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.springtaller.entidades.Vehiculo;
import com.ipartek.formacion.springtaller.entidades.Vehiculo.EstadoReparacion;
import com.ipartek.formacion.springtaller.servicios.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public String tabla(Model modelo) {
		modelo.addAttribute("vehiculos", usuarioService.listadoVehiculos());

		return "admin/tabla";
	}

	@GetMapping("editar")
	public String editar(Long id, Model modelo) {
		Vehiculo vehiculo = null;

		if (id != null) {
			vehiculo = usuarioService.detalleVehiculo(id).get();
		} else {
			vehiculo = Vehiculo.builder().build();
		}

		modelo.addAttribute("vehiculo", vehiculo);
		
		desplegables(modelo);

		return "admin/formulario";
	}

	private void desplegables(Model modelo) {
		modelo.addAttribute("usuarios", usuarioService.listadoUsuarios());
		modelo.addAttribute("estados", EstadoReparacion.values());
	}

	@PostMapping("editar")
	public String editarPost(@Valid Vehiculo vehiculo, BindingResult bindingResult, Model modelo) {
		if (bindingResult.hasErrors()) {
			desplegables(modelo);
			
			return "admin/formulario";
		}

		if (vehiculo.getId() != null) {
			usuarioService.modificacionVehiculo(vehiculo);
		} else {
			usuarioService.altaVehiculo(vehiculo);
		}

		return "redirect:/admin";
	}

	@GetMapping("borrar")
	public String borrar(Long id) {
		usuarioService.bajaVehiculo(id);

		return "redirect:/admin";
	}

}
