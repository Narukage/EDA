
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

import ArbolG.NodoAG;

public class ArbolG extends Arbol {
	//Variables privadas
	private NodoAG pr;		//Nodo inicial
	
	//Constructor
	public ArbolG() {
		pr = null;
	}
	
	//Lee el fichero de texto pasado por parametro y lo inserta en el arbol
	public void leeArbol(String f) {
		if(f == null)
			return;
		
		//Variables iniciales
        FileReader     file_reader     = null; //Lector de fichero
        BufferedReader buffered_reader = null; //Memoria en buffer para leer linea por linea
        String 		   line            = null; //Cada linea que se lee del fichero
        
        try{
        	//Lee el fichero
        	file_reader     = new FileReader(f);
        	buffered_reader = new BufferedReader(file_reader);
            
        	//Leemos y comprobamos la primera linea y luego continuamos con do-while
        	line = buffered_reader.readLine();
        	if( line != null) {
	            do{
	            	//Dividimos la linea leida en funcion de sus #
	                String[] datos = line.split("#");
	                boolean continente = false; //Variable auxiliar
	                
	                if(datos.length !=5)
	                	break;
	                
	                //Comprobamos datos vacios
	                if(datos[0].equals("")){
	                	datos[0] = null;
	                }else{
	                	continente = true;
	                }
	                
	                if(datos[1].equals(""))
	                	datos[1] = null;
	                
	                if(datos[2].equals(""))
	                	datos[2] = null;
					
					//Si se le ha asgiando el continente, ponemos la variable auxiliar a true
					if(continente){
						a.changeState();
					}
					
					//Tratamos las coordenadas
                    String[] latitud=datos[3].split(" ");
                    String[] longitud=datos[4].split(" ");
                    
                    Coordenada latitud_coordenada  = new Coordenada(Integer.parseInt(latitud[0]),  Integer.parseInt(latitud[0]),  latitud[2].charAt(0));
	                Coordenada longitud_coordenada = new Coordenada(Integer.parseInt(longitud[0]), Integer.parseInt(longitud[1]), longitud[2].charAt(0));
	                
	                try {
                    	//Creamos el ploc a insertar
                    	PLoc dentro = new PLoc(datos[0], datos[1], datos[2]);
                    	
                    	//y le pasamos las coordenadas
                        dentro.setLatitud(latitud_coordenada);
                        dentro.setLongitud(longitud_coordenada);

                        //metemos el ploc en la lista
                        inserta(dentro);

                        //Imprimir algun error por coordenada erronea
                    }catch(CoordenadaExcepcion e){
                        System.out.println(e);
                    }
	                
	                //Leemos la linea para continuar la lectura
	                line=buffered_reader.readLine();
	            }while(line != null);
        	}
	            
        }catch(IOException c){
            c.printStackTrace();
        }
        
      //Despues de usarlos los cerramos
        try{
        	if(buffered_reader != null)
        		buffered_reader.close();
        	
            if(file_reader != null)
            	file_reader.close();
                
        }catch(IOException c){
            c.printStackTrace();
        }
	}
	
	//Si el nodo inicial del arbol esta vacio, entonces el arbol lo es
	public boolean esVacio() {
		if(pr==null){
			return true;
		}
		return false;
	}
	
	public boolean inserta (PLoc p) {
		//Comprobamos que el objeto que nos han pasado no es nulo
		if(p == null)
			return false;
		
			NodoAG nodoactual; //Nodo auxiliar para recorrer el arbol
			NodoAG minodo 		= new NodoAG(p); //Nodo a insertar
			boolean insertado 	= false;
			//Si el arbol es vacio, el Ploc pasara a ser nuestro nodo inicial
			if(esVacio()) {
				pr = minodo;
				return true;
			}else {
				nodoactual = pr; //Empezamos por el primer nodo
				
				do {
					if(minodo.getPD().getGps()[1] < nodoactual.getPD().getGps()[1]) { //SO o NO
						if(minodo.getPD().getGps()[0] < nodoactual.getPD().getGps()[0]){ //SO
							if(nodoactual.getSO()!=null) { //SO ocupado
								nodoactual=nodoactual.getSO();
							}else { //SO libre
								nodoactual.setSO(minodo);
								insertado=true;
								return true;
							}
						}else { //NO
							if(nodoactual.getNO()!=null) { //NO ocupado
								nodoactual=nodoactual.getNO();
							}else { //NO libre
								nodoactual.setNO(minodo);
								insertado=true;
								return true;
							}

						}
						
					}else {
						if(minodo.getPD().getGps()[0] < nodoactual.getPD().getGps()[0]){ //SE
							if(nodoactual.getSE()!=null) { //SE ocupado
								nodoactual=nodoactual.getSE();
							}else { //SE libre
								nodoactual.setSE(minodo);
								insertado=true;
								return true;
							}
						}else {
							if(nodoactual.getNE()!=null) { //NE ocupada
								nodoactual=nodoactual.getNE();
							}else { //NE libre
								nodoactual.setNE(minodo);
								insertado=true;
								return true;
							}
						}
					}
				}while(!insertado); //Mientras no se haya insertado el Nodo, continuamos recorriendo el arbol
			}
		return false;
	}
	
