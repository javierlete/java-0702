package com.ipartek.formacion.citas.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJdbc;
import com.ipartek.formacion.citas.entidades.Cita;

public class DaoCitaJdbc implements DaoCita {

	private final DaoJdbc<Cita> dao = new DaoJdbc<>("jdbc:sqlite:bdd/citas.db", "", "");

	@Override
	public Collection<Cita> obtenerTodos() {
		return dao.ejecutarConsulta("SELECT * FROM citas", DaoCitaJdbc::mapeador);
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return dao.ejecutarConsulta("SELECT * FROM citas WHERE id=?", DaoCitaJdbc::mapeador, id).stream().findFirst();
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
		return dao.ejecutarConsulta("SELECT * FROM citas WHERE texto LIKE ?", DaoCitaJdbc::mapeador, "%" + texto + "%");
	}

	private static Cita mapeador(ResultSet rs) throws SQLException {
		return new Cita(rs.getLong("id"), rs.getString("texto"), rs.getTimestamp("inicio").toLocalDateTime(),
				rs.getTimestamp("fin").toLocalDateTime());
	}
}
