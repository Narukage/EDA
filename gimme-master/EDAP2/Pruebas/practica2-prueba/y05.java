
public class y05 {
	// insertaArrayEnPLoc y ciudadEnLista
	public static void main(String [] args) throws CoordenadaExcepcion{
		VectorG l = new VectorG();
		PLoc p;
		
		System.out.println("----------probando insertaArrayPLoc-----------");
		System.out.println("insertando array vacio");
		l.insertaArrayPLoc(null);
		l.escribeVectorG();
		System.out.println();
		System.out.println("insertando un array guay");
		l.insertaArrayPLoc(crearArray());
		l.escribeVectorG();
		
		l = new VectorG();
		p = new PLoc("AS", "esto es un pais", "esto al principio");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCabeza(p);
		p = new PLoc("AS", "es es una moto", "estoy el segundo");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCabeza(p);
		System.out.println("insertando array en lista no vacia");
		l.insertaArrayPLoc(crearArray());
		l.escribeVectorG();
		
		System.out.println("---------probando ciudadEnLista---------------");
		VectorG vacia = new VectorG();
		try {
			System.out.println("buscando ciudad en lista vacia");
			int pos = l.ciudadEnLista("no estoy");
		}
		catch(CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
		}
		try {
			int pos = l.ciudadEnLista(null);
			System.out.println("buscando ciudad null (no esta en la lista): " + pos);
		}catch(CiudadNoEncontradaExcepcion e) {
			System.out.println("ciudad null no encontrada");
		}
		try {
			int pos = l.ciudadEnLista("mi ciudad");
			System.out.println("buscando ciudad mi ciudad (no esta en la lista): " + pos);
		}catch(CiudadNoEncontradaExcepcion e) {
			System.out.println("ciudad mi ciudad no encontrada");
		}
		
		try {
			int pos = l.ciudadEnLista("ciudad 12");
			System.out.println("buscando cidudad que esta dos veces ciudad 12: " + pos);
		}
		catch(CiudadNoEncontradaExcepcion e) {
			System.out.println("si sale esto esta mal");
		}
		System.out.println("insertando una ciudad null y buscando en la lista");
		p = new PLoc("AS", "pais 1 ", null);
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		try {
			int pos = l.ciudadEnLista(null);
			System.out.println("busncando null, encontrado en la posicion " + pos);
		}
		catch(CiudadNoEncontradaExcepcion e) {
			System.out.println("no has encontrado null y si que estaba en la lista");
		}
		
	}
	public static PLoc [] crearArray() throws CoordenadaExcepcion{
		PLoc [] a =  	new PLoc []{
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
		
		for(int i = 0; i < a.length; i++) {
			a[i].setLatitud(new Coordenada(0, 0, 'N'));
			a[i].setLongitud(new Coordenada(0, 0, 'E'));
		}
		
		
		
		return a;
	}
}