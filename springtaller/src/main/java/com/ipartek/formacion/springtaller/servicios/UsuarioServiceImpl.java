package com.ipartek.formacion.springtaller.servicios;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.springtaller.entidades.Usuario;
import com.ipartek.formacion.springtaller.entidades.Vehiculo;
import com.ipartek.formacion.springtaller.repositorios.UsuarioRepository;
import com.ipartek.formacion.springtaller.repositorios.VehiculoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Collection<Vehiculo> listadoVehiculos() {
		return vehiculoRepository.findAll(Pageable.ofSize(20)).getContent();
	}

	@Override
	public Collection<Vehiculo> listadoVehiculos(Usuario usuario) {
		String rol = usuario.getRol().getNombre();

		if (rol != null && (rol.equals("ADMINISTRADOR") || rol.equals("TRABAJADOR"))) {
			return listadoVehiculos();
		} else {
			return vehiculoRepository.findByPropietarioId(usuario.getId());
		}
	}

	@Override
	public Optional<Vehiculo> detalleVehiculo(Long id) {
		return vehiculoRepository.findById(id);
	}

	@Override
	public Optional<Vehiculo> detalleVehiculo(String matricula) {
		return vehiculoRepository.buscarPorMatricula(matricula);
	}

	@Override
	public Vehiculo altaVehiculo(@Valid Vehiculo vehiculo) {
		if (vehiculo.getId() != null) {
			throw new IllegalArgumentException("No se puede poner un id para añadir un vehículo");
		}

		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public Vehiculo modificacionVehiculo(@Valid Vehiculo vehiculo) {
		if (vehiculo.getId() == null) {
			throw new IllegalArgumentException("Se necesita un id para modificar un vehículo");
		}

		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public void bajaVehiculo(@Valid @NotNull Long id) {
		vehiculoRepository.deleteById(id);
	}

	@Override
	public Collection<Usuario> listadoUsuarios() {
		return usuarioRepository.findAll(Pageable.ofSize(20)).getContent();
	}

}
