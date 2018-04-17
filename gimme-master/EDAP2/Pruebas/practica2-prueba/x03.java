
public class x03 {
	// borra ciudad.
	public static void main(String [] args) throws CoordenadaExcepcion{
		ListaG l = new ListaG();
		
		
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
		l.escribeListaG();
		System.out.println("borrando tokio => " + l.borraCiudad("tokio"));
		l.escribeListaG();
		System.out.println("boorando tailanda => " + l.borraCiudad("bangladesh"));
		l.escribeListaG();
		
		System.out.println("boorando tailanda => " + l.borraCiudad("pekin"));
		l.escribeListaG();
		System.out.println("boorando tailanda => " + l.borraCiudad("shangai"));
		l.escribeListaG();
	
		System.out.println("borrando de lista que quedo vacia " + l.borraCiudad("hola"));
		
		p = new PLoc("AS", "mauritius", null);
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		
		l.insertaCabeza(p);
		l.escribeListaG();
		System.out.println("boorrando null " + l.borraCiudad(null));
		l.escribeListaG();
	}
}
