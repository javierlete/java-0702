package com.ipartek.formacion.springtaller.pruebas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.springtaller.entidades.Rol;
import com.ipartek.formacion.springtaller.entidades.Usuario;
import com.ipartek.formacion.springtaller.entidades.Vehiculo;
import com.ipartek.formacion.springtaller.repositorios.RolRepository;
import com.ipartek.formacion.springtaller.repositorios.UsuarioRepository;
import com.ipartek.formacion.springtaller.repositorios.VehiculoRepository;

@Order(1)
@Component
public class RepositoriosPrueba implements CommandLineRunner {

	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		var admin = Rol.builder().nombre("ADMINISTRADOR").build();
		var trabajador = Rol.builder().nombre("TRABAJADOR").build();
		var user = Rol.builder().nombre("USUARIO").build();

		rolRepository.saveAll(List.of(admin, user, trabajador));

		usuarioRepository.save(
				Usuario.builder().nombre("Javier").email("javier@email.net").password(passwordEncoder.encode("javier")).rol(admin).build());
		usuarioRepository
				.save(Usuario.builder().nombre("Pepe").email("pepe@email.net").password(passwordEncoder.encode("pepe")).rol(trabajador).build());
		usuarioRepository
				.save(Usuario.builder().nombre("Juan").email("juan@email.net").password(passwordEncoder.encode("juan")).rol(trabajador).build());

		usuarioRepository.findByEmail("pepe@email.net").ifPresent(System.out::println);

		vehiculoRepository.save(Vehiculo.builder().matricula("1234ABC").bastidor("12341234123412341")
				.propietario(Usuario.builder().id(3L).build()).build());
		vehiculoRepository.save(Vehiculo.builder().matricula("5678ABC").bastidor("12341234123412342")
				.propietario(Usuario.builder().id(4L).build()).build());
		vehiculoRepository.save(Vehiculo.builder().matricula("4321ABC").bastidor("12341234123412343")
				.propietario(Usuario.builder().id(4L).build()).build());

		System.out.println("\nTodos los vehículos\n");
		
		vehiculoRepository.findAll().forEach(System.out::println);
		
		System.out.println("\nPrimera página\n");
		
		Page<Vehiculo> pagina = vehiculoRepository.obtenerTodosConPropietario(Pageable.ofSize(2));
		pagina.forEach(System.out::println);
		
		System.out.println("\nSiguiente página\n");
		
		pagina = vehiculoRepository.obtenerTodosConPropietario(pagina.nextOrLastPageable());
		pagina.forEach(System.out::println);
		
		System.out.println("\nBuscar id 2\n");

		vehiculoRepository.obtenerPorIdConPropietario(2L).ifPresent(System.out::println);
		
		System.out.println("\nBuscar matrícula 4321ABC\n");

		vehiculoRepository.buscarPorMatricula("4321ABC").ifPresent(System.out::println);
	}

}
