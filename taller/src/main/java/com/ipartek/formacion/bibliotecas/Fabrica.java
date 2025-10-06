package com.ipartek.formacion.bibliotecas;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Fabrica {
	private static final Properties props = new Properties();
	
	static {
		try {
			props.load(new FileReader("fabrica.properties"));
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Error al leer el fichero properties");
		}
	}
	
	public static Object obtenerObjeto(String clave) {
		try {
			String tipo = props.getProperty(clave);
			
			Class<?> clase = Class.forName(tipo);
			Constructor<?> constructor = clase.getConstructor();
			Object objeto = constructor.newInstance(); 
			
			return objeto;
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new IllegalArgumentException("No se ha podido crear el objeto", e);
		}
	}
}
