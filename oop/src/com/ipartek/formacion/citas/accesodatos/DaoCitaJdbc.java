package com.ipartek.formacion.citas.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJdbc;
import com.ipartek.formacion.citas.entidades.Cita;
import com.ipartek.formacion.citas.entidades.Usuario;

public class DaoCitaJdbc implements DaoCita {

	private final DaoJdbc<Cita> dao = new DaoJdbc<>("jdbc:sqlite:bdd/citas.db", "", "");

	@Override
	public Collection<Cita> obtenerTodos() {
		return dao.ejecutarConsulta("""
				SELECT c.id, c.texto, c.inicio, c.fin, u.id as u_id, u.nombre as u_nombre
				FROM citas c
				LEFT JOIN usuarios u ON c.usuario_id = u.id;
				""", DaoCitaJdbc::mapeador);
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return dao.ejecutarConsulta("""
				SELECT c.id, c.texto, c.inicio, c.fin, u.id as u_id, u.nombre as u_nombre
				FROM citas c
				LEFT JOIN usuarios u ON c.usuario_id = u.id
				WHERE id=?;
				""", DaoCitaJdbc::mapeador, id).stream().findFirst();
	}

	@Override
	public Cita insertar(Cita cita) {
		dao.ejecutarConsulta("INSERT INTO citas (texto,inicio,fin) VALUES (?,?,?)", null, cita.getTexto(),
				Timestamp.valueOf(cita.getInicio()), Timestamp.valueOf(cita.getFin()));

		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		dao.ejecutarConsulta("UPDATE citas SET texto=?,inicio=?,fin=? WHERE id=?", null, cita.getTexto(),
				Timestamp.valueOf(cita.getInicio()), Timestamp.valueOf(cita.getFin()), cita.getId());

		return cita;
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarConsulta("DELETE FROM citas WHERE id=?", null, id);
	}

	@Override
	public Collection<Cita> buscarPorTexto(String texto) {
		return dao.ejecutarConsulta("""
				SELECT c.id, c.texto, c.inicio, c.fin, u.id as u_id, u.nombre as u_nombre
				FROM citas c
				LEFT JOIN usuarios u ON c.usuario_id = u.id
				WHERE texto LIKE ?;
				""", DaoCitaJdbc::mapeador, "%" + texto + "%");
	}

	private static Cita mapeador(ResultSet rs) throws SQLException {
		Usuario usuario = rs.getObject("u_id") == null ? null : new Usuario(rs.getLong("u_id"), rs.getString("u_nombre"));
		return new Cita(rs.getLong("id"), usuario, rs.getString("texto"), rs.getTimestamp("inicio").toLocalDateTime(),
				rs.getTimestamp("fin").toLocalDateTime());
	}
}
