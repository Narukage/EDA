
public class y07 {
	// getPLoc
	public static void main(String [] args) {
		VectorG vacia = new VectorG();
		
		System.out.println("getPLoc de lista vacia..");
		try {
			PLoc encontrada = vacia.getPLoc(0);
			encontrada.escribeInfoGps();
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		
		VectorG lista = new VectorG();
		lista = crearLista2();
		System.out.println("buscando localidades en la lista");
		lista.escribeVectorG();
		try {
			PLoc encontrada = lista.getPLoc(0);
			System.out.println("ciudad en posicion 0: ");
			encontrada.escribeInfoGps();
			System.out.println("ciudad en posicion  3: ");
			encontrada = lista.getPLoc(3);
			encontrada.escribeInfoGps();
			System.out.println("ciudad en posicion 9: ");
			encontrada = lista.getPLoc(9);	
			encontrada.escribeInfoGps();
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		System.out.println("buscando en posiciones imposibles---");
		try {
			System.out.println("buscando en posicion -1");
			PLoc encontrada = lista.getPLoc(-1);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		try {
			System.out.println("buscando en posicion 10");
			PLoc encontrada = lista.getPLoc(10);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		try {
			System.out.println("buscando en posicion -10");
			PLoc encontrada = lista.getPLoc(-10);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		try {
			System.out.println("buscando en posicion 13");
			PLoc encontrada = lista.getPLoc(13);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}


		
	}
	public static VectorG crearLista2() {
		VectorG nueva = new VectorG();
		PLoc [] ciudades = {
			new PLoc("AS", null, "ciudad x"),
			new PLoc("AS", null, "ciudad 12"),
			new PLoc("AS", "mi pais", "ciudad 13"),
			new PLoc("AS", "mi pais", "ciudad 12"),
			new PLoc("AS", "pais b", "ciudad 11"),
			new PLoc("AS", "en medio country", "ciudad 14"),
			new PLoc("AS", "pais d", "ciudad 15"),
			new PLoc("AS", "pais d", "ciudad 16"),
			new PLoc("AS", "otro pais", "ciudad 17"),
			new PLoc("AS", "otro pais", "ciudad 18"),
		};
		for(int i = 0; i < ciudades.length; i++) {
			try {
				ciudades[i].setLatitud(new Coordenada(0, 0, 'N'));
				ciudades[i].setLongitud(new Coordenada(0, 0, 'E'));
				nueva.insertaCola(ciudades[i]);
			}
			catch(CoordenadaExcepcion e) {}
		}
		return nueva;
	}
	
}
