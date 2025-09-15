package bases;

import java.math.BigDecimal;

public class Ejemplos {
	public static void main(String[] args) {
		double d1 = 0.1, d2 = 0.2;
		
		double suma = d1 + d2;
		
		System.out.println(suma);
		
		int iInt = 5;
		Integer iInteger = iInt;
		
		System.out.println(iInteger);
		
		BigDecimal bd1 = new BigDecimal("0.1");
		BigDecimal bd2 = new BigDecimal("0.2");
		
		BigDecimal bdSuma = bd1.add(bd2);
		
		System.out.println(bdSuma);
		
		String texto1 = new String("Texto");
		String texto2 = new String("Texto");
		
		System.out.println(texto1 == texto2);
		System.out.println(texto1.equals(texto2));
		
		String saludo = "Hola ";
		String nombre = "Javier";
		
		System.out.println(nombre.toUpperCase());
		
		System.out.println(saludo + nombre);
		
		System.out.println(new StringBuffer(saludo).append(nombre).toString());
		
		String log = "";
		
		log += "Uno\n";
		log += "Dos\n";
		log += "Tres\n";
		
		System.out.println(log);
		
		String log1 = "";
		
		log1 = new StringBuffer(log1).append("Uno\n").toString();
		log1 = new StringBuffer(log1).append("Dos\n").toString();
		log1 = new StringBuffer(log1).append("Tres\n").toString();
		
		System.out.println(log1);
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("Uno\n");
		sb.append("Dos\n");
		sb.append("Tres\n");
		
		System.out.println(sb.toString());
	}
}
