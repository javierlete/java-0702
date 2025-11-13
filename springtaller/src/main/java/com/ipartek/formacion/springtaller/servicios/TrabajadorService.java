package com.ipartek.formacion.springtaller.servicios;

import com.ipartek.formacion.springtaller.entidades.Vehiculo;

public interface TrabajadorService {
	void meterVehiculoEnCola(Long idVehiculo);
	Vehiculo sacarVehiculoDeCola();
	Iterable<Vehiculo> listarVehiculosEnCola();
}
