package com.ipartek.formacion.springtaller.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.springtaller.servicios.TrabajadorService;

@Controller
@RequestMapping("trabajador")
public class TrabajadorController {
	@Autowired
	private TrabajadorService trabajadorService;
	
	@GetMapping("agregar-vehiculo")
	public String agregarVehiculo(Long id) {
		trabajadorService.meterVehiculoEnCola(id);
		
		return "redirect:/trabajador/cola";
	}
	
	@GetMapping("cola")
	public String cola(Model modelo) {
modelo.addAttribute("vehiculos", trabajadorService.listarVehiculosEnCola());
		
		return "trabajador/cola";
	}
}
