package com.ipartek.formacion.taller.accesodatos;

import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.Dao;
import com.ipartek.formacion.taller.modelos.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
	Optional<Usuario> obtenerPorEmail(String email);
}
