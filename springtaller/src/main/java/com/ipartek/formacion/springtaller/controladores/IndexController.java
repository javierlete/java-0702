package com.ipartek.formacion.springtaller.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipartek.formacion.springtaller.servicios.UsuarioService;

@Controller
public class IndexController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("vehiculos", usuarioService.listadoVehiculos());
		
		return "vehiculos";
	}
	
	@GetMapping("vehiculo")
	public String vehiculo(Long id, Model modelo) {
		modelo.addAttribute("vehiculo", usuarioService.detalleVehiculo(id).orElse(null));
		
		return "vehiculo";
	}
}
