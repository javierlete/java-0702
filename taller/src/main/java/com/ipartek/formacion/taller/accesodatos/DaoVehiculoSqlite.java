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
		dao.ejecutarConsulta("INSERT INTO vehiculos (matricula, bastidor, modelo, marca) VALUES (?,?,?,?)", null,
				vehiculo.getMatricula(), vehiculo.getBastidor(), vehiculo.getModelo(), vehiculo.getMarca());

		return vehiculo;
	}

	@Override
	public Vehiculo modificar(Vehiculo vehiculo) {
		dao.ejecutarConsulta("UPDATE vehiculos SET matricula=?, bastidor=?, modelo=?, marca=? WHERE id=?", null,
				vehiculo.getMatricula(), vehiculo.getBastidor(), vehiculo.getModelo(), vehiculo.getMarca(),
				vehiculo.getId());

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
		return new Vehiculo(rs.getLong("id"), rs.getString("matricula"), rs.getString("bastidor"),
				rs.getString("modelo"), rs.getString("marca"),
				EstadoReparacion.valueOf(rs.getString("estado_reparacion")));
	}
}
