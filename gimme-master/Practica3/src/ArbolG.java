//DNI 48620792B BARBA ROBLES, ALBERTO
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class ArbolG extends Arbol {
	
	//Clase hija NodoAG
	private class NodoAG{
		private PLoc pd;
		private NodoAG no;
		private NodoAG so;
		private NodoAG ne;
		private NodoAG se;
		
		public NodoAG() {
			pd = null;
			no = null;
			so = null;
			ne = null;
			se = null;
		}
		
		public NodoAG(PLoc p) {
			if(p!=null) {
				pd = p;
				no = null;
				so = null;
				ne = null;
				se = null;
			}
		}
		
		//SETTERS Y GETTERS
		//NEW
		public void setNO(NodoAG nodo) {
			no = nodo;
		}
		//NEW
		public void setSO(NodoAG nodo) {
			so = nodo;
		}
		//NEW
		public void setNE(NodoAG nodo) {
			ne = nodo;
		}
		//NEW
		public void setSE(NodoAG nodo) {
			se = nodo;
		}
		//NEW
		public NodoAG getNO() {
			return no;
		}
		//NEW
		public NodoAG getSO() {
			return so;
		}
		//NEW
		public NodoAG getNE() {
			return ne;
		}
		//NEW
		public NodoAG getSE() {
			return se;
		}
		public PLoc getPD() {
			return pd;
		}
	}
	
	private NodoAG pr;		//Nodo inicial
	
	public ArbolG() {		//Constructor
		pr = null;
	}
	
	//Lee el fichero por texto pasado por parametro y lo inserta en el arbol
	public void leeArbol(String f) {
		if(f != null){
			//FileReader para leer el documento que me pasan por parametro
			FileReader fil=null;
			//BufferedReader para leer por lineas el documento
			BufferedReader bu=null;
			String linea=null;
			//try para lanzar las funciones que puedan lanzar excepciones
			try{
				//le paso f para empezar a leer el fichero
				fil=new FileReader(f);
				//para empezar a leer linea por linea copiando el documento entero en la memoria local
				bu=new BufferedReader(fil);
				//leo la primera linea del buffer e ira saltando de lina con el bucle
				linea=bu.readLine();
				while(linea != null){
					//mi array de datos de la linea separados por #
					String[] info=linea.split("#");
					if(info.length==5){
						boolean teniaContinente=false;
						String conti=null;
						String pais=null;
						String localidad=null;
						//compruebo que la primera posicion de info sea distinta de null o vacio
						if(info[0]!=null && !info[0].equals("")){
							conti=info[0];
							teniaContinente=true;
						}
						//compruebo que la segunda posicion de info sea distinta de null o vacio
						if(info[1]!=null && !info[1].equals("")){
							pais=info[1];
						}
						//compruebo que la tercera posicion de info sea distinta de null o vacio
						if(info[2]!=null && !info[2].equals("")){
							localidad=info[2];
						}
						//creo mi PLoc a pasandole los datos por parametro
						PLoc a=new PLoc(conti,pais,localidad);
						//Si se le ha asgiando el continente, ponemos la variable auxiliar a true
						if(teniaContinente){
							a.changeState();
						}
						//para separar en info en la posicion 3 para lati con un espacio
						String[] lati=info[3].split(" ");
						//para separar en info en la posicion 4 para longi con un espacio
						String[] longi=info[4].split(" ");
						//transformo los datos numericos de Strings a enteros para crear mis coordenadas
						int latiGrados=Integer.parseInt(lati[0]);
						int latiMinutos=Integer.parseInt(lati[1]);
						int longiGrados=Integer.parseInt(longi[0]);
						int longiMinutos=Integer.parseInt(longi[1]);
						//paso de String a char
						char letralati=lati[2].charAt(0);
						char letralongi=longi[2].charAt(0);
						//creo mis coordenadas
						Coordenada miCoorde = new Coordenada(latiGrados,latiMinutos,letralati);
						Coordenada miCoorde2 = new Coordenada(longiGrados,longiMinutos,letralongi);
						try{
							//le paso las Coordenadas a mi PLOC
							a.setLatitud(miCoorde);
							a.setLongitud(miCoorde2);
							//lo inserto con el metodo inserta
							inserta(a);						
						}catch(CoordenadaExcepcion error){
							//muestro el error
							System.out.println(error);
						}
					}
					//para forzar que lea la linea por cada pasada del bucle
					linea=bu.readLine();
				}
			}catch(IOException o){
				o.printStackTrace();
			}
			try{
				//cierro el FileReader				
				if(fil != null){
					fil.close();
				}
				//cierro el BufferedReader
				if(bu != null){
					bu.close();
				}
			}catch(IOException c){
				c.printStackTrace();
			}
		}
	}
	
	//Metodo que indica si el arbol esta vacio
	public boolean esVacio() {
		boolean vacio=false;
		if(pr==null) {
			vacio=true;
		}else {
			vacio=false;
		}
		return vacio;
	}
	
	//Inserta el PLoc en el arbol
	public boolean inserta(PLoc p) {
		//booleano que comprueba si el ploc ha sido insertado
		boolean insertado=false;
		//Nodo auxiliar
		NodoAG nodoactual;
		//si el objeto no es nulo
		if(p!=null) {
			//Creamos el nodo
			NodoAG minodo = new NodoAG(p);
			//Si el arbol es vacio lo insertamos en la primera posicion
			if(esVacio()) {
				pr = minodo;
				insertado = true;
			}else{
				//sino el nodo actual es
				nodoactual = pr;
				//Mientras no este insertado, vamos iterando
				while(!insertado) {
					//Si minodo.longitud < nodoactual.longitud, es so o no
					if(minodo.getPD().getGps()[1] < nodoactual.getPD().getGps()[1]) { //so o no
						//si la latitud de minodo es menor, es so
						if(minodo.getPD().getGps()[0] < nodoactual.getPD().getGps()[0]){
							if(nodoactual.getSO()!=null) { //so ya esta ocupada por otro nodo
								nodoactual=nodoactual.getSO();
							}else { //so no esta ocupada = actualizamos con minodo y pasa a estar insertada
								nodoactual.setSO(minodo);
								insertado=true;
							}
						//si la latitud de mi nodo es superior, es no
						}else { //no
							if(nodoactual.getNO()!=null) { //no ocupada
								nodoactual=nodoactual.getNO();
							}else { //no no ocupada  = actualizamos con minodo y pasa a estar insertada
								nodoactual.setNO(minodo);
								insertado=true;
							}
						}
					//Si minodo.longitud > nodoactual.longitud, es se o ne
					}else{
						//si la latitud de minodo es menor, es se
						if(minodo.getPD().getGps()[0] < nodoactual.getPD().getGps()[0]){ //se
							if(nodoactual.getSE()!=null) { //se ocupada
								nodoactual=nodoactual.getSE();
							}else { //se no ocupada = actualizamos con minodo y pasa a estar insertada
								nodoactual.setSE(minodo);
								insertado=true;
							}
						//si la latitud de minodo es mayor, es ne
						}else {
							if(nodoactual.getNE()!=null) { //ne ocupada
								nodoactual=nodoactual.getNE();
							}else { //ne no ocupada = actualizamos con minodo y pasa a estar insertada
								nodoactual.setNE(minodo);
								insertado=true;
							}
						}
					}
				}
			}
		}
		return insertado;
	}
	
	//Devuelve true si encuentra al menos una ciudad  cuyo nombre coincida con el string, y realizamos un recorrido inorden
	public boolean ciudadEnArbol(String v) {
		//nuestro nodo actual en principio es la raiz
		NodoAG nodoactual = pr;
		//booleano que controla la ciudad encontrada
		boolean encontrada = false;
		//Cola provisional donde guardamos los nodos hijos siguientes y con la que vamos iterando los nodos
		Queue<NodoAG> cola = new LinkedList<NodoAG>();
		if(v != null) {
			//mientras el nodo actual no sea nulo (el del inicio)
			if(nodoactual!=null) {
				//lo anyadimos a la cola
				cola.add(nodoactual);
				//Mientras la cola no este vacia o no se haya encontrado la primera coincidencia
				while(!cola.isEmpty() && !encontrada) {
					nodoactual = cola.poll();
					//Si encuentra la ciudad, devuelve true
					if(nodoactual.getPD().getCiudad().equalsIgnoreCase(v)) {
						encontrada = true;
					}else {
						//Si no la encuentra, mira todos los nodos derivados y los anyade a la cola
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
				}
			}	
		}
		return encontrada;
	}
	
	//Devuelve en el treeset las ciudades que tengan el mismo pais que el ploc pasado por parametro
	public TreeSet<String> getCiudades(PLoc p){
		//Inicializamos la variable de retorno
		TreeSet<String> t = null;
		//nuestro nodo actual en principio es la raiz
		NodoAG nodoactual = pr;
		//si no es null
		if(p != null) {
			t = new TreeSet<String>();
			//Cola provisional donde guardamos los nodos hijos siguientes
			Queue<NodoAG> cola = new LinkedList<NodoAG>();
			//mientras el nodo actual no sea nulo (el del inicio)
			if(nodoactual!=null) {
				//lo anyadimos a la cola
				cola.add(nodoactual);
				//Mientras la cola no este vacia, la recorremos entera
				while(!cola.isEmpty()) {
					//Cogemos el nodo que antes se haya insertado
					nodoactual = cola.poll();
					//Si encuentra  el nodo con el ploc que tenga el mismo pais que el del ploc pasado por parametro, lo metemos en el treeset
					if(nodoactual.getPD().getPais().equalsIgnoreCase(p.getPais())) {
						t.add(nodoactual.getPD().getCiudad());
					}else {
						//Si no la encuentra, mira todos los nodos derivados y los anyade a la cola
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
				}
			}
		}
		return t;
	}
	
	//Devuelve la ciudad mas lejana en la direccion pasada por parametro
	public PLoc busquedaLejana(String s) {
		//PLoc resultado
		PLoc p = null;
		//Nodo auxiliar
		NodoAG nodoactual = pr;
		NodoAG nodofinal = pr;
		//Cola provisional donde guardamos los nodos hijos siguientes
		Queue<NodoAG> cola = new LinkedList<NodoAG>();
		//Miramos el string
		if(s!=null && !s.equals("")) {
			//Busqueda euclidea
			//mientras el nodo actual no sea nulo (el del inicio)
			if(nodoactual!=null) {
				//lo anyadimos a la cola
				cola.add(nodoactual);
				//Mientras la cola no este vacia o no se haya encontrado la primera coincidencia
				while(!cola.isEmpty()) {
					//cogemos la ciudad de la cola
					nodoactual = cola.poll();
					
					//Comprobamos el valor de longitud y latitud de la ciudad para ver si es el mas lejano en la direccion pedida
					//Usamos un switch
					switch(s) {
					case "NO":
							//en NO buscamos el de mayor latitud y menor longitud
							if(nodofinal.getPD().getGps()[1] > nodoactual.getPD().getGps()[1]) {
								if(nodofinal.getPD().getGps()[0] < nodoactual.getPD().getGps()[0]) {
									nodofinal = nodoactual;
								}
							}
						break;
						
					case "SO":
							//en SO buscamos el de menor latitud y menor longitud
							if(nodofinal.getPD().getGps()[1] > nodoactual.getPD().getGps()[1]) {
								if(nodofinal.getPD().getGps()[0] > nodoactual.getPD().getGps()[0]) {
									nodofinal = nodoactual;
								}
							}
						break;
						
					case "NE":
							//en NE buscamos el de mayor latitud y mayor longitud
							if(nodofinal.getPD().getGps()[1] < nodoactual.getPD().getGps()[1]) {
								if(nodofinal.getPD().getGps()[0] < nodoactual.getPD().getGps()[0]) {
									nodofinal = nodoactual;
								}
							}
						break;
						
					case "SE":
							//en NO buscamos el de menor latitud y mayor longitud
							if(nodofinal.getPD().getGps()[1] < nodoactual.getPD().getGps()[1]) {
								if(nodofinal.getPD().getGps()[0] > nodoactual.getPD().getGps()[0]) {
									nodofinal = nodoactual;
								}
							}
						break;
					}
					
					//Metemos sus nodos derivados en la cola para posteriores comprobaciones
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
				//al final tendra que estar el mas lejano en la direccion indicada
				p = nodofinal.getPD();
			}
		}
		if(p == null && !esVacio()) {
			p = pr.getPD();
		}
		return p;
	}
	
	//Escribe el arbol siguiendo el recorrido inorden
	public void recorridoInorden() {
		NodoAG nodoactual = pr;
		//Si el arbol no es vacio, recorrido inorden recursivo
		if(!esVacio()) {
			//funcion recursiva
			InOrden(nodoactual);
		}
	}
	
	//Escribe el arbol siguiendo el recorrido por niveles usando una cola. Los que entran primero anyaden sus hijos a las colas
	//por lo que imprimiran sus nombres luego
	public void recorridoNiveles() {
		//Nodo actual inicial
		NodoAG nodoactual = pr;
		Queue<NodoAG> cola = new LinkedList<NodoAG>(); //cola FIFO: primero que entra, primero que sale. Para el recorrido por niveles.
		//Si el nodo no es nulo
		if(nodoactual!=null) {
			//comenzamos desde la raiz del arbol
			cola.add(nodoactual);
			//mientras la cola siga teniendo elementos
			while(!cola.isEmpty()) {
				//Sacamos un elemento
				nodoactual = cola.poll();
				//imprimimos la ciudad
				System.out.println(nodoactual.getPD().getCiudad());
				
				//comprobamos si tiene hijos en todas sus direcciones, y si los tiene, los encolamos
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
		}
	}
	
	//NEW: metodo recursivo para poder cambiar de nodo
	public void InOrden(NodoAG nodo) {
		//Si el nodo no es nulo
		if(nodo!=null) {
			//subarbol izqdo
			if(nodo.getNO()!=null) { 
				InOrden(nodo.getNO());
			}
			if(nodo.getSO()!=null) {
				InOrden(nodo.getSO());
			}
			
			System.out.println(nodo.getPD().getCiudad()); //raiz
			
			//subarbol dcho
			if(nodo.getNE()!=null) { 
				InOrden(nodo.getNE());
			}
			if(nodo.getSE()!=null) {
				InOrden(nodo.getSE());
			}
		}	
	}
}
