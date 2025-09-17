package bases;

import java.util.Scanner;

public class Consola {
	public static void main(String[] args) {
		System.out.print("Dime tu nombre y te saludo: ");
		
		var sc = new Scanner(System.in);
		
		var nombre = sc.nextLine();
		
		System.out.println("Hola " + nombre);
		
		sc.close();
	}
}
