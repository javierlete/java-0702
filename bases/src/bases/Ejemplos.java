package bases;

import java.math.BigDecimal;

public class Ejemplos {
	public static void main(String[] args) {
		var d1 = 0.1;
		var d2 = 0.2;

		var suma = d1 + d2;

		System.out.println(suma);

		var iInt = 5;
		Integer iInteger = iInt;

		System.out.println(iInteger);

		var bd1 = new BigDecimal("0.1");
		var bd2 = new BigDecimal("0.2");

		var bdSuma = bd1.add(bd2);

		System.out.println(bdSuma);

		var texto1 = new String("Texto");
		var texto2 = new String("Texto");

		System.out.println(texto1 == texto2);
		System.out.println(texto1.equals(texto2));

		var saludo = "Hola ";
		var nombre = "Javier";

		System.out.println(nombre.toUpperCase());

		System.out.println(saludo + nombre);

		System.out.println(new StringBuffer(saludo).append(nombre).toString());

		var log = "";

		log += "Uno\n";
		log += "Dos\n";
		log += "Tres\n";

		System.out.println(log);

		var log1 = "";

		log1 = new StringBuffer(log1).append("Uno\n").toString();
		log1 = new StringBuffer(log1).append("Dos\n").toString();
		log1 = new StringBuffer(log1).append("Tres\n").toString();

		System.out.println(log1);

		var sb = new StringBuffer();

		sb.append("Uno\n");
		sb.append("Dos\n");
		sb.append("Tres\n");

		System.out.println(sb.toString());

		System.out.println("""
				Hola a todos
				Qué tal estáis
				Bienvenidos
				""");
		
		System.out.println("Hola a todos\r\n"
				+ "				Qué tal estáis\r\n"
				+ "				Bienvenidos");
	}
}
