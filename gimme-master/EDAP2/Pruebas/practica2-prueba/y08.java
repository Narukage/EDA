
public class y08 {
	public static void main(String [] args) {
		prueba1();
		prueba2();
	}
	public static void prueba1() {
		VectorG lista = new VectorG();
		PLoc p;
		
		try {
			p = new PLoc("AS", "pais A", "ciudad C");
			p.setLatitud(new Coordenada(12, 12, 'N'));
			p.setLongitud(new Coordenada(12, 12, 'E'));
			lista.insertaCola(p);
			p = new PLoc("AS", "pais A", "ciudad B");
			p.setLatitud(new Coordenada(12, 12, 'N'));
			p.setLongitud(new Coordenada(12, 12, 'E'));
			lista.insertaCola(p);
			p = new PLoc("AS", "pais A", "ciudad A");
			p.setLatitud(new Coordenada(12, 12, 'N'));
			p.setLongitud(new Coordenada(12, 12, 'E'));
			lista.insertaCola(p);
		}
		catch(CoordenadaExcepcion e) {
			System.out.println("error raro");
		}
	
		System.out.println("ordenando la lista -----------------");
		lista.escribeVectorG();
		System.out.println("despues de ordenar la lista---------");
		lista.ordenaLista();
		lista.escribeVectorG();
	}
	public static void prueba2() {
		VectorG lista = new VectorG();
		PLoc p;
		
		try {
			p = new PLoc("AS", "pais A", "ciudad A");
			p.setLatitud(new Coordenada(12, 12, 'N'));
			p.setLongitud(new Coordenada(12, 12, 'E'));
			lista.insertaCola(p);
			p = new PLoc("AS", "pais A", "ciudad A");
			p.setLatitud(new Coordenada(12, 12, 'N'));
			p.setLongitud(new Coordenada(11, 19, 'E'));
			lista.insertaCola(p);
			p = new PLoc("AS", "pais A", "ciudad A");
			p.setLatitud(new Coordenada(12, 12, 'N'));
			p.setLongitud(new Coordenada(9, 11, 'E'));
			lista.insertaCola(p);
			
		}
		catch(CoordenadaExcepcion e) {
			System.out.println("error raro");
		}
	
		System.out.println("ordenando la lista -----------------");
		lista.escribeVectorG();
		System.out.println("despues de ordenar la lista---------");
		lista.ordenaLista();
		lista.escribeVectorG();		
	}
}
