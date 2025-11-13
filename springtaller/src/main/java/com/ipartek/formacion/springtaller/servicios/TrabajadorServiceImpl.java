package com.ipartek.formacion.springtaller.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.springtaller.entidades.Vehiculo;
import com.ipartek.formacion.springtaller.entidades.Vehiculo.EstadoReparacion;
import com.ipartek.formacion.springtaller.modelo.ColaVehiculos;
import com.ipartek.formacion.springtaller.repositorios.VehiculoRepository;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

	@Autowired
	private ColaVehiculos cola;
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Override
	public void meterVehiculoEnCola(Long id) {
		Vehiculo vehiculo = vehiculoRepository.findById(id).get();
		
		vehiculo.setEstadoReparacion(EstadoReparacion.EN_CURSO);
		
		vehiculoRepository.save(vehiculo);
		
		cola.meter(vehiculo);
	}

	@Override
	public Vehiculo sacarVehiculoDeCola() {
		var vehiculo = cola.sacar();
		
		vehiculo.setEstadoReparacion(EstadoReparacion.RECIBIDO);
		
		vehiculoRepository.save(vehiculo);
		
		return vehiculo;
	}

	@Override
	public Iterable<Vehiculo> listarVehiculosEnCola() {
		return cola.getVehiculos();
	}
	
}
