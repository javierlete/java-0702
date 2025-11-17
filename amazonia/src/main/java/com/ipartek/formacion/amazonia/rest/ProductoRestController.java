package com.ipartek.formacion.amazonia.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.amazonia.modelos.Producto;
import com.ipartek.formacion.amazonia.servicios.AnonimoService;

@RestController
@RequestMapping("/api/v2/productos")
public class ProductoRestController {
	@Autowired
	private AnonimoService anonimoService;
	
	@GetMapping
	public Page<Producto> listadoProductos(Pageable pageable) {
		return anonimoService.listadoProductos(pageable);
	}
}