	//Recorrido inorden
	public boolean ciudadEnArbol(String v) {
		if(v == null)
			return false;
		
		if(pr == null)
			return false;
		
		boolean encontrada = false;
		NodoAG nodoactual = pr; //Empezamos el recorrido por el primer nodo
		Queue<NodoAG> cola = new LinkedList<NodoAG>(); //Cola auxiliar para recorrer el arbol

		cola.add(nodoactual); //Anyadimos el nodo a la cola
		
		//Mientras la cola no este vacia o no se haya encontrado la primera coincidencia
		while(!cola.isEmpty() && !encontrada) {
			
			nodoactual = cola.poll();
			
			if(nodoactual.getPD().getCiudad().equalsIgnoreCase(v)) {
				encontrada = true;
			}else {
				comprobarHijos(cola, nodoactual);
			}
		}
		return encontrada;
	}
	
	//NEW, metodo para comprobar los hijos de un nodo en todas direcciones
	public void comprobarHijos(Queue<NodoAG> cola, NodoAG nodoactual) {
		//Si no encuentra la ciudad en el nodo, mira todos los nodos hijos y los anyade a la cola
		if(nodoactual.getSE()!=null) {
			cola.add(nodoactual.getSE());
		}
		if(nodoactual.getNE()!=null) {
			cola.add(nodoactual.getNE());
		}
		if(nodoactual.getSO()!=null) {
			cola.add(nodoactual.getSO());
		}
		if(nodoactual.getNO()!=null) {
			cola.add(nodoactual.getNO());
		}
	}
	
	//Recorrido inorden
	public TreeSet<String> getCiudades(PLoc p){
		if(p == null)
			return false;
		
		if(pr == null)
			return false;
		
		TreeSet<String> t = new TreeSet<String>(); //TreeSet donde guardaremos las ciudades
		NodoAG nodoactual = pr; //Empezamos a recorrer el arbol desde el nodo raiz
		
		Queue<NodoAG> cola = new LinkedList<NodoAG>(); //Cola auxiliar
		cola.add(nodoactual);
		
		while(!cola.isEmpty()) {
			nodoactual = cola.poll();
			//Si encuentra  el nodo con el ploc que tenga el mismo pais que el del ploc pasado por parametro, lo metemos en el treeset
			if(nodoactual.getPD().getPais().equalsIgnoreCase(p.getPais())) {
				t.add(nodoactual.getPD().getCiudad());
			}else {
				comprobarHijos(cola, nodoactual);
			}
		}
		return t;
	}
	
