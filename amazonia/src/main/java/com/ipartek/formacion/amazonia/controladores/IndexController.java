package com.ipartek.formacion.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipartek.formacion.amazonia.servicios.AnonimoService;

@Controller
public class IndexController {
	private static final int TAMANO_PAGINA = 2;

	@Autowired
	private AnonimoService anonimoService;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("pagina", anonimoService.listadoProductos(Pageable.ofSize(TAMANO_PAGINA)));
		return "index";
	}
	
	@GetMapping("detalle")
	public String detalle(Long id, Model modelo) {
		modelo.addAttribute("producto", anonimoService.detalleProducto(id));
		return "detalle";
	}
}
