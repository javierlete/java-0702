package com.ipartek.formacion.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.formacion.amazonia.modelos.Categoria;
import com.ipartek.formacion.amazonia.modelos.Producto;
import com.ipartek.formacion.amazonia.modelos.Usuario;
import com.ipartek.formacion.amazonia.servicios.AnonimoService;

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
	public String registro(Usuario usuario) {
//		var direccion = Direccion.builder().calle("Su calle").localidad("Su localidad").codigoPostal("12345").provincia("Su provincia").build();
//		var usuario = Usuario.builder().nombre("Javier").apellidos("Lete").email("javier@email.net").password("javier").telefono("123123123").direccion(direccion).build();

//		modelo.addAttribute("usuario", usuario);
		
		return "registro";
	}
	
	@PostMapping("registro")
	public String registroPost(Usuario usuario) {
		System.out.println(usuario);
		
		anonimoService.registrarse(usuario);
		
		return "redirect:/";
	}
}