	public PLoc busquedaLejana(String s) {
		if(s==null || s.equals(""))
			return false;
		
		if(pr == null)
			return false;
		
		PLoc p = null;
		NodoAG nodoactual = pr;
		NodoAG nodofinal = pr;
		Queue<NodoAG> cola = new LinkedList<NodoAG>(); //Cola auxiliar
		
		//Busqueda euclidea
		cola.add(nodoactual);
		
		while(!cola.isEmpty()) {
			nodoactual = cola.poll();
				//cogemos la ciudad de la cola
				nodoactual = cola.poll();
				float latitudActual = nodoactual.getPD().getGps()[1];
				float longitudActual = nodoactual.getPD().getGps()[0];
				float latitudFinal = nodofinal.getPD().getGps()[1];
				float longitudFinal = nodofinal.getPD().getGps()[0];
				
				switch(s) {
				case "NO":
						//en NO buscamos el de mayor latitud y menor longitud
						if(latitudFinal > latitudActual) {
							if(longitudFinal < longitudActual) {
								nodofinal = nodoactual;
							}
						}
					break;
					
				case "SO":
						//en SO buscamos el de menor latitud y menor longitud
						if(latitudFinal > latitudActual) {
							if(longitudFinal > longitudActual) {
								nodofinal = nodoactual;
							}
						}
					break;
					
				case "NE":
						//en NE buscamos el de mayor latitud y mayor longitud
						if(latitudFinal < latitudActual) {
							if(longitudFinal < longitudActual) {
								nodofinal = nodoactual;
							}
						}
					break;
					
				case "SE":
						//en NO buscamos el de menor latitud y mayor longitud
						if(latitudFinal < latitudActual) {
							if(longitudFinal > longitudActual) {
								nodofinal = nodoactual;
							}
						}
					break;
				}
				
				comprobarHijos(cola, nodoactual);
		}
		
		p = nodofinal.getPD();
		
		if(p == null && !esVacio()) {
			p = pr.getPD();
		}
		return p;
	}
	
	public void recorridoInorden() {
		if(esVacio())
			return false;
		
		NodoAG nodoactual = pr;
		
		InOrden(nodoactual); //Metodo recursivo
	}
	
	//NEW, metodo recursivo para el recorrido inorden de un arbol
	public void InOrden(NodoAG nodo) {
		/*if(nodo == null)
			break;*/
		if(nodo!=null) {
			//Subarbol izqdo
			if(nodo.getNO()!=null) { 
				InOrden(nodo.getNO());
			}
			if(nodo.getSO()!=null) {
				InOrden(nodo.getSO());
			}
			
			System.out.println(nodo.getPD().getCiudad()); //Raiz
			
			//Subarbol dcho
			if(nodo.getNE()!=null) { 
				InOrden(nodo.getNE());
			}
			if(nodo.getSE()!=null) {
				InOrden(nodo.getSE());
			}
		}
	}
	
	public void recorridoNiveles() {
		if(pr == null)
			return false;
		
		NodoAG nodoactual = pr; //Nodo inicial
		Queue<NodoAG> cola = new LinkedList<NodoAG>(); //Cola FIFO auxiliar
		
		cola.add(nodoactual);
		
		while(!cola.isEmpty()) {
			nodoactual = cola.poll();
			//Imprimimos la ciudad
			System.out.println(nodoactual.getPD().getCiudad());
			comprobarHijosFIFO(cola, nodoactual);
		}
	}
	
	//NEW, metodo para comprobar los hijos de un nodo en todas direcciones (Cambia el orden de visita de los hijos)
	public void comprobarHijosFIFO(Queue<NodoAG> cola, NodoAG nodo) {
		//Comprobamos si tiene hijos en todas sus direcciones, y si los tiene, los encolamos
		if(nodoactual.getNO()!=null) { 
			cola.add(nodoactual.getNO()); //NO
		}
		if(nodoactual.getSO()!=null) {
			cola.add(nodoactual.getSO()); //SO
		}
		if(nodoactual.getNE()!=null) {
			cola.add(nodoactual.getNE()); //NE
		}
		if(nodoactual.getSE()!=null) {
			cola.add(nodoactual.getSE()); //SE
		}
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		//Clase privada NodoAG
	private class NodoAG{
		//Variables privadas del nodo
		private PLoc pd;
		private NodoAG so;
		private NodoAG no;
		private NodoAG se;
		private NodoAG ne;
		
		//Constructor
		public NodoAG() {
			no = null;
			pd = null;
			se = null;
			ne = null;
			so = null;
		}
		
		//Constructor
		public NodoAG(PLoc p) {
			if(p!=null) {
				no = null;
				pd = p;
				se = null;
				ne = null;
				so = null;
			}
		}
		
		//Set y Get
		public void setNO(NodoAG nodo) 	{ no = nodo;	}
		public void setSO(NodoAG nodo) 	{ so = nodo;	}
		public void setNE(NodoAG nodo) 	{ ne = nodo;	}
		public void setSE(NodoAG nodo) 	{ se = nodo;	}
		public NodoAG getNO() 			{ return no;	}
		public NodoAG getSO() 			{ return so;	}
		public NodoAG getNE() 			{ return ne;	}
		public NodoAG getSE() 			{ return se;	}
		public PLoc getPD() 			{ return pd;	}
	}
}