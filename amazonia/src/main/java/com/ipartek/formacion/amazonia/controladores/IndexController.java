package com.ipartek.formacion.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.formacion.amazonia.modelos.Categoria;
import com.ipartek.formacion.amazonia.modelos.Direccion;
import com.ipartek.formacion.amazonia.modelos.Producto;
import com.ipartek.formacion.amazonia.modelos.Usuario;
import com.ipartek.formacion.amazonia.servicios.AnonimoService;

import jakarta.validation.Valid;

@Controller
public class IndexController {
	private static final int TAMANO_PAGINA = 2;

	@Autowired
	private AnonimoService anonimoService;

	@GetMapping
	public String index(Model modelo, @RequestParam(name = "id", required = false) Long idCategoria,
			@RequestParam(name = "pagina", required = false, defaultValue = "0") int numeroPagina) {
		Page<Producto> pagina;

		Categoria categoria;

		Pageable pageable = Pageable.ofSize(TAMANO_PAGINA).withPage(numeroPagina);
		
		if (idCategoria == null) {
			pagina = anonimoService.listadoProductos(pageable);
			categoria = Categoria.builder().nombre("TODOS").descripcion("Todos los productos de Amazonia").build();
		} else {
			pagina = anonimoService.listadoProductos(pageable, idCategoria);
			categoria = anonimoService.detalleCategoria(idCategoria);
		}

		modelo.addAttribute("categoria", categoria);
		modelo.addAttribute("pagina", pagina);

		return "index";
	}

	@GetMapping("detalle")
	public String detalle(Long id, Model modelo) {
		modelo.addAttribute("producto", anonimoService.detalleProducto(id));

		return "detalle";
	}
	
	@GetMapping("registro")
	public String registro(Usuario usuario, Direccion direccion) {
		return "registro";
	}
	
	@PostMapping("registro")
	public String registroPost(@Valid Usuario usuario, BindingResult bindingResult, @Valid Direccion direccion, BindingResult bindingResultDireccion) {
		System.out.println(usuario);
		System.out.println(direccion);
		
		if(bindingResult.hasErrors() || bindingResultDireccion.hasErrors()) {
			System.out.println(bindingResult);
			System.out.println(bindingResultDireccion);
			
			return "registro";
		}
		
		usuario.setDireccion(direccion);
		
		anonimoService.registrarse(usuario);
		
		return "redirect:/";
	}
}
