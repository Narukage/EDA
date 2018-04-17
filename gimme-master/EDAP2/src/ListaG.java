//DNI 48620792B BARBA ROBLES, ALBERTO
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ListaG implements Lista{
	//clase privada de la clase ListaG con sus variables de instancia
	private class NodoLG{
		private PLoc pd;
		private NodoLG next;

		//NEW auxiliares para la aplicacion
		private PLoc cercano;
		double distancia;
		//constructor que inicializa las varibales a sus valores por defecto
		public NodoLG(){
			pd=null;
			next=null;
			cercano = null;
			distancia = Double.MAX_VALUE;
		}
		//constructor que inicializa el nodo con el objeto de tipo PLoc y el resto por defecto
		public NodoLG(PLoc p){
			pd=p;
			next=null;
			cercano = null;
			distancia = Double.MAX_VALUE;
		}

		//NEW metodo que asigna el siguiente nodo a este en la lista enlazada
		public void next(NodoLG n){
			next = n;
		}

		//NEW metodo que devuelve el siguiente nodo
		public NodoLG getNext(){
			return next;
		}


		//NEW metodo que devuelve el ploc asignado en este nodo
		public PLoc getPLoc(){
			return pd;
		}

		//NEW metodo que cambia el ploc
		public void ploc(PLoc p){
			pd = p;
		}

		//NEW metodo que da valor al nodo cercano
		public void setCercano(PLoc p, double d){
			cercano = p;
			distancia = d;
		}

		//NEW metodo que devuelve el PLoc mas cercano
		public PLoc getCercano(){
			return cercano;
		}

		//NEW metodo devuelve la distancia al PLoc mas cercano actualmente
		public double getDistancia(){
			return distancia;
		}
	}
	//variable de instancia de ListaG
	private NodoLG pr;
	//constructor que inicializa las varibales de instancia a sus valores por defecto
	public ListaG(){
		pr=null;
	}
	//meotod que escibe la lista empezando por la cabeza
	public void escribeListaG(){
		//Contador del nodo actual
		int pos=0;
		//si hay nodos
		if(pr!=null){
			//inicializamos las variables del buscador
			NodoLG actual=pr;
			//while para recorrer la lista
			while(actual!=null){
				//imprimimos el formato
				System.out.print("nodo "+pos+": ");
				//Usamos el metodo de PLoc para imprimir el mensaje
				actual.getPLoc().escribeInfoGps();

				//Pasamos al siguiente nodo
				pos++;
				actual=actual.getNext();
			}
		}
	}
	//metodo para leer el fichero 
	public void leeLista(String f){
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
							//convierto este PLoc generado en la nueva cabeza, y el otro que habia pasa a ser su siguiente y
							//creo el nodo que contiene al ploc
							NodoLG nodo = new NodoLG(a);
							//lo convierto en cabeza y el nodo siguiente pasa a ser el que habia antes
							nodo.next(pr);
							pr = nodo;
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
	//si la cabeza es vacia, entonces toda la lista lo es 
	//0(1)
	public boolean esVacia(){		

		if(pr==null){
			return true;
		}
		return false;
	}
	//para insertar un objeto en la cabeza
	//0(1)
	public void insertaCabeza(PLoc p){
		//si no es nulo el Ploc
		if(p!=null){
			//creamos un nodo y lo hacemos cabeza
			NodoLG nodo = new NodoLG(p);
			//su nodo siguiente sera el que antes era cabeza
			nodo.next(pr);
			pr = nodo;
		}
	}
	//metodo para insertar en la cola
	//0(n)
	public void insertaCola(PLoc v){
		//comprobamos que no es nulo el ploc
		if(v!=null){
			//creamos el nodo a insertar
			NodoLG nodo = new NodoLG(v);

			//si la cabeza es nula, sera esta nuestra nueva cabeza, y sino buscamos el ultimo nodo para insertarlo
			if(pr==null){
				pr = nodo;
			}else{
				//el primero sera la cabeza
				NodoLG actual = pr;
				NodoLG next=pr.getNext();

				//bucle while hasta que el siguiente nodo sea nulo (significa llegar al final de la lista)
				while(next!=null){
					actual = next;
					next = actual.getNext();
				}
				//en todo caso, el bucle while recorre la lista hasta llegar al final. En cuyo caso, esto siempre 
				//anyade el nodo a la ultima posicion
				actual.next(nodo);
			}
		}
	}
	//metodo que anyade al final de la lista los objetos pasados por parametro de tipo Ploc
	//0(n)
	public void insertaArrayPLoc(PLoc[] v){
		//comprobamos que no es nulo el ploc
		if(v!=null && v.length>0){
			//si la cabeza es nula, sera esta nuestra nueva cabeza, y sino buscamos el ultimo nodo para insertarlo
			if(pr==null){
				//creamos el nodo a insertar
				NodoLG nodo = new NodoLG(v[0]);
				//lo inserto como cabeza
				pr = nodo;
				//metemos el resto de ellos siguiendo el bucle
				for(int i = 1;i<v.length;i++){
					//creamos el nodo siguiente
					NodoLG nodoNext = new NodoLG(v[i]);
					//lo anyadimos como el siguiente
					nodo.next(nodoNext);
					//ahora el nodo actual sera el nodo siguiente a este
					nodo = nodoNext;
				}
			}else{
				//el primero sera la cabeza
				NodoLG actual = pr;
				NodoLG next=pr.getNext();
				//bucle while hasta que el siguiente nodo sea nulo (significa llegar al final de la lista)
				while(next!=null){
					actual = next;
					next = actual.getNext();
				}
				//en todo caso, el bucle while recorre la lista hasta llegar al final. En cuyo caso, esto siempre 
				//anyade el nodo a la ultima posicion

				//metemos el total de ellos siguiendo el bucle
				for(int i = 0;i<v.length;i++){
					//creamos el nodo siguiente
					NodoLG nodoNext = new NodoLG(v[i]);
					//lo anyadimos como el siguiente
					actual.next(nodoNext);
					//ahora el nodo actual sera el nodo siguiente a este
					actual = nodoNext;
				}
			}
		}
	}
	//metodo que borra el Ploc de la cabeza
	//0(1)
	public boolean borraCabeza(){
		//si hay cabeza
		if(pr!=null){
			//cogemos el siguiente nodo al primero
			NodoLG aux = pr.getNext();
			//ahora el siguiente es la cabeza
			pr = aux;
			return true;
		}
		return false;
	}
	//metodo que borra el ultimo objeto Ploc de la cola de la lista
	//0(n)
	public boolean borraCola(){
		//si hay lista 
		if(pr != null){
			//el primero sera la cabeza
			NodoLG actual = pr;
			NodoLG next=pr.getNext();
			//si next es nulo, significa que solo hay un nodo en todo el vector, que es el que se borra
			if(next!=null){
				//bucle while hasta que el siguiente nodo sea nulo (significa llegar al final de la lista)
				while(next.getNext()!=null){
					actual = next;
					next = next.getNext();
				}
				//cuando nextNext es null, significa que next es el ultimo, y que es el que tenemos que borrar
				actual.next(null);
			}else{
				pr = null;
			}
			return true;
		}
		return false;
	}
	//metodo que devuelve la posicion del primer Ploc con la ciudad que coincida con v
	//0(n)
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion{
		//Contador de posicion
		int pos = 0;
		//si hay lista y v no es nulo
		if(pr!=null){
			//tenemos el nodo actual y el siguiente para ir moviendonos por la lista
			NodoLG actual = pr;
			//Seguimos iterando hasta que no lleguemos al final
			while(actual!=null){
				//evaluo la condicion de nuevo con el nodo actual, por lo tanto, si es positivo se devuelve la posicion
				if(actual.getPLoc().getCiudad()==v || (actual.getPLoc().getCiudad()!=null && actual.getPLoc().getCiudad().equalsIgnoreCase(v))){
					return pos;
				}
				//aumentamos la posicion previamente
				actual = actual.getNext();
				pos++;
			}
			//llegar aqui implica que la ciudad no estaba dentro, por lo tanto se lanza la excepcion
			throw new CiudadNoEncontradaExcepcion(v);
		}else{
			throw new CiudadNoEncontradaExcepcion(v);
		}
	}
	//metodo que elimina el primer PLoc que contiene una ciudad de la lista que coincida con v
	//0(n)
	public boolean borraCiudad(String v){
		//comprobaciones pertinentes
		if(pr!=null){
			//auxiliares
			NodoLG actual = pr;
			NodoLG next = pr.getNext();
			//compruebo el primer nodo de todos
			if(pr.getPLoc().getCiudad()==v || (pr.getPLoc().getCiudad()!=null && pr.getPLoc().getCiudad().equalsIgnoreCase(v))) {
				pr = pr.getNext();
				return true;
			}
			//sino, procedo a realizar las iteraciones con los siguientes nodos
			while(next!=null){
				//Si el nodo siguiente, tiene dicha ciudad, al nodo actual se le asigna como siguiente, al siguiente del siguiente (aunque sea null)
				if(next.getPLoc().getCiudad()==v || (next.getPLoc().getCiudad()!=null && next.getPLoc().getCiudad().equalsIgnoreCase(v))){
					actual.next(next.getNext());
					return true;
				}
				//nos movemos una posicion a la derecha
				actual = next;
				next = next.getNext();
			}
		}
		return false;
	}
	//metodo que borra de la lista todos los objetos de tipo PLoc que coincidan con el nombre pasado por parametro
	//0(n)
	public boolean borraPais(String s){
		//variables auxiliares
		boolean encontrado=false;
		//comprobaciones pertinentes
		if(pr!=null){
			//auxiliares
			NodoLG actual = pr;
			//itero con los siguientes nodos
			while(actual!=null){
				//Si el nodo siguiente, tiene dicho pais, al nodo actual se le asigna como siguiente, al siguiente del siguiente (aunque sea null)
				if(actual.getNext()!=null && (actual.getNext().getPLoc().getPais()==s || (actual.getNext().getPLoc().getPais()!=null && actual.getNext().getPLoc().getPais().equalsIgnoreCase(s)))){
					actual.next(actual.getNext().getNext());
					encontrado = true;
				}else{
					//me muevo una posicion a la derecha
					actual = actual.getNext();
				}
			}
			//compruebo al final el primer nodo de todos
			if(pr.getPLoc().getPais()==s || (pr.getPLoc().getPais()!=null && pr.getPLoc().getPais().equalsIgnoreCase(s))) {
				pr = pr.getNext();

			}
			//finalizo la ejecucion devolviendo true si ha borrado al menos 1 ploc
			return encontrado;
		}
		return false;
	}
	//metodo que devuelve el objeto PLoc contenido en la lista que ocupa la posicion pos
	//0(n)
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException{
		PLoc res = null;
		//contador auxiliar para atravesar la lista
		int aux = 0;
		//si la posicion pasada es negativa, empleo el throw
		if(pos < 0){
			throw new IndexOutOfBoundsException(Integer.toString(pos));
		}else{
			//si hay lista
			if(pr != null){
				NodoLG actual = pr;
				NodoLG next = pr.getNext();
				//si pos es == 0, se devuelve lo que haya en pr, sino se continua buscando
				if(pos == 0){
					res = actual.getPLoc();
				}else {
					//Recorro hasta encontrar la posicion o hasta llegar a final de cola
					while(aux!=pos && next!=null){
						//me muevo una posicion a la derecha
						actual = next;
						next = next.getNext();
						//sumo en 1 el nodo a buscar
						aux++;
					}
					//si el nodo siguiente es nulo, he llegado al final sin haber hecho el return, por lo que devuelvo la excepcion
					if(pos>aux){
						throw new IndexOutOfBoundsException(Integer.toString(pos));
					}
					//cuando sale del bucle while, la variable NEXT contiene el Ploc buscado
					res = actual.getPLoc();
				}
			}
		}
		return res;
	}
	//metodo que devuelve un array de PLoc con todas las ciudades del pais pasado por parametro
	public PLoc[] Pais(String p){
		PLoc[] res = null;
		//Si el string pasado no es null
		if(p!=null){
			//si hay una lista de nodos
			if(pr != null){
				//Creo los auxiliares de busqueda
				NodoLG actual = pr;
				NodoLG next = pr.getNext();
				//mientras el siguiente nodo no sea nulo
				while(next!=null){
					//si este nodo coincide con su pais lo meto dentro del array
					if(actual.getPLoc().getPais()!=null && actual.getPLoc().getPais().equalsIgnoreCase(p)){
						//si no se ha encontrado ningun PLoc con el pais pasado por parametro hasta ahora
						if(res == null){
							res = new PLoc[1];
							res[0]=actual.getPLoc();
							//si no se anyade y expande
						}else{
							PLoc[] aux = res;
							res = new PLoc[aux.length+1];
							for(int i =0; i<aux.length;i++){
								res[i] = aux[i];
							}
							//y en la ultima posicion situo el nodo actual
							res[res.length-1] = actual.getPLoc();
						}
					}
					actual=next;
					next= next.getNext();
				}
				//evaluo el ultimo nodo
				if(actual.getPLoc().getPais()!=null && actual.getPLoc().getPais().equalsIgnoreCase(p)){
					//si no se ha encontrado ningun PLoc con el pais pasado por parametro hasta ahora
					if(res == null){
						res = new PLoc[1];
						res[0]=actual.getPLoc();
						//si no se anyade y expande
					}else{
						PLoc[] aux = res;
						res = new PLoc[aux.length+1];
						for(int i =0; i<aux.length;i++){
							res[i] = aux[i];
						}
						//y en la ultima posicion el nodo actual
						res[res.length-1] = actual.getPLoc();
					}
				}
			}
		}
		return res;
	}
	//metodo que ordena de forma creciente la lista por la coordenada longitud decimal y en orden alfabetico
	public void ordenaLista(){
		//PLoc auxiliar para cambiar entre dos posiciones del array
		PLoc aux = null;
		//Realizo un bubbleSort. Almaceno en la primera posicion del que tenga el valor mas bajo comparado con todos
		//los demas elementos de la lista, y asi con cada posicion
		if(pr!=null){
			if(pr.getNext()!=null){
				//inicializamos las variables
				NodoLG actual1 = pr;
				NodoLG actual2 = pr.getNext();
				while(actual1!=null) {
					while(actual2!=null) {
						//si el nodo 1 es mayor que el nodo 2, los cambiamos entre ellos
						if(actual1.getPLoc().compareTo(actual2.getPLoc())==1){
							aux = actual2.getPLoc();
							actual2.ploc(actual1.getPLoc());
							actual1.ploc(aux);
						}
						//paso al siguiente nodo de la lista
						actual2=actual2.getNext();
					}
					//paso al siguiente nodo de la lista
					actual1=actual1.getNext();
					if(actual1!=null) {
						actual2=actual1.getNext();
					}
				}
			}
		}
	}
	//NEW metodo auxiliar que me permite imprimir todas las ciudades fronterizas y mutuas de esta listaG.
	public void imprimeFronteras(String continente, double Lmax) {
		//Variables auxiliares
		int contCiudades = 0;
		//Recorro la lista entera, primeramente veo que hay ciudades en la lista
		if(pr!=null) {
			NodoLG actual = pr;
			while(actual!=null){
				//Si pertenece a este continente
				if(actual.getPLoc().getContinente()==continente || (actual.getPLoc().getContinente()!=null && actual.getPLoc().getContinente().equalsIgnoreCase(continente))) {
					//Aumento en uno el contador de ciudades del continente
					contCiudades++;
					//Ahora le buscamos la ciudad de UN PAIS DIFERENTE mas cercana.
					//Primero recorremos la lista de nuevo desde el principio
					NodoLG actual2 = pr;
					while(actual2!=null) {
						//Comprobamos que son del mismo continente
						if(actual2.getPLoc().getContinente()==continente || (actual2.getPLoc().getContinente()!=null && actual2.getPLoc().getContinente().equalsIgnoreCase(continente))) {
							//Comprobamos que son de paises diferentes
							if(actual.getPLoc().getPais()!=actual2.getPLoc().getPais() && (actual.getPLoc().getPais()!=null && actual2.getPLoc().getPais()!=null 
									&& !actual.getPLoc().getPais().equalsIgnoreCase(actual2.getPLoc().getPais()))) {
								//calculo la distancia entre ellos
								if(actual.getPLoc().getGps()!=null && actual2.getPLoc().getGps()!=null) {
									double[] gps1 = actual.getPLoc().getGps();
									double[] gps2 = actual2.getPLoc().getGps();
									//aplico la formula para almacenarla en mi distance
									double distance = Math.sqrt(Math.pow((gps2[0])-(gps1[0]), 2)+ Math.pow((gps2[1])-(gps1[1]),2));

									////_______TEST
									/*System.out.println(actual.getPLoc().getPais()+" - Valores: " +gps1[0] +"-"+gps1[1]);
									System.out.println(actual2.getPLoc().getPais()+" - Valores: " +gps2[0] +"-"+gps2[1]);
									System.out.println(actual.getPLoc().getPais()+" - " +actual.getPLoc().getCiudad()+
													" frontera de "+actual2.getPLoc().getPais() +" - "+actual2.getPLoc().getCiudad()+
													" a distancia: "+distance);*/

									//Compruebo que es menor o igual a la distancia minima
									if(distance<=Lmax) {
										//Si es menor a la otra ciudad que tenia el ploc anteriormente, este sera su nuevo PLoc mas cercano
										if(distance<actual.getDistancia()) {
											actual.setCercano(actual2.getPLoc(), distance);
										}
									}
								}
							}
						}
						//Paso al siguiente nodo
						actual2=actual2.getNext();
					}
				}
				//Paso al siguiente nodo
				actual=actual.getNext();
			}
			//Imprimo el mensaje de falta de fronteras
			if(contCiudades<=1) {
				System.out.println("NO HAY FRONTERAS INTRACONTINENTALES");	
			}else {
				//Siempre se imprime este mensaje
				System.out.println("CIUDADES FRONTERIZAS");	
				//Llegados aqui todos los PLocs del continente tienen la distancia a la otra ciudad fronteriza mas proxima. Lo unico que hay que
				//hacer es explorarla e imprimir los valores en base a ese valor distancia
				actual = pr;
				//tengo una lista de los nodos con ciudades fronterizas, para ordenarlo mas facilmente luego
				ArrayList<NodoLG> listaMagica = new ArrayList<NodoLG>();
				//Ademas de una lista de los mutuos (solo saldra una vez)
				ArrayList<NodoLG> listaMutuas = new ArrayList<NodoLG>();
				while(actual!=null) {
					//busco las ciudades que pertenezcan al continente
					if(actual.getPLoc().getContinente()==continente || (actual.getPLoc().getContinente()!=null && actual.getPLoc().getContinente().equalsIgnoreCase(continente))) {
						//Si tienen un PLoc cercano, entonces es que tienen ciudad fronteriza, por lo que lo metemos en la lista
						if(actual.getCercano()!=null) {
							listaMagica.add(0,actual);
						}
					}
					actual = actual.getNext();
				}
				//Realizo un bubble sort para ordenar segun la distancia entre ciudades fronterizas (explicado en ordenaLista de vectorG)
				NodoLG aux = null;
				for(int i = 0; i < listaMagica.size();i++){
					for(int j = i+1; j<listaMagica.size();j++){
						//Si son mutuamente ciudades fronterizas, entonces lo saco de la lista
						if(listaMagica.get(i).getCercano()==listaMagica.get(j).getPLoc() &&
								listaMagica.get(j).getCercano()==listaMagica.get(i).getPLoc()) {
							//lo anyado en lista mutuas
							listaMutuas.add(listaMagica.get(i));
							//y procedo a sacar ambos de la lista de ciudades fronterizas
							listaMagica.remove(j);
						}else {
							if(listaMagica.get(i).getDistancia()>=listaMagica.get(j).getDistancia()){
								aux=listaMagica.get(j);
								aux=listaMagica.set(i,aux);
								listaMagica.set(j,aux);
							}
						}
					}
				}
				//Realizo un bubble sort para ordenar segun la distancia entre ciudades fronterizas mutuas(explicado en ordenaLista de vectorG)
				for(int i = 0; i < listaMutuas.size();i++){
					for(int j = i+1; j<listaMutuas.size();j++){
						if(listaMutuas.get(i).getDistancia()>=listaMutuas.get(j).getDistancia()){
							aux=listaMutuas.get(j);
							aux=listaMutuas.set(i,aux);
							listaMutuas.set(j,aux);
						}
					}
				}
				//recorro para imprimir las ciudades fronterizas
				for(int i=0;i<listaMagica.size();i++) {
					//Imprimo la info del tiron
					PLoc primero = listaMagica.get(i).getPLoc();
					PLoc segundo = listaMagica.get(i).getCercano();
					//Vemos cual de las dos va primero (si aparecen en la listaMutuas
					for(int j=0;j<listaMutuas.size();j++) {
						if(listaMagica.get(i)==listaMutuas.get(j)) {
							if(listaMagica.get(i).getPLoc().getCiudad().compareToIgnoreCase(listaMutuas.get(j).getCercano().getCiudad())>0) {
								segundo = listaMagica.get(i).getPLoc();
								primero = listaMagica.get(i).getCercano();
							}
						}
					}
					System.out.println(primero.getCiudad()+" ("+primero.getPais()+") - "+
							segundo.getCiudad()+" ("+segundo.getPais()+")");
				}
				//Imprimo las mutuas si hay
				if(listaMutuas.size()>0) {
					System.out.println("CIUDADES FRONTERIZAS MUTUAS");
					for(int i=0;i<listaMutuas.size();i++) {
						//Vemos cual de los dos va primero
						PLoc primero = null;
						PLoc segundo = null;
						if(listaMutuas.get(i).getPLoc().getCiudad().compareToIgnoreCase(listaMutuas.get(i).getCercano().getCiudad())<0) {
							primero = listaMutuas.get(i).getPLoc();
							segundo = listaMutuas.get(i).getCercano();
						}else{
							segundo = listaMutuas.get(i).getPLoc();
							primero = listaMutuas.get(i).getCercano();
						}
						System.out.println(primero.getCiudad()+" ("+primero.getPais()+") - "+
								segundo.getCiudad()+" ("+segundo.getPais()+")");
					}
				}
			}
		}
	}
}
