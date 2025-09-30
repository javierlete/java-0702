package com.ipartek.formacion.citas.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJdbc;
import com.ipartek.formacion.citas.entidades.Usuario;

public class DaoUsuarioJdbc implements DaoUsuario {

	private final DaoJdbc<Usuario> dao = new DaoJdbc<>("jdbc:sqlite:bdd/citas.db", "", "");

	@Override
	public Collection<Usuario> obtenerTodos() {
		return dao.ejecutarConsulta("SELECT * FROM usuarios", DaoUsuarioJdbc::mapeador);
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		return dao.ejecutarConsulta("SELECT * FROM usuarios WHERE id=?", DaoUsuarioJdbc::mapeador, id).stream().findFirst();
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		dao.ejecutarConsulta("INSERT INTO usuarios (nombre) VALUES (?)", null, usuario.getNombre());
		return usuario;
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		dao.ejecutarConsulta("UPDATE usuarios SET nombre=? WHERE id=?", null, usuario.getNombre(), usuario.getId());
		return usuario;
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarConsulta("DELETE FROM usuarios WHERE id=?", null, id);
	}

	private static Usuario mapeador(ResultSet rs) throws SQLException {
		return new Usuario(rs.getLong("id"), rs.getString("nombre"));
	}
}
