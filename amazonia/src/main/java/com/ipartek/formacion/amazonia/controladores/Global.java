package com.ipartek.formacion.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ipartek.formacion.amazonia.servicios.AnonimoService;

@ControllerAdvice
public class Global {
	@Autowired
	private AnonimoService anonimoService;
	
	@ModelAttribute
	private void rellenarModelo(Model modelo) {
		modelo.addAttribute("categorias", anonimoService.listadoCategorias(Pageable.ofSize(1000)));
	}
}
