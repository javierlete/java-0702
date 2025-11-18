package com.ipartek.formacion.amazonia.pruebas;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.amazonia.modelos.Producto;
import com.ipartek.formacion.amazonia.repositorios.ProductoRepository;
import com.ipartek.formacion.amazonia.servicios.AnonimoService;

@Component
public class Pruebas implements CommandLineRunner {
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private AnonimoService anonimoServicio;

	@Override
	public void run(String... args) throws Exception {
		productoRepository.save(Producto.builder().nombre("Portátil").precio(new BigDecimal(1234)).imagen("portatil.jpg").build());
		productoRepository.save(Producto.builder().nombre("Ratón").precio(new BigDecimal(12)).imagen("raton.jpg").build());
		productoRepository.save(Producto.builder().nombre("Teclado").precio(new BigDecimal(123)).imagen("teclado.jpg").build());

		System.out.println("REPOSITORIO FINDALL");
		productoRepository.findAll().forEach(System.out::println);

		var pageable = Pageable.ofSize(2);

		Page<Producto> pagina;

		do {
			pagina = anonimoServicio.listadoProductos(pageable);

			if (pagina.hasContent()) {
				System.out.println("PAGINA");
				pagina.forEach(System.out::println);
				
				pageable = pagina.nextOrLastPageable();
			}
		} while (pagina.hasNext());
	}

}
