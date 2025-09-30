package com.ipartek.formacion.citas.pruebas;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoUsuario;
import com.ipartek.formacion.citas.entidades.Usuario;

public class DaoUsuarioPrueba {
	public static void main(String[] args) {
		DaoUsuario dao = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");
		
		dao.insertar(new Usuario(null, "javier"));
		
		dao.obtenerTodos().forEach(System.out::println);
	}
}
