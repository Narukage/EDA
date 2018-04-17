import java.util.ArrayList;

public class x04 {
	public static void main(String [] args) {
		ListaG l = null;
		
		l = crearLista2();
		System.out.println("+++++borrando pais null (dos al principio) => " + l.borraPais(null));
		l.escribeListaG();
		System.out.println("+++++borrando pais en medio country (uno en medio) => " + l.borraPais("en medio country"));
		l.escribeListaG();
		System.out.println("+++++borrando pais otro pais (dos al final) => " + l.borraPais("otro pais"));
		l.escribeListaG();
		System.out.println("+++++borrando mi pais (dos al principio) => " + l.borraPais("mi pais"));
		l.escribeListaG();
		System.out.println("+++++borrando pais d (dos al final) => " + l.borraPais("pais d"));
		l.escribeListaG();
		System.out.println("+++++borrando pais b (dos elementos lista queda vacia): " + l.borraPais("pais b"));
		l.escribeListaG();
		System.out.println("+++++la lista queda vacia " + l.esVacia());
		
		System.out.println("CREANDO OTRA LISTA....");
		l = crearLista();
		l.escribeListaG();
		System.out.println("borrando pais que no existe " + l.borraPais("purqui"));
		System.out.println("borrando pais 1 => " + l.borraPais("pais 1"));
		l.escribeListaG();
		System.out.println("borrando pais b => " + l.borraPais("pais b"));
		l.escribeListaG();
		System.out.println("borrando pais d => " + l.borraPais("pais d"));
		l.escribeListaG();
		System.out.println("borrando pais a => " + l.borraPais("pais a"));
		l.escribeListaG();
		
	}
	
	public static ListaG crearLista() {
		ListaG nueva = new ListaG();
		PLoc [] ciudades = {
			new PLoc("AS", "pais 1", "ciudad x"),
			new PLoc("AS", "pais 1", "ciudad 12"),
			new PLoc("AS", "pais a", "ciudad 13"),
			new PLoc("AS", "pais a", "ciudad 12"),
			new PLoc("AS", "pais b", "ciudad 11"),
			new PLoc("AS", "pais 1", "ciudad 14"),
			new PLoc("AS", "pais d", "ciudad 15"),
			new PLoc("AS", "pais d", "ciudad 16"),
			new PLoc("AS", "pais 1", "ciudad 17"),
			new PLoc("AS", "pais 1", "ciudad 18"),
		};
		try {
			for(int i = 0; i < ciudades.length; i++) {
				ciudades[i].setLatitud(new Coordenada(0, 0, 'N'));
				ciudades[i].setLongitud(new Coordenada(0, 0, 'E'));
				nueva.insertaCola(ciudades[i]);
			}
		}catch(CoordenadaExcepcion e){}
		return nueva;
	}
	public static ListaG crearLista2() {
		ListaG nueva = new ListaG();
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
		try {
			for(int i = 0; i < ciudades.length; i++) {
				ciudades[i].setLatitud(new Coordenada(0, 0, 'N'));
				ciudades[i].setLongitud(new Coordenada(0, 0, 'E'));
				nueva.insertaCola(ciudades[i]);
			}
		}
		catch(CoordenadaExcepcion e) {}
		return nueva;
	}
}
