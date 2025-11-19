package com.ipartek.formacion.amazonia.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ipartek.formacion.amazonia.modelos.Rol;

public interface RolRepository extends CrudRepository<Rol, Long>, PagingAndSortingRepository<Rol, Long> {

	Optional<Rol> findByNombre(String nombre);

}
