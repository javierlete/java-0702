package com.ipartek.formacion.citas.accesodatos;

import java.util.Collection;

import com.ipartek.formacion.bibliotecas.accesodatos.Dao;
import com.ipartek.formacion.citas.entidades.Cita;

public interface DaoCita extends Dao<Cita> {
	Collection<Cita> buscarPorTexto(String texto);
}
