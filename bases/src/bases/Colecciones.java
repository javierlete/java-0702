package bases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Colecciones {
	public static void main(String[] args) {
		var lista = new ArrayList<String>();
		
		lista.add("Uno");
		lista.add("Dos");
		lista.add("Dos");
		lista.add(null);
		lista.add("Tres");
		
		System.out.println(lista);
		
		System.out.println(lista.size());
		
		System.out.println(lista.get(1));
		
		var conjunto = new HashSet<String>();
		
		conjunto.add("Uno");
		conjunto.add("Dos");
		conjunto.add("Dos");
		conjunto.add(null);
		conjunto.add("Tres");
		
		System.out.println(conjunto);
		
		System.out.println(conjunto.size());
		
		var iterador = conjunto.iterator();
		
		System.out.println(iterador.next());
		System.out.println(iterador.next());
		
		var mapa = new HashMap<String, String>();
		
		mapa.put("casa", "house");
		mapa.put("perro", "dog");
		mapa.put("cocina", "kitchen");
		
		System.out.println(mapa.get("perro"));
		
		System.out.println(mapa);
		
		System.out.println(mapa.keySet());
		
		System.out.println(mapa.values());
		
		System.out.println(mapa.entrySet());
	}
}
