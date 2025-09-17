package bases;

import static java.lang.Math.*;

import java.util.Scanner;

public class Operadores {
	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		
		System.out.println(5 ^ 2); // XOR
		// System.out.println(5 ** 2); // NO existe en Java
		System.out.println(pow(5, 2));
		System.out.println(sqrt(4));
		
		// String texto1 = "Javier";
		// String texto2 = sc.nextLine();
		
		// System.out.println(texto1.equals(texto2));
		
		System.out.println(~5);
		
		int x;
		
		System.out.println(x = 5);
		
		System.out.println(x);
		
		sc.close();
	}
}
