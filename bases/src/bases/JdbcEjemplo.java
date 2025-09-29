package bases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEjemplo {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:sqlite:bdd/ejemplo.db";
		String user = "";
		String pass = "";
		
		Connection con = DriverManager.getConnection(url, user, pass);
		PreparedStatement pst = con.prepareStatement("SELECT * FROM personas");
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			System.out.printf("%4s %-20s\n", rs.getLong("id"), rs.getString("nombre"));
		}
		
		pst = con.prepareStatement("INSERT INTO personas (nombre) VALUES (?)");
		
		String nombre = "Prueba";
		
		pst.setString(1, nombre);
		
		pst.executeUpdate();
	}
}
