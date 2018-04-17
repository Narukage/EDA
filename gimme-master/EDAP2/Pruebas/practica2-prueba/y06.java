
public class y06 {
	// getPLoc y Pais
	public static void main(String [] args) {
		VectorG lista = crearLista2();
		VectorG vacia = new VectorG();
		PLoc [] encontradas = null;
		
		System.out.println("buscando pais en lista vacia");
		encontradas = vacia.Pais("un pais");
		System.out.println(encontradas);
		
		System.out.println("buscamos en la lista:");
		lista.escribeVectorG();
		System.out.println("buscando pais null");
		encontradas = lista.Pais(null);
		escribeVector(encontradas);
		System.out.println("buscamos otro pais: ");
		encontradas = lista.Pais("otro pais");
		escribeVector(encontradas);
		
	}
	public static VectorG crearLista2() {
		VectorG nueva = new VectorG();
		PLoc [] ciudades = {
			new PLoc("AS", null, "ciudad x"),
			new PLoc("AS", null, null),
			new PLoc("AS", "mi pais", "ciudad 13"),
			new PLoc("AS", "mi pais", "ciudad 12"),
			new PLoc("AS", "pais b", "ciudad 11"),
			new PLoc("AS", "en medio country", "ciudad 14"),
			new PLoc("AS", "pais d", "ciudad 15"),
			new PLoc("AS", null, "ciudad 16"),
			new PLoc("AS", "otro pais", "ciudad 17"),
			new PLoc("AS", "otro pais", "ciudad 18"),
		};
		for(int i = 0; i < ciudades.length; i++) {
			try {
				ciudades[i].setLatitud(new Coordenada(0, 0, 'N'));
				ciudades[i].setLongitud(new Coordenada(0, 0, 'E'));
			}
			catch(CoordenadaExcepcion e) {}
			nueva.insertaCola(ciudades[i]);
		}
		return nueva;
	}
	public static void escribeVector(PLoc [] v) {
		for(PLoc p : v) {
			p.escribeInfoGps();
		}
	}
}
