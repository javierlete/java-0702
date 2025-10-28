package com.ipartek.formacion.taller.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.DaoJdbc;
import com.ipartek.formacion.taller.modelos.Vehiculo;
import com.ipartek.formacion.taller.modelos.Vehiculo.EstadoReparacion;

public class DaoVehiculoSqlite implements DaoVehiculo {

	private DaoJdbc<Vehiculo> dao = new DaoJdbc<>("jdbc:sqlite:taller.db", "", "");

	@Override
	public Collection<Vehiculo> obtenerTodos() {
		return dao.ejecutarConsulta("SELECT * FROM vehiculos", DaoVehiculoSqlite::mapeador);
	}

	@Override
	public Optional<Vehiculo> obtenerPorId(Long id) {
		return dao.ejecutarConsulta("SELECT * FROM vehiculos WHERE id=?", DaoVehiculoSqlite::mapeador, id).stream()
				.findFirst();
	}

	@Override
	public Vehiculo insertar(Vehiculo vehiculo) {
		Optional<Vehiculo> vehiculoId = dao.ejecutarConsulta(
				"INSERT INTO vehiculos (matricula, bastidor, modelo, marca, estado_reparacion) VALUES (?,?,?,?,?)",
				DaoVehiculoSqlite::mapeador, vehiculo.getMatricula(),
				vehiculo.getBastidor(), vehiculo.getModelo(), vehiculo.getMarca(), vehiculo.getEstadoReparacion())
				.stream().findFirst();

		if (vehiculoId.isPresent()) {
			vehiculo.setId(vehiculoId.get().getId());
		}

		return vehiculo;
	}

	@Override
	public Vehiculo modificar(Vehiculo vehiculo) {
		dao.ejecutarConsulta(
				"UPDATE vehiculos SET matricula=?, bastidor=?, modelo=?, marca=?, estado_reparacion=? WHERE id=?", null,
				vehiculo.getMatricula(), vehiculo.getBastidor(), vehiculo.getModelo(), vehiculo.getMarca(),
				vehiculo.getEstadoReparacion(), vehiculo.getId());

		return vehiculo;
	}

	@Override
	public void borrar(Long id) {
		dao.ejecutarConsulta("DELETE FROM vehiculos WHERE id=?", null, id);
	}

	@Override
	public Optional<Vehiculo> buscarPorMatricula(String matricula) {
		return dao.ejecutarConsulta("SELECT * FROM vehiculos WHERE matricula=?", DaoVehiculoSqlite::mapeador, matricula)
				.stream().findFirst();
	}

	private static Vehiculo mapeador(ResultSet rs) throws SQLException {
		return Vehiculo.builder().id(rs.getLong("id")).matricula(rs.getString("matricula"))
				.bastidor(rs.getString("bastidor")).modelo(rs.getString("modelo")).marca(rs.getString("marca"))
				.estadoReparacion(EstadoReparacion.valueOf(rs.getString("estado_reparacion"))).build();
	}
}
