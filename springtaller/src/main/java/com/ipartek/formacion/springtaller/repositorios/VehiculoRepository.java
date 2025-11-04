package com.ipartek.formacion.springtaller.repositorios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.springtaller.entidades.Vehiculo;

@RepositoryRestResource(collectionResourceRel = "vehiculos", path = "vehiculos")
public interface VehiculoRepository extends CrudRepository<Vehiculo, Long>, PagingAndSortingRepository<Vehiculo, Long> {
	default Page<Vehiculo> obtenerTodosConPropietario() {
		return obtenerTodosConPropietario(Pageable.ofSize(2));
	}

	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol")
	Page<Vehiculo> obtenerTodosConPropietario(Pageable pageable);

	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol where v.id=:id")
	Optional<Vehiculo> obtenerPorIdConPropietario(Long id);

	@Query("from Vehiculo v left join fetch v.propietario p left join fetch p.rol where v.matricula=:matricula")
	Optional<Vehiculo> buscarPorMatricula(String matricula);
}
