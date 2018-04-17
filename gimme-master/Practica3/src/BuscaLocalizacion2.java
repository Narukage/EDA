//DNI 48620792B BARBA ROBLES, ALBERTO
/*
 COSTES ArbolG:
 1) int esVacio(): O(1)
 2) boolean inserta(PLoc p): O(log n)
 3) int ciudadEnArbol(String v): O(n)
 4) PLoc busquedaLejana(String s): 0(n)
 	
 COSTES aplicacion:
 1) busqueda por rango:
 2) busqueda por paises:
 */
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class BuscaLocalizacion2 {

	public static void main(String[] args) {
		if(args.length==4) { //nombre del fichero base de datos, latitud y longitud (double), rango (double)
			ArbolG arbolisimo = new ArbolG();
			arbolisimo.leeArbol(args[0]);
			
			
			//NEW: metodo recursivo para poder obtener el nodo mas alejado (NO, SO, SE, NE) de todos y devolverlo
			/*public NodoAG recursivoLejano(NodoAG nodo, int tipo){
				//El tipo indica el tipo de recorrido lejano
				NodoAG nodoFinal = null;
				//Recorrido NO
				if(tipo == 0) {
					//Si NO devuelve un nodo NO, este es el mas lejano
					if(nodo.getNO()!=null) {
						nodoFinal = nodo.getNO(); //De momento este es el mas lejano
						NodoAG aux = recursivoLejano(nodo.getNO(),0); //recorremos buscando un NO mas lejano aun
						if(aux!=null) { //Si existe, este sera el nuevo nodo lejano
							nodoFinal = aux;
						}
					}
					//Sino, seguimos explorando por SO hasta alcanzarlo
					if(nodo.getSO()!=null && nodoFinal == null) {
						//Seguimos iterando por SO hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getSO(),0);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getSO(); //De momento este es el mas cercano a NO
						}
					}
					//Sino, seguimos explorando por NE hasta alcanzarlo
					if(nodo.getNE()!=null) { 
						//Seguimos iterando por NE hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getNE(),0);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getNE(); //De momento este es el mas cercano a NO
						}
					}
					//Sino, seguimos explorando por SE hasta alcanzarlo
					if(nodo.getSE()!=null) {
						//Seguimos iterando por SE hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getSE(),0);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getSE(); //De momento este es el mas cercano a NO
						}
					}
				}
				
				//Recorrido SO
				if(tipo == 1) {
					//Si SO devuelve un nodo SO, este es el mas lejano
					if(nodo.getSO()!=null) {
						nodoFinal = nodo.getSO(); //De momento este es el mas lejano
						NodoAG aux = recursivoLejano(nodo.getSO(),1); //recorremos buscando un SO mas lejano aun
						if(aux!=null) { //Si existe, este sera el nuevo nodo lejano
							nodoFinal = aux;
						}
					}
					//Sino, seguimos explorando por NO hasta alcanzarlo
					if(nodo.getNO()!=null && nodoFinal == null) {
						//Seguimos iterando por NO hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getNO(),1);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getNO(); //De momento este es el mas cercano a SO
						}
					}
					//Sino, seguimos explorando por SE hasta alcanzarlo
					if(nodo.getSE()!=null) {
						//Seguimos iterando por SE hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getSE(),1);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getSE(); //De momento este es el mas cercano a SO
						}
					}
					//Sino, seguimos explorando por NE hasta alcanzarlo
					if(nodo.getNE()!=null) { 
						//Seguimos iterando por NE hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getNE(),1);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getNE(); //De momento este es el mas cercano a SO
						}
					}
				}
				
				//Recorrido NE
				if(tipo == 2) {
					//Si SO devuelve un nodo NE, este es el mas lejano
					if(nodo.getNE()!=null) {
						nodoFinal = nodo.getNE(); //De momento este es el mas lejano
						NodoAG aux = recursivoLejano(nodo.getNE(),2); //recorremos buscando un NE mas lejano aun
						if(aux!=null) { //Si existe, este sera el nuevo nodo lejano
							nodoFinal = aux;
						}
					}
					//Sino, seguimos explorando por NE hasta alcanzarlo
					if(nodo.getSE()!=null) { 
						//Seguimos iterando por NE hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getSE(),2);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getSE(); //De momento este es el mas cercano a NE
						}
					}
					//Sino, seguimos explorando por NO hasta alcanzarlo
					if(nodo.getNO()!=null && nodoFinal == null) {
						//Seguimos iterando por NO hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getNO(),2);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getNO(); //De momento este es el mas cercano a NE
						}
					}
					//Sino, seguimos explorando por SE hasta alcanzarlo
					if(nodo.getSO()!=null) {
						//Seguimos iterando por SE hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getSO(),2);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getSO(); //De momento este es el mas cercano a NE
						}
					}
				}
				
				//Recorrido SE
				if(tipo == 3) {
					//Si SO devuelve un nodo SE, este es el mas lejano
					if(nodo.getSE()!=null) {
						nodoFinal = nodo.getSE(); //De momento este es el mas lejano
						NodoAG aux = recursivoLejano(nodo.getSE(),2); //recorremos buscando un SE mas lejano aun
						if(aux!=null) { //Si existe, este sera el nuevo nodo lejano
							nodoFinal = aux;
						}
					}
					//Sino, seguimos explorando por NE hasta alcanzarlo
					if(nodo.getNE()!=null) { 
						//Seguimos iterando por NE hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getNE(),3);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getNE(); //De momento este es el mas cercano a SE
						}
					}
					//Sino, seguimos explorando por SE hasta alcanzarlo
					if(nodo.getSO()!=null) {
						//Seguimos iterando por SE hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getSO(),3);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getSO(); //De momento este es el mas cercano a SE
						}
					}
					//Sino, seguimos explorando por NO hasta alcanzarlo
					if(nodo.getNO()!=null && nodoFinal == null) {
						//Seguimos iterando por NO hasta encontrarlo
						NodoAG aux = recursivoLejano(nodo.getNO(),3);
						if(aux != null) {
							nodoFinal = aux;
						}else {
							nodoFinal = nodo.getNO(); //De momento este es el mas cercano a SE
						}
					}

				}
				return nodoFinal;
			}*/
		}else if(args.length==2) {
			ArbolS arbolillo = new ArbolS();
			arbolillo.leeArbol(args[0]);
			
			if(!arbolillo.buscarPais(args[1])) {
				for(Map.Entry<String, TreeSet<PLoc>> paises : arbolillo.getTreeMap().entrySet()) {
					PLoc p = new PLoc(null, paises.getKey(), null);
					TreeSet<String> ciudades = arbolillo.getCiudades(p);
					Iterator<String> iterator = ciudades.iterator();
					System.out.print(paises.getKey()+" "+"("+ciudades.size()+")"+": ");
					System.out.print(iterator.next());
					
						while(iterator.hasNext()) {
							System.out.print("- "+iterator.next());
						}
					System.out.println();
					
				}
				
			}else {
				PLoc p = new PLoc(null, args[1], null);
				TreeSet<String> ciudades = arbolillo.getCiudades(p);
				Iterator<String> iterator = ciudades.iterator();
				System.out.print(args[1]+" "+"("+ciudades.size()+")"+": ");
				System.out.print(iterator.next());
				
				while(iterator.hasNext()) {
						System.out.print("- "+iterator.next());
				}
				System.out.println();
			}
		}
	}
}
