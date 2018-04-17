
public class y01 {
	// prueba insertaCabeza y borraCabeza
	public static void main(String [] args) throws CoordenadaExcepcion{
		VectorG l = new VectorG();
		PLoc p;
		l.insertaCabeza(null); // no hace nada.
		
		System.out.println("insertando AS-tailandia-bangladesh ");
		p = new PLoc("AS", "tailandia", "bangladesh");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		
		l.insertaCabeza(p);
		l.escribeVectorG();
		System.out.println("------------");
		
		
		p = new PLoc("AS", "china", "pekin");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		
		System.out.println("insertnado AS-china-pekin");
		l.insertaCabeza(p);
		l.escribeVectorG();
		System.out.println("-------------");
		
		System.out.println("insertando AS-china-shangai");
		p = new PLoc("AS", "china", "shangai");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		
		l.insertaCabeza(p);
		l.escribeVectorG();
		System.out.println("-------------");
		
		System.out.println("inserntando AS-japon-tokio");
		p = new PLoc("AS", "japon", "tokio");
		p.setLongitud(new Coordenada(0, 0, 'E'));
		p.setLatitud(new Coordenada(0, 0, 'N'));
		
		l.insertaCabeza(p);
		l.escribeVectorG();
		System.out.println("-------------");
		
		while(!l.esVacia()) {
			System.out.println(l.borraCabeza());
			System.out.println("borrando...");
			l.escribeVectorG();
		}
		System.out.println("borrando cola de vacio => " + l.borraCola());
		
	
	}
}
