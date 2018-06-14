//DNI MIGUEL HERMIDA CORES
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

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
					
	                PLoc a=new PLoc(datos[0],datos[1],datos[2]);
	                
					if(continente){
						a.set_no_tenia_continente(false);
					}
					
					//Tratamos las coordenadas
                    String[] latitud=datos[3].split(" ");
                    String[] longitud=datos[4].split(" ");
                    
                    Coordenada latitud_coordenada  = new Coordenada(Integer.parseInt(latitud[0]),  Integer.parseInt(latitud[1]),  latitud[2].charAt(0));
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
		if(pr==null)
			return true;
		
		return false;
	}
	
	public boolean inserta (PLoc p) {
		//Comprobamos que el objeto que nos han pasado no es nulo
		if(p == null)
			return false;
		
			NodoAG nodoactual	= null; //Nodo auxiliar para recorrer el arbol
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
		if(nodoactual.getNO()!=null) {
			cola.add(nodoactual.getNO());
		}
		if(nodoactual.getNE()!=null) {
			cola.add(nodoactual.getNE());
		}
		if(nodoactual.getSO()!=null) {
			cola.add(nodoactual.getSO());
		}
		if(nodoactual.getSE()!=null) {
			cola.add(nodoactual.getSE());
		}	
	}
	
	@Override
	public TreeSet<String> getCiudades(PLoc p){
		if(p == null)
			return null;
		
		if(pr == null)
			return null;
		
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
	
	public TreeSet<PLoc> getCiudadesPLoc(){
		if(pr == null)
			return null;
		
		TreeSet<PLoc> ciudades 	= new TreeSet<PLoc>(); //TreeSet donde guardaremos las ciudades
		NodoAG nodoactual		= pr; ////Empezamos a recorrer el arbol desde el nodo raiz
		Queue<NodoAG> cola 		= new LinkedList<NodoAG>(); //Cola auxiliar
		
		cola.add(nodoactual);
		ciudades.add(nodoactual.pd);
		
		while(!cola.isEmpty()) {
			nodoactual = cola.poll();
			ciudades.add(nodoactual.getPD());		
			comprobarHijos2(cola, nodoactual);
		}
		return ciudades;
	}
	
	public void comprobarHijos2(Queue<NodoAG> cola, NodoAG nodoactual) {
		//Comprobamos los hijos
		if(nodoactual.getNO()!=null) { 
			cola.add(nodoactual.getNO()); //NO
		}
		if(nodoactual.getNE()!=null) {
			cola.add(nodoactual.getNE()); //NE
		}
		if(nodoactual.getSE()!=null) {
			cola.add(nodoactual.getSE()); //SE
		}
		if(nodoactual.getSO()!=null) {
			cola.add(nodoactual.getSO()); //SO
		}
	}
	
	public double[] Distancias(PLoc auxiliar,PLoc bucle, String s){
		double[] aux = new double[2];		
		double Dist1 = 0.0;
		double Dist2 = 0.0;
		
			switch(s) {
				
				case "NO":
							aux[0] = 90;
							aux[1] = -180;
							Dist1 = DistanciaEuclidea(auxiliar, aux);
							Dist2 = DistanciaEuclidea(bucle, aux);;
							break;
				case "SO":
							aux[0] = -90;
							aux[1] = -180;
							Dist1 = DistanciaEuclidea(auxiliar, aux);
							Dist2 = DistanciaEuclidea(bucle, aux);
							break;
				case "SE":
							aux[0] = -90;
							aux[1] = 180;
							Dist1 = DistanciaEuclidea(auxiliar, aux);
							Dist2 = DistanciaEuclidea(bucle, aux);
							break;
				case "NE":
							aux[0] = 90;
							aux[1] = 180;
							Dist1 = DistanciaEuclidea(auxiliar, aux);
							Dist2 = DistanciaEuclidea(bucle, aux);
							break;
			}
				
				double[] respuesta={Dist1, Dist2};
				return respuesta;
			}
	
	public double DistanciaEuclidea(PLoc aux1, double[] Array3){
						
			if (aux1 == null)
				return 0.0;
				
				double DistEuclidea = 0.0;
					
				double[] Array1 = aux1.getGps();
				double restaAux = Array1[0] - Array3[0];
				double resta2Aux = Array1[1] - Array3[1];
					
				DistEuclidea = Math.sqrt(Math.pow(restaAux, 2) + Math.pow(resta2Aux, 2));
				
			return DistEuclidea;
				
		}
	
	public PLoc busquedaLejana(String s) {
		if(s==null || s.equals(""))
			return null;
		
		if(pr == null)
			return null;
		
		NodoAG nodoactual 	= pr;
		PLoc resultado		= null;
		
		resultado = busqueda(s, pr);
		
		return resultado;
		}
	
	public PLoc busqueda(String s, NodoAG nodoactual) {
		PLoc resultado		= null;
		PLoc auxiliar		= null;
		double[] res		= null;
		
		if(nodoactual != null) {		
			resultado = nodoactual.pd;
			auxiliar = busqueda(s, nodoactual.no);
			res = llamada(res, auxiliar, resultado, s);
			auxiliar = busqueda(s, nodoactual.ne);
			res = llamada(res, auxiliar, resultado, s);
			auxiliar = busqueda(s, nodoactual.so);
			res = llamada(res, auxiliar, resultado, s);
			auxiliar = busqueda(s, nodoactual.se);
			res = llamada(res, auxiliar, resultado, s);
			
			if(res[0] <= res[1]) {
				resultado = auxiliar;
			}
		}
		
		return resultado;
	}
	
	public double[] llamada(double[] res, PLoc auxiliar, PLoc resultado, String s) {
		res = Distancias(auxiliar, resultado, s);
		return res;
	}

	
	public void recorridoInorden() {
		if(!esVacio()) {
			
			NodoAG nodoactual = pr;
		
			InOrden(nodoactual); //Metodo recursivo recorrido InOrden
		}
	}
	
	//NEW, metodo recursivo para el recorrido inorden de un arbol
	public void InOrden(NodoAG nodo) {
		if(nodo!=null) {
			//Subarbol izquierdo
			if(nodo.getNO()!=null) { 
				InOrden(nodo.getNO());
			}
			if(nodo.getSO()!=null) {
				InOrden(nodo.getSO());
			}
			
			System.out.println(nodo.getPD().getCiudad()); //Nodo Raiz
			
			//Subarbol derecho
			if(nodo.getNE()!=null) { 
				InOrden(nodo.getNE());
			}
			if(nodo.getSE()!=null) {
				InOrden(nodo.getSE());
			}
		}
	}
	
	public void recorridoNiveles() {
		if(pr != null) {
		
			NodoAG nodoactual	= pr; //Nodo inicial
			Queue<NodoAG> cola 	= new LinkedList<NodoAG>(); //Cola FIFO auxiliar
			
			cola.add(nodoactual);
			
			while(!cola.isEmpty()) {		
				nodoactual = cola.poll();
				
				if(nodoactual.pd.getCiudad() == null) {
					System.out.println("x");
				}else {
					System.out.println(nodoactual.getPD().getCiudad());
				}
				comprobarHijosFIFO(cola, nodoactual);
			}
		}
	}
	
	//NEW, metodo para comprobar los hijos de un nodo en todas direcciones (Cambia el orden de visita de los hijos)
	public void comprobarHijosFIFO(Queue<NodoAG> cola, NodoAG nodoactual) {
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
