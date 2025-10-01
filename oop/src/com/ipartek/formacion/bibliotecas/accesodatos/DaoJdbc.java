package com.ipartek.formacion.bibliotecas.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DaoJdbc<T> {
	private String url;
	private String user;
	private String pass;
	
	public DaoJdbc(String url, String user, String pass) {
		super();
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	public Collection<T> ejecutarConsulta(String sql, Mapeador<T> mapeador, Object... args) {
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement pst = con.prepareStatement(sql);) {

			int i = 1;

			for (var arg : args) {
				pst.setObject(i++, arg);
			}

			if (pst.execute()) {
				ResultSet rs = pst.getResultSet();

				ArrayList<T> objetos = new ArrayList<>();

				while (rs.next()) {
					objetos.add(mapeador.mapear(rs));
				}

				return objetos;
			} else {
				int numeroRegistrosModificados = pst.getUpdateCount();

				if (numeroRegistrosModificados != 1) {
					throw new AccesoDatosException("Se han modificado " + numeroRegistrosModificados + " registros");
				}

				return null;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido ejecutar la consulta", e);
		}
	}

	public interface Mapeador<T> {
		T mapear(ResultSet rs) throws SQLException;
	}
}
