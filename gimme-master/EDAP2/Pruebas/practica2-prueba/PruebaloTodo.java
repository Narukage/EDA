

/**
 * Clase para probar la Practica 2
 * 
 * @author Nedyar
 */
public class PruebaloTodo {
	static String entrada, entrada2;

	public static void main(String[] args) {
		if (args.length == 2) {
			entrada = args[0];
			entrada2 = args[1];

			Lista lista = new ListaG();
			System.out.println("\t\tPRUEBAS DE ListaG\n");
			pruebasComunes(lista);
			
			lista = new VectorG();
			System.out.println("\n\t\tPRUEBAS DE VectorG\n");
			pruebasComunes(lista);
		} else {
			System.err.println("Error de entrada. El orden de los argumentos debe ser el siguiente:");
			System.err.println("pruebaloTodo.dat\n" + "pruebaloTodoJodido.dat\n");
		}
	}

	public static void pruebasComunes(Lista lista) {
		seguimiento("Operaciones en lista vacia");
		System.out.println(lista.borraCabeza());
		System.out.println(lista.borraCola());
		System.out.println(lista.borraCiudad(""));
		System.out.println(lista.borraPais(""));
		try {
			System.out.println(lista.ciudadEnLista("Ciudad cualquiera"));
		} catch (CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
		}
		try {
			System.out.println(lista.getPLoc(0));
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		System.out.println(lista.Pais(""));
		lista.ordenaLista();
		escribeLista(lista);

		seguimiento("Operaciones con nulls");
		lista.insertaCabeza(null);
		lista.insertaCola(null);
		lista.insertaArrayPLoc(null);
		lista.leeLista(null);
		try {
			System.out.println(lista.ciudadEnLista(null));
		} catch (CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
		}
		System.out.println(lista.borraCiudad(null));
		System.out.println(lista.borraPais(null));
		System.out.println(lista.Pais(null));
		escribeLista(lista);

		seguimiento("Operaciones de insercion");
		PLoc[] ciudades = iniciaCiudades(15);
		lista.insertaCabeza(ciudades[4]);
		escribeLista(lista);

		lista = reiniciarLista(lista);
		lista.insertaCola(ciudades[10]);
		escribeLista(lista);

		lista = reiniciarLista(lista);
		lista.insertaArrayPLoc(ciudades);
		escribeLista(lista);

		lista.insertaCabeza(ciudades[4]);
		lista.insertaArrayPLoc(iniciaCiudades(3));
		lista.insertaCola(ciudades[10]);
		escribeLista(lista);

		seguimiento("Operaciones de consulta validas");
		try {
			System.out.println(lista.ciudadEnLista("Ciudad5"));
		} catch (CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
		}
		try {
			lista.getPLoc(7).escribeInfoGrados();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		try {
			lista.getPLoc(100).escribeInfoGrados();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		try {
			lista.getPLoc(-1).escribeInfoGrados();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		ciudades = lista.Pais("Pais");
		if (ciudades != null)
			if (ciudades.length > 0)
				for (PLoc ciudad : ciudades) {
					ciudad.escribeInfoGrados();
				}
			else
				System.out.println("array vacio");
		else
			System.out.println("array nulo");

		seguimiento("Operaciones de borrado");
		System.out.println(lista.borraCiudad("Ciudad0"));
		System.out.println(lista.borraCabeza());
		System.out.println(lista.borraCola());
		escribeLista(lista);
		System.out.println(lista.borraPais("Pas"));
		System.out.println(lista.borraPais("Pais"));
		escribeLista(lista);

		seguimiento("Operaciones de consultas no validas");
		try {
			System.out.println(lista.ciudadEnLista("Ciudad5"));
		} catch (CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
		}
		try {
			lista.getPLoc(7).escribeInfoGrados();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		try {
			lista.getPLoc(0).escribeInfoGrados();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		try {
			lista.getPLoc(-1).escribeInfoGrados();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		ciudades = lista.Pais("Pais");
		if (ciudades != null)
			if (ciudades.length > 0)
				for (PLoc ciudad : ciudades) {
					ciudad.escribeInfoGrados();
				}
			else
				System.out.println("array vacio");
		else
			System.out.println("array nulo");
		escribeLista(lista);

		seguimiento("Consultas a datos con null");
		ciudades = new PLoc[3];
		ciudades[0] = new PLoc("Continente", "Pais", null);
		ciudades[1] = new PLoc("Continente", null, null);
		ciudades[2] = new PLoc("Continente", null, null);
		lista.insertaArrayPLoc(ciudades);
		try {
			System.out.println(lista.ciudadEnLista(null));
		} catch (CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
		}
		System.out.println(lista.borraCiudad(null));
		ciudades = lista.Pais(null);
		for (PLoc ciudad : ciudades)
			ciudad.escribeInfoGrados();
		System.out.println(lista.borraPais(null));
		escribeLista(lista);

		seguimiento("Leyendo lista facil");
		lista.leeLista(entrada);
		escribeLista(lista);

		seguimiento("Ordenacion de lista facil");
		lista.ordenaLista();
		escribeLista(lista);

		seguimiento("Leyendo lista jodida");
		lista.leeLista(entrada2);
		escribeLista(lista);

		seguimiento("Ordenacion de lista jodida");
		lista.ordenaLista();
		escribeLista(lista);
	}

	public static Lista reiniciarLista(Lista lista) {
		if (lista instanceof ListaG)
			lista = new ListaG();
		else
			lista = new VectorG();
		return lista;
	}

	public static PLoc[] iniciaCiudades(int length) {
		PLoc[] ciudades = new PLoc[length];
		for (int i = 0; i < ciudades.length; i++) {
			ciudades[i] = new PLoc("Continente", "Pais", "Ciudad" + i);

			Coordenada latitud;
			if (i % 2 == 0 && i % 3 == 0)
				latitud = new Coordenada((i * 3) % 90, (i * 7) % 59, 'N');
			else
				latitud = new Coordenada((i * 2) % 90, (i * 5) % 59, 'S');

			Coordenada longitud;
			if (i % 4 == 0 || i % 5 == 0)
				longitud = new Coordenada((i * 2) % 90, (i * 4) % 59, 'E');
			else
				longitud = new Coordenada((i * 9) % 90, (i * 8) % 59, 'O');

			if (i % 8 == 0)
				latitud = null;

			if (i % 9 == 0)
				longitud = null;

			try {
				ciudades[i].setLatitud(latitud);
				ciudades[i].setLongitud(longitud);
			} catch (CoordenadaExcepcion e) {
				System.out.println(e);
			}
		}
		return ciudades;
	}

	public static void seguimiento(String argumento) {
		System.out.println("\n\t-> Seguimiento: " + argumento + " <-");
	}

	public static void escribeLista(Lista lista) {
		System.out.println("-> Lista:");
		if (lista != null)
			if (lista.esVacia())
				System.out.println("Lista vacia");
			else if (lista instanceof ListaG)
				((ListaG) lista).escribeListaG();
			else
				((VectorG) lista).escribeVectorG();
		else
			System.out.println("Lista Nula");
	}
}
