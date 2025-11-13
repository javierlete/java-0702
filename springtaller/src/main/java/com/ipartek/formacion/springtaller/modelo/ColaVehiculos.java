package com.ipartek.formacion.springtaller.modelo;

import java.util.LinkedHashSet;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ipartek.formacion.springtaller.entidades.Vehiculo;

@Component
@SessionScope
public class ColaVehiculos {
	private LinkedHashSet<Vehiculo> cola = new LinkedHashSet<>();
	
	public Iterable<Vehiculo> getVehiculos() {
		return cola;
	}
	
	public void meter(Vehiculo vehiculo) {
		cola.add(vehiculo);
	}
	
	public Vehiculo sacar() {
		var vehiculo = cola.getFirst();
		
		cola.removeFirst();
		
		return vehiculo;
	}
}
