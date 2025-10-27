package com.ipartek.formacion.taller.rest;

import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.taller.logicanegocio.UsuarioNegocio;
import com.ipartek.formacion.taller.modelos.Vehiculo;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("vehiculos")
public class VehiculoRest {
	private static final UsuarioNegocio negocio = (UsuarioNegocio) Fabrica.obtenerObjeto("negocio.usuario");
	
	@GET
	public Iterable<Vehiculo> get() {
		return negocio.listadoVehiculos();
	}
	
	@GET
	@Path("{id}")
	public Vehiculo getId(@PathParam("id") Long id) {
		Optional<Vehiculo> vehiculo = negocio.detalleVehiculo(id);
		
		if(vehiculo.isPresent()) {
			return vehiculo.get();
		} else {
			throw new NotFoundException("No se ha encontrado el vehículo cuyo id es " + id);
		}
	}
	
	@POST
	public Response post(Vehiculo vehiculo) {
		Vehiculo creado = negocio.altaVehiculo(vehiculo);
		
		return Response.status(Status.CREATED).entity(creado).build();
	}
	
	@PUT
	@Path("{id}")
	public Vehiculo post(@PathParam("id") Long id, Vehiculo vehiculo) {
		return negocio.modificacionVehiculo(vehiculo);
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		negocio.bajaVehiculo(id);
	}
}
