package com.ipartek.formacion.taller.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJdbc;
import com.ipartek.formacion.taller.modelos.Usuario;

public class DaoUsuarioSqlite implements DaoUsuario {

	private static final DaoJdbc<Usuario> dao = new DaoJdbc<>("jdbc:sqlite:taller.db", "", "");

	@Override
	public Collection<Usuario> obtenerTodos() {
		return dao.ejecutarConsulta("SELECT * FROM usuarios", DaoUsuarioSqlite::mapeador);
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		return dao.ejecutarConsulta("SELECT * FROM usuarios WHERE id=?", DaoUsuarioSqlite::mapeador, id).stream()
				.findFirst();
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		dao.ejecutarConsulta("INSERT INTO usuarios (email, password, nombre) VALUES (?,?,?)", null, usuario.getEmail(),
				usuario.getPassword(), usuario.getNombre());
		return usuario;
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		dao.ejecutarConsulta("UPDATE usuarios SET email=?, password=?, nombre=? WHERE id=?", null, usuario.getEmail(),
				usuario.getPassword(), usuario.getNombre(), usuario.getId());
		return usuario;
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarConsulta("DELETE FROM usuarios WHERE id=?", null, id);
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return dao.ejecutarConsulta("SELECT * FROM usuarios WHERE email=?", DaoUsuarioSqlite::mapeador, email).stream()
				.findFirst();
	}

	private static Usuario mapeador(ResultSet rs) throws SQLException {
		return Usuario.builder().id(rs.getLong("id")).nombre(rs.getString("nombre")).email(rs.getString("email"))
				.password(rs.getString("password")).build();
	}
}
