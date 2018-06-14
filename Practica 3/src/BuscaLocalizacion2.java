import java.util.*;

public class BuscaLocalizacion2 {
	
	public static void main(String[] args) { //Busqueda por latitud y longitud
		if(args.length==4) {
			ArbolG arbol = new ArbolG();
			arbol.leeArbol(args[0]);
			
			double lati 			= 0.0;
			double longi 			= 0.0;
			double rang 			= 0.0;
			String latitud			= args[1];
			String longitud			= args[2];
			String rango 			= args[3];
			ArrayList<PLoc> yay	= new ArrayList<PLoc>();
			TreeSet<PLoc> ciudades 	= arbol.getCiudadesPLoc();
			
			lati = Double.parseDouble(latitud);
			longi = Double.parseDouble(longitud);
			rang = Double.parseDouble(rango);
	
			//Ordeno el ArrayList en orden ascendente
			Collections.sort(yay);
			
			busqueda(ciudades, yay, lati, longi, rang);
			mostrar3(yay);
			
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
	
	public static void busqueda(TreeSet<PLoc> ciudades, ArrayList<PLoc> yay, double lati, double longi, double rang) {
		for(PLoc ploc : ciudades) {
			if(ploc.getGps()[0] >= lati - rang) {
				if(ploc.getGps()[0] <= rang + lati && ploc.getGps()[1] >= longi - rang) {
					if(ploc.getGps()[1] <= rang + longi) {
						yay.add(ploc);
					}
				}
			}
		}
	}
	
	public static void mostrar3(ArrayList<PLoc> yay) {
		if(yay.size() == 0) {
			System.out.println("NO HAY SALIDA");
		}else {
			for(int i = 0; i < yay.size(); i++) {
				yay.get(i).escribeInfoGps();
			}
		}
	}
	
	public static void buscar(ArbolS arbolillo) {
		for(Map.Entry<String, TreeSet<PLoc>> paises : arbolillo.getTreeMap().entrySet()) {
			PLoc p = new PLoc(null, paises.getKey(), null);
			TreeSet<String> ciudades = arbolillo.getCiudades(p);
			Iterator<String> iterator = ciudades.iterator();
			System.out.print(paises.getKey()+" "+"("+ciudades.size()+")"+": ");
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
	
	public static void mostrar2(String[] args, TreeSet<String> ciudades) {
		System.out.print(args[1]+" "+"("+ciudades.size()+")"+": ");
	}
}
