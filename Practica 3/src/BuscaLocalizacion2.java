import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class BuscaLocalizacion2 {
	
	public static void main(String[] args) { //Busqueda por latitud y longitud
		if(args.length==4) {
			ArbolG arbol = new ArbolG();
			arbol.leeArbol(args[0]);
			
			//FALTA POR HACER ME ESTOY LIANDO MUCHO
			//MORIRME QUIERO
			
		}else if(args.length==2) { //Busqueda por pais
			ArbolS arbol = new ArbolS();
			arbol.leeArbol(args[0]);
			
			if(!arbol.buscarPais(args[1])) {
				buscar(arbol);
			}else{
				buscarElse(arbol, args);
			}
		}
	}
	
	public static void buscar(ArbolS arbolillo) {
		for(Map.Entry<String, TreeSet<PLoc>> paises : arbolillo.getTreeMap().entrySet()) {
			PLoc p = new PLoc(null, paises.getKey(), null);
			TreeSet<String> ciudades = arbolillo.getCiudades(p);
			Iterator<String> iterator = ciudades.iterator();
			mostrar(ciudades, paises);
			String aux = iterator.next();
			System.out.print(aux);
			
				while(iterator.hasNext()) {
					System.out.print("- "+iterator.next());
				}
			System.out.println();
		}
	}
	
	public static void buscarElse(ArbolS arbolillo, String[] args) {
		PLoc p = new PLoc(null, args[1], null);
		TreeSet<String> ciudades = arbolillo.getCiudades(p);
		Iterator<String> iterator = ciudades.iterator();
		mostrar2(args, ciudades);
		System.out.print(iterator.next());
		
		while(iterator.hasNext()) {
				System.out.print("- "+iterator.next());
		}
		System.out.println();
	}
	
	public static void mostrar(TreeSet<String> ciudades, TreeSet<PLoc> paises) {
		System.out.print(paises.getKey()+" "+"("+ciudades.size()+")"+": ");
	}
	
	public static void mostrar2(String[] args, TreeSet<String> ciudades) {
		System.out.print(args[1]+" "+"("+ciudades.size()+")"+": ");
	}
}
