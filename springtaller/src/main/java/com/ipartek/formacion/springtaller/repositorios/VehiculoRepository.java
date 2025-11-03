package com.ipartek.formacion.springtaller.repositorios;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.springtaller.entidades.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {
	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol")
	Collection<Vehiculo> obtenerTodosConPropietario();

	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol where v.id=:id")
	Optional<Vehiculo> obtenerPorIdConPropietario(Long id);

	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol where v.matricula=:matricula")
	Optional<Vehiculo> buscarPorMatricula(String matricula);
}
