

/**
 * Clase para probar la clase BuscaLocalizacion2
 * 
 * @author Nedyar
 * @version 1.0
 */
public class PruebasAplicaciones {
	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("\tPruebas de Busqueda por rango\n");
			String[] newargs = new String[4];
			newargs[0] = args[0];
			newargs[1] = "0";
			newargs[2] = "0";
			newargs[3] = "0";
			BuscaLocalizacion2.main(newargs);
			System.out.println();

			newargs[3] = "-1";
			BuscaLocalizacion2.main(newargs);
			System.out.println();

			newargs[1] = "35.75";
			newargs[2] = "51.75";
			newargs[3] = "35";
			BuscaLocalizacion2.main(newargs);
			System.out.println();

			newargs[1] = "35.75";
			newargs[2] = "51.75";
			newargs[3] = "155";
			BuscaLocalizacion2.main(newargs);
			System.out.println();

			newargs[1] = "35.75";
			newargs[2] = "51.75";
			newargs[3] = "0.1";
			BuscaLocalizacion2.main(newargs);
			System.out.println();

			System.out.println("\n\tPruebas de Busqueda por paises\n");
			newargs = new String[2];
			newargs[0] = args[0];
			newargs[1] = "";
			BuscaLocalizacion2.main(newargs);
			System.out.println();

			newargs[1] = "Argentina";
			BuscaLocalizacion2.main(newargs);
			System.out.println();

			newargs[1] = "Senegal";
			BuscaLocalizacion2.main(newargs);
			System.out.println();
		} else
			System.err.println("falta el nombre del fichero");
	}
}