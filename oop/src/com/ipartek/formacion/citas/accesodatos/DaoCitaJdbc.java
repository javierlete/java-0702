package com.ipartek.formacion.citas.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.citas.entidades.Cita;

public class DaoCitaJdbc implements DaoCita {

	private static final String URL = "jdbc:sqlite:bdd/citas.db";
	private static final String USER = "";
	private static final String PASS = "";

	@Override
	public Collection<Cita> obtenerTodos() {
		return ejecutarConsulta("SELECT * FROM citas");
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return ejecutarConsulta("SELECT * FROM citas WHERE id=?", id).stream().findFirst();
	}

	@Override
	public Cita insertar(Cita cita) {
		ejecutarConsulta("INSERT INTO citas (texto,inicio,fin) VALUES (?,?,?)", cita.getTexto(),
				Timestamp.valueOf(cita.getInicio()), Timestamp.valueOf(cita.getFin()));

		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		ejecutarConsulta("UPDATE citas SET texto=?,inicio=?,fin=? WHERE id=?", cita.getTexto(),
				Timestamp.valueOf(cita.getInicio()), Timestamp.valueOf(cita.getFin()), cita.getId());

		return cita;
	}

	@Override
	public void borrar(Long id) {
		ejecutarConsulta("DELETE FROM citas WHERE id=?", id);
	}

	@Override
	public Collection<Cita> buscarPorTexto(String texto) {
		return ejecutarConsulta("SELECT * FROM citas WHERE texto LIKE ?", "%" + texto + "%");
	}

	private Collection<Cita> ejecutarConsulta(String sql, Object... args) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pst = con.prepareStatement(sql);) {

			int i = 1;

			for (var arg : args) {
				pst.setObject(i++, arg);
			}

			if (pst.execute()) {
				ResultSet rs = pst.getResultSet();

				ArrayList<Cita> citas = new ArrayList<>();

				while (rs.next()) {
					citas.add(new Cita(rs.getLong("id"), rs.getString("texto"),
							rs.getTimestamp("inicio").toLocalDateTime(), rs.getTimestamp("fin").toLocalDateTime()));
				}

				return citas;
			} else {
				int numeroRegistrosModificados = pst.getUpdateCount();

				if (numeroRegistrosModificados == 0) {
					throw new AccesoDatosException("Error de concurrencia");
				}

				return null;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido ejecutar la consulta", e);
		}
	}

}
