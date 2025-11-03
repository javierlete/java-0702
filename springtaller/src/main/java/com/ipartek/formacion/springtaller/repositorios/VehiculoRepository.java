package com.ipartek.formacion.springtaller.repositorios;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.springtaller.entidades.Vehiculo;

@RepositoryRestResource(collectionResourceRel = "vehiculos", path = "vehiculos")
public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {
	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol")
	Collection<Vehiculo> obtenerTodosConPropietario();

	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol where v.id=:id")
	Optional<Vehiculo> obtenerPorIdConPropietario(Long id);

	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol where v.matricula=:matricula")
	Optional<Vehiculo> buscarPorMatricula(String matricula);
}
