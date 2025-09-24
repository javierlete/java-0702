package bases;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Consola {
	public static void main(String[] args) {
		try (var sc = new Scanner(System.in)) {
			System.out.print("Dime tu nombre y te saludo: ");

			var nombre = sc.nextLine();

			System.out.println("Hola " + nombre);
		} catch (NoSuchElementException e) {
			System.out.println("No se pueden encontrar más líneas en la consola");
		}
	}
}
