



import java.util.TreeSet;

/**
 * Clase para probar las clases ArbolG y ArbolS
 * 
 * @author Nedyar
 * @version 1.0
 */
public class PruebasArbol {
	public static void main(String[] args) {
		if (args.length > 0) {
			Arbol arbol = new ArbolG();
			System.out.println("\t\t-->PRUEBAS ArbolG<--");
			pruebas(arbol, args[0]);

			System.out.println("\t\t-->PRUEBAS ArbolS<--");
			arbol = new ArbolS();
			pruebas(arbol, args[0]);
		} else
			System.err.println("falta el nombre del fichero");
	}

	public static void pruebas(Arbol arbol, String entrada) {
		System.out.println("\n-->Arbol Vacio?");
		System.out.println(arbol.esVacio());

		System.out.println("\n-->Moscow en arbol?");
		System.out.println(arbol.ciudadEnArbol("Moscow"));
		System.out.println("-->Hammerfest en arbol?");
		System.out.println(arbol.ciudadEnArbol("Hammerfest"));
		System.out.println("-->Juju en arbol?");
		System.out.println(arbol.ciudadEnArbol("Juju"));

		// SA#Guyana#Georgetown#6 45 N#58 15 O
		System.out.println("\n-->Insertamos Georgetown?");
		PLoc localidad = new PLoc("SA", "Guyana", "Georgetown");
		try {
			localidad.setLatitud(new Coordenada(6, 45, 'N'));
			localidad.setLongitud(new Coordenada(58, 15, 'O'));
		} catch (CoordenadaExcepcion e) {
			System.out.println("A estas alturas y con fallos en PLoc?");
		}
		System.out.println(arbol.inserta(localidad));

		System.out.println("\n-->Leemos el arbol");
		arbol.leeArbol(entrada);

		System.out.println("\n-->Arbol Vacio?");
		System.out.println(arbol.esVacio());

		if (arbol instanceof ArbolG) {
			System.out.println("\n-->Recorrido en Inorden:");
			((ArbolG) arbol).recorridoInorden();

			System.out.println("\n-->Recorrido por niveles");
			((ArbolG) arbol).recorridoNiveles();
		} else {
			System.out.println("\n-->Recorrido por paises:");
			for (String pais : ((ArbolS) arbol).getPaises()) {

				TreeSet<String> ciudades = ((ArbolS) arbol).getCiudades(new PLoc("", pais, ""));
				int tam = ciudades.size();

				System.out.print(pais + " (" + tam + "):");

				for (int i = 0; i < tam; i++) {
					System.out.print(" " + ciudades.pollFirst());
					if (i < tam - 1)
						System.out.print(" - ");
				}
				System.out.println();
			}

			System.out.println("\n-->Borramos pais France?");
			System.out.println(((ArbolS) arbol).borraPais("France"));

			System.out.println("\n-->Recorrido por paises:");
			for (String pais : ((ArbolS) arbol).getPaises()) {

				TreeSet<String> ciudades = ((ArbolS) arbol).getCiudades(new PLoc("", pais, ""));
				int tam = ciudades.size();

				System.out.print(pais + " (" + tam + "):");

				for (int i = 0; i < tam; i++) {
					System.out.print(" " + ciudades.pollFirst());
					if (i < tam - 1)
						System.out.print(" - ");
				}
				System.out.println();
			}

			System.out.println("\n-->Borramos pais France?");
			System.out.println(((ArbolS) arbol).borraPais("France"));
		}

		System.out.println("\n-->Moscow en arbol?");
		System.out.println(arbol.ciudadEnArbol("Moscow"));
		System.out.println("-->Hammerfest en arbol?");
		System.out.println(arbol.ciudadEnArbol("Hammerfest"));
		System.out.println("-->Juju en arbol?");
		System.out.println(arbol.ciudadEnArbol("Juju"));

		System.out.println("\n-->getCiudades Pais: Argentina");
		TreeSet<String> resultado = arbol.getCiudades(new PLoc("", "Argentina", ""));
		for (String ciudad : resultado) {
			System.out.println(ciudad);
		}

		System.out.println("-->busquedaLejana no:");
		System.out.println(arbol.busquedaLejana("no").getCiudad());
		System.out.println("-->busquedaLejana so:");
		System.out.println(arbol.busquedaLejana("so").getCiudad());
		System.out.println("-->busquedaLejana ne:");
		System.out.println(arbol.busquedaLejana("ne").getCiudad());
		System.out.println("-->busquedaLejana se:");
		System.out.println(arbol.busquedaLejana("se").getCiudad());
		System.out.println();
	}
}
