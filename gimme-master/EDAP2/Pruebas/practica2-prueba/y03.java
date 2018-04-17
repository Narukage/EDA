
public class y03 {
	// borra ciudad.
	public static void main(String [] args) throws CoordenadaExcepcion{
		VectorG l = new VectorG();
		
		
		System.out.println(l.borraCiudad(null));
		System.out.println(l.borraCiudad("pus"));

		PLoc p;
		
		p = new PLoc("AS", "tailandia", "bangladesh");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		
		p = new PLoc("AS", "china", "pekin");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		
		p = new PLoc("AS", "china", "shangai");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		
		p = new PLoc("AS", "japon", "tokio");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		
		System.out.println("borrando tokio => " + l.borraCiudad("tokio"));
		l.escribeVectorG();
		System.out.println("borrando tokio => " + l.borraCiudad("tokio"));
		l.escribeVectorG();
		System.out.println("boorando tailanda => " + l.borraCiudad("bangladesh"));
		l.escribeVectorG();
		
		System.out.println("boorando tailanda => " + l.borraCiudad("pekin"));
		l.escribeVectorG();
		System.out.println("boorando tailanda => " + l.borraCiudad("shangai"));
		l.escribeVectorG();
	
		System.out.println("borrando de lista que quedo vacia " + l.borraCiudad("hola"));
		
		p = new PLoc("AS", "mauritius", null);
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		
		l.insertaCabeza(p);
		l.escribeVectorG();
		System.out.println("boorrando null " + l.borraCiudad(null));
		l.escribeVectorG();
	}
}
