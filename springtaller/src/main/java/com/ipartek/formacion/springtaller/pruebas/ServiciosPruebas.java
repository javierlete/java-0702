package com.ipartek.formacion.springtaller.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.springtaller.entidades.Usuario;
import com.ipartek.formacion.springtaller.servicios.AnonimoService;
import com.ipartek.formacion.springtaller.servicios.UsuarioService;

import jakarta.validation.ConstraintViolationException;

@Order(2)
@Component
public class ServiciosPruebas implements CommandLineRunner {

	@Autowired
	private AnonimoService anonimoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void run(String... args) throws Exception {
		anonimoService.autenticar("pepe@email.net", "").ifPresentOrElse(System.out::println,
				() -> System.out.println("No autenticado"));
		anonimoService.autenticar("javier@email.net", "javier").ifPresentOrElse(System.out::println,
				() -> System.out.println("No autenticado"));
		
		try  {
			anonimoService.registrar(Usuario.builder().nombre("Pedro").email("pedro@email.net").password("pedro").build());
			
			usuarioService.listadoUsuarios().forEach(System.out::println);
		} catch(ConstraintViolationException e) {
			e.getConstraintViolations().forEach(c -> System.out.printf("%s: %s\n", c.getPropertyPath(), c.getMessage()));
		}
	}

}
