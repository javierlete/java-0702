package com.ipartek.formacion.springtaller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.springtaller.entidades.Vehiculo;
import com.ipartek.formacion.springtaller.servicios.UsuarioService;

@RestController
@RequestMapping("/api/v2/vehiculos")
public class VehiculoRestController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public Iterable<Vehiculo> get() {
		return usuarioService.listadoVehiculos();
	}
	
	@GetMapping("{id}")
	public Vehiculo getId(@PathVariable Long id) {
		return usuarioService.detalleVehiculo(id).orElse(null);
	}
	
	@PostMapping
	public Vehiculo post(@RequestBody Vehiculo vehiculo) {
		return usuarioService.altaVehiculo(vehiculo);
	}
	
	@PutMapping("{id}")
	public Vehiculo post(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
		if(id != vehiculo.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return usuarioService.modificacionVehiculo(vehiculo);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		usuarioService.bajaVehiculo(id);
	}
}
