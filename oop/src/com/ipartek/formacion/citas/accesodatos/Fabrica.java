package com.ipartek.formacion.citas.accesodatos;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Fabrica {
	private static DaoCita dao;
	
	static {
		try {
			Properties props = new Properties();
			
			props.load(new FileReader("fabrica.properties"));
			
			String tipo = props.getProperty("dao.cita");
			
			dao = switch (tipo) {
			case "arraylist" -> new DaoCitaArrayList();
			case "treemap" -> new DaoCitaTreeMap();
			case "jdbc" -> new DaoCitaJdbc();
			default -> throw new IllegalArgumentException("No existe ese tipo");
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DaoCita obtenerDaoCita() {
		return dao; 
	}
}
