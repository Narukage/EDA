
public class x02 {
	// prueba insertaCola y prueba borraCola
	public static void main(String [] args) throws CoordenadaExcepcion{
		ListaG l = new ListaG();
		PLoc p;
		l.insertaCola(null); // no hace nada.
		
		System.out.println("insertando AS-tailandia-bangladesh ");
		p = new PLoc("AS", "tailandia", "bangladesh");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		l.escribeListaG();
		System.out.println("------------");
		
		
		System.out.println("insertnado AS-china-pekin");
		p = new PLoc("AS", "china", "pekin");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		l.escribeListaG();
		System.out.println("-------------");
		
		System.out.println("insertando AS-china-shangai");
		p = new PLoc("AS", "china", "shangai");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		l.escribeListaG();
		System.out.println("-------------");
		
		System.out.println("inserntando AS-japon-tokio");
		p = new PLoc("AS", "japon", "tokio");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		l.insertaCola(p);
		l.escribeListaG();
		System.out.println("-------------");
		
		while(!l.esVacia()) {
			System.out.println("borrando cola..");
			System.out.println(l.borraCola());
			l.escribeListaG();
		}
		
		System.out.println("borrando cola de vacio.." + l.borraCola());
	}
}
