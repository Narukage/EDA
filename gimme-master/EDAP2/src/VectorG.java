//DNI 48620792B BARBA ROBLES, ALBERTO
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;

public class VectorG implements Lista{
	private ArrayList<PLoc> vector;
	//constructor que crea un vector vacio
	public VectorG(){
		//creado sin ser nulo, solo vacio
		vector=new ArrayList<PLoc>();
	}
	//Escribe el vector entero
	public void escribeVectorG(){
		//recorro el vector entero
		for(int i=0; i<vector.size(); i++){
			//Imprimo por pantalla la info del PLoc segun el formato que me dan en la descripcion
			//Primero imprimo el posic
			System.out.print("posic "+i+": ");
			//luego la info del PLoc sumando su metodo escribeInfoGps()
			vector.get(i).escribeInfoGps();
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
						//Si se le ha asignado el continente, pongo la variable auxiliar a true
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

							//--------------------------------> SIEMPRE A LA CABEZA O AL FINAL,
							//O CON LA FUNCION ADD SIN INDICE 
							//para meter el PLoc dentro del ArrayList
							//Respuesta: En la cabeza con el indice, si tengo que anyadirlo al final, pues con add
							vector.add(0, a);
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
	
	//0(1)
	public boolean esVacia(){
		//me devuelve el booleano
		return vector.isEmpty();
	}
	//---------------en inserta cabeza e insertaCola e insertaArray,
	//si alguno de los plocs que se quieren insertar es nulo, se inserta igualmente?
	//Respuesta: NI DE CONYA, no hay nulls

	//0(n)
	public void insertaCabeza(PLoc p){
		//anyade al principio 
		if(p != null){
			vector.add(0, p);
		}
	}
	//0(1)
	public void insertaCola(PLoc v){
		if(v != null){
			vector.add(v);
		}
	}
	//0(n)
	public void insertaArrayPLoc(PLoc[] v){
		if(v != null){
			for(int i=0;i < v.length;i++){
				if(v[i] != null){
					vector.add(v[i]);
				}
			}
		}
	}
	//0(n)
	public boolean borraCabeza(){
		if(vector.isEmpty()){
			return false;
		}else{
			vector.remove(0);
			return true;
		}
	}
	//0(1)
	public boolean borraCola(){
		if(vector.isEmpty()){
			return false;
		}else{
			//eliminar el ultimo
			vector.remove(vector.size()-1);
			return true;
		}
	}
	//---------------------------->ignorar diferencias de mayusculas y minusculas ?
	//Respuesta: Meter siempre equalsIgnoreCase
	//0(n)
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion{
		for(int i=0;i<vector.size();i++){
			if(vector.get(i).getCiudad()==v || (vector.get(i).getCiudad()!=null && vector.get(i).getCiudad().equalsIgnoreCase(v))){
				//devolver la posicion de ese PLoc
				return i;
			}
		}
		//si no encuentro la ciudad lanzo la excepcion
		throw new CiudadNoEncontradaExcepcion(v);
	}
	//---------------------------->ignorar diferencias de mayusculas y minusculas ?
	//0(n)
	public boolean borraCiudad(String v){
		for(int i=0;i<vector.size();i++){
			if(vector.get(i).getCiudad()==v || (vector.get(i).getCiudad()!=null && vector.get(i).getCiudad().equalsIgnoreCase(v))){
				vector.remove(i);
				//devuelvo la posicion de ese PLoc
				return true;
			}
		}
		//cuando no encuetra la ciudad que coincida
		return false;
	}
	//---------------------------->ignorar diferencias de mayusculas y minusculas ?
	//equalsIgnoreCase por si las moscas
	
	//metodo para borrar el pais
	//0(n)
	public boolean borraPais(String s){
		//bucle para recorrer el tamanyo
		for(int i=0;i<vector.size();i++){
			//si esa posicion del vector con el pais coincide con el pasado por parametro
			if(vector.get(i).getPais()==s || (vector.get(i).getPais()!=null && vector.get(i).getPais().equalsIgnoreCase(s))){
				vector.remove(i);
				//devolver la posicion de ese PLoc
				return true;
			}
		}
		//cuando no encuetra la ciudad que coincida
		return false;
	}
	//0(1)
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException{
		if(pos >= 0 && pos <vector.size()){
			return vector.get(pos);			
		}
		//paso el entero a String
		throw new IndexOutOfBoundsException(Integer.toString(pos));
	}	
	public PLoc[] Pais(String p){
		PLoc[] res=null;
		//Recorremos el vector
		for(int i=0;i<vector.size();i++){
			//Compruebo que el Ploc tenga el pais buscado
			if(vector.get(i).getPais()== p || (vector.get(i).getPais() != null && vector.get(i).getPais().equalsIgnoreCase(p))){
				//almaceno el PLoc con el mismo pais
				//Si res es nulo, lo inicializo con un hueco
				if(res==null){
					res = new PLoc[1];
					//sino, expando en una posicion mas el hueco
				}else{
					//copio en un array auxiliar el original
					PLoc[] aux = res;
					//al original le doy un tamanyo extra, y ahora apunta a otra posicion de memoria
					res = new PLoc[aux.length+1];
					//como aux tiene la posicion de memoria del array original, copio todo su contenido
					for(int x = 0; x<aux.length;x++){
						res[x] = aux[x];
					}
				}
				//lo almaceno en la ultima posicion 
				res[res.length-1]=vector.get(i);
			}
		}
		return res;
	}	
	public void ordenaLista(){
		//PLoc auxiliar para cambiar entre dos posiciones del array
		PLoc aux = null;
		//Realizo un bubbleSort. Almaceno en la primera posicion del que tenga el valor mas bajo comparado con todos
		//los demas elementos del vector, y asi con cada posicion
		for(int i = 0; i < vector.size();i++){
			for(int j = i+1; j<vector.size();j++){
				//si algun elemento del array es menor que el de la posicion con el que estoy comparando, se cambian de sitio
				if(vector.get(i).compareTo(vector.get(j))==1){
					//guardo el vector "mas pequenyo" en el auxiliar
					aux=vector.get(j);
					//lo quito de la lista
					vector.remove(j);
					//lo anyado en la posicion de i, dado que al hacerlo desplazo el elemento en "i" a la
					//derecha, por tanto el elemento que estaba en J ocupara su lugar (asi se ordena mejor de forma creciente)
					vector.add(i,aux);
				}
			}
		}
	}
	//NEW metodo que invoca EscribeInfoGPS del PLoc de la lista ordenada mas cercana a la longitud pasada por parametro
	public void imprimeCercano(double l){
		//Recorro la lista desde izquierda a derecha arrinconando el valor por ambos extremos y
		//Comparo los valores finales por la izquierda y la derecha, eligiendo el mas cercano para imprimir

		//Auxiliares
		double izq = Double.MAX_VALUE;
		double dech = Double.MAX_VALUE;
		PLoc izqPLoc = null;
		PLoc dechPLoc = null;
		//recorro la lista desde izquierda
		for(int i=0;i<vector.size();i++){
			//Si tiene gps
			if(vector.get(i).getGps()!=null){
				//Si longitud - longitud del ploc es mayor que 0 (esta a su izquierda)
				if(l-vector.get(i).getGps()[1]>0){
					//Si es menor al valor menor previo
					if(l-vector.get(i).getGps()[1]<=izq){
						//es el nuevo nodo mas cercano a la izquierda
						izq = l-vector.get(i).getGps()[1];
						izqPLoc = vector.get(i);
					}
				}
			}
		}
		//ahora recorro la lista desde derecha
		for(int i=vector.size()-1;i>=0;i--){
			//Si tiene gps
			if(vector.get(i).getGps()!=null){
				//Si longitud - longitud del ploc es mayor que 0 (esta a su izquierda)
				if(vector.get(i).getGps()[1]-l>0){
					//Si es menor al valor menor previo
					if(vector.get(i).getGps()[1]-l<=dech){
						//Es el nuevo nodo mas cercano por la derecha
						dech =vector.get(i).getGps()[1]-l;
						dechPLoc = vector.get(i);
					}
				}
			}
		}
		//Si el que esta a la izquierda es de la misma longitud
		if(izq==0){
			izqPLoc.escribeInfoGps();
		}else{
			//Si el que esta a la derecha es de la misma longitud
			if(dech==0){
				dechPLoc.escribeInfoGps();
			}else{
				//Si el de la derecha esta a menor distancia
				if(izq>dech){
					dechPLoc.escribeInfoGps();
				}else{
					if(izq<dech){
						//sino es el que esta a la izquierda
						izqPLoc.escribeInfoGps();
					}
				}
			}
		}
		//Si llega aqui sin escribir nada, enonces en la lista no habia PLocs
	}
	//NEW invoca EscribeInfoGPS de los PLocs mas cercanos a L dentro del rango, sacandolos de la lista ordenada.
	//Realiza un bucle hasta que dejan de haber PLocs dentro del rango.
	public void imprimeCercanos(double l, double rango){
		//Recorro la lista desde izquierda a derecha arrinconando el valor por ambos extremos.
		//Comparo los valores finales por la izquierda y la derecha y selecciono el mas cercano para imprimir.
		//Una vez que he impreso sus datos, lo quito de la lista ya que no lo necesito mas

		//Empiezo con que los nodos de derecha e izquierda como nulos y se van recogiendo de la lista del vector
		PLoc izqPLoc = null;
		PLoc dechPLoc = null;
		//Auxiliar para comprobar que hay ciudades dentro del rango
		boolean hayCiudades = false;
		//inicio del bucle
		do {
			//Auxiliares
			double izq = Double.MAX_VALUE; //distancia del nodo a la izquierda de L mas cercano
			double dech = Double.MAX_VALUE; //igual pero a la derecha

			izqPLoc = null;
			dechPLoc = null;

			int posizq = -1;
			int posdech = -1; //posiciones en el vector del nodo izquierda y derecha mas cercanos a la longitud "l"

			//recorro la lista desde izquierda
			for(int i=0;i<vector.size();i++){
				//Si tiene gps
				if(vector.get(i).getGps()!=null){
					//Si longitud - longitud del ploc es mayor que 0 (esta a su izquierda) y esta en el rango
					if(l-vector.get(i).getGps()[1]>0 && l-vector.get(i).getGps()[1]<=rango){
						//Si es menor al valor menor previo
						if(l-vector.get(i).getGps()[1]<=izq){
							//es el nuevo nodo mas cercano a la izquierda
							izq = l-vector.get(i).getGps()[1];
							izqPLoc = vector.get(i);
							posizq = i;
							//si hay alguna, entonces es true
							hayCiudades=true;
						}
					}
				}
			}
			//recorro la lista desde la derecha
			for(int i=vector.size()-1;i>=0;i--){
				//Si tiene gps
				if(vector.get(i).getGps()!=null){
					//Si longitud del ploc - longitud es mayor que 0 (esta a su derecha)
					if(vector.get(i).getGps()[1]-l>0 && vector.get(i).getGps()[1]-l<=rango ){
						//Si es menor al valor menor previo
						if(vector.get(i).getGps()[1]-l<=dech){
							//Es el nuevo nodo mas cercano por la derecha
							dech =vector.get(i).getGps()[1]-l;
							dechPLoc = vector.get(i);
							posdech = i;
							//si hay alguna, es true
							hayCiudades=true;
						}
					}
				}
			}
			//Si el que esta a la izquierda esta en la longitud
			if(izq==0){
				System.out.println(izqPLoc.getCiudad());
				vector.remove(posizq);
			}else{
				//Si el que esta a la derecha es el que esta en la longitud
				if(dech==0){
					System.out.println(dechPLoc.getCiudad());
					vector.remove(posdech);
				}else{
					//Si el de la derecha esta a menor distancia
					if(izq>dech){
						System.out.println(dechPLoc.getCiudad());
						vector.remove(posdech);
					}else{
						if(izq<dech){
							//sino es el que esta a la izquierda
							System.out.println(izqPLoc.getCiudad());
							vector.remove(posizq);
						}else {
							if(izq==dech && izqPLoc!=null) {
								//Si son iguales, el de la izquierda preferentemente
								System.out.println(izqPLoc.getCiudad());
								vector.remove(posizq);
							}
						}
					}
				}
			}

			//No necesito comprobar que izqPLoc o dechPLoc no sean nulos porque como las variables izq y dech empiezan como Double.MAX_VALUE
			//ambas, pues si ambos nodos no existen saldran de esta cadena de ifs (no hay un if que ponga izq==dech). Y con que alguno exista, ya la distancia a la longitud sera
			//menor que el Double.MAX_VALUE que he puesto.
		}while(!(izqPLoc==null && dechPLoc==null));
		//llegados aqui, si no hubo ciudades, imprimo el mensaje de que no las hubo
		if(!hayCiudades) {
			System.out.println("NO HAY CIUDADES EN ESE RANGO");
		}
	}
}
