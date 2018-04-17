//DNI 48620792B BARBA ROBLES, ALBERTO
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Map;

public class ArbolS extends Arbol{
	//TreeMap organizado por paises, con un treeset por pais
	private TreeMap<String , TreeSet<PLoc>> tm;
	
	 public ArbolS() {
		 tm = new TreeMap<String , TreeSet<PLoc>>();
	 }
	
	//Lee el fichero de texto e inserta en el treeMap los PLocs organizados por pais
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
	
	//Comprueba que el tree map esta vacio
	public boolean esVacio() {
		//Booleano comprobador
		boolean vacio=false;
		//Si no existe
		if(tm!=null) {
			//Si esta vacio
			if(tm.isEmpty()) {
				vacio=true;
			}else {
				vacio=false;
			}
		}else {
			vacio = true;
		}
		return vacio;
	}
	
	//Inserta el ploc dentro del treeMap usando su pais
	public boolean inserta(PLoc p) {
		//Si existe
		if(p!=null) {
			//Si tiene pais
			if(p.getPais()!=null) {
				//Cogemos el treeset asociado al pais
		    	TreeSet<PLoc> t = tm.get(p.getPais());
		    	//Si existe, lo anyadimos ahi
				if(t!=null) {
					t.add(p);
					return true;
				//Sino, creamos el treeset y lo anyadimos con su pais
				}else {
					t = new TreeSet<PLoc>();
					t.add(p);
					tm.put(p.getPais(), t);
					return true;
				}
			}
		}
		      
		return false;
	}
	
	//Busca a ver si esta la ciudad en el TreeMap
	public boolean ciudadEnArbol(String v) {
		boolean encontrada = false;
		//Si no es null el string
		if(v!=null) {
			//Si el arbol no es vacio
			if(!esVacio()) {
				//Recorremos  el TreeMap entero buscando por todos los paises
				for(Map.Entry<String, TreeSet<PLoc>> paises : tm.entrySet()) {
					//Generamos un Ploc auxiliar con cada pais
					PLoc p = new PLoc(null, paises.getKey(), null);
					//Recogemos todas las ciudades con ese pais
					TreeSet<String> ciudades = getCiudades(p);
					//Si el TreeSet contiene la ciudad, devolvemos true
					if(ciudades.contains(v)) {
						encontrada=true;
					}
				}	
			}

		}
		return encontrada;
	}
	
	//Metodo que devuelve las ciudades de un pais en un TreeSet
	public TreeSet<String> getCiudades(PLoc p) {
		//Generamos el treeSet auxiliar
		TreeSet<String> val = new TreeSet<String>();
		//Miramos que P no sea null
		if(p!=null) {
			//Si no es vacio el treeMap
			if(!esVacio()) {
				//Buscamos dentro del TreeMap el treeset con el pais del ploc
				if(tm.containsKey(p.getPais())) {
					//Si existe, lo generamos
					TreeSet<PLoc> pel = tm.get(p.getPais());
					//Copiamos de cada PLoc su ciudad en el TreeSet de tipo string
					for(PLoc ciudad : pel) {
						val.add(ciudad.getCiudad());
					}
				}
			}
		}
		return val;
	}
	
	//Busca el PLoc mas alejado en la direccion dada
	public PLoc busquedaLejana(String s) {
		//Lo que devolvemos
		PLoc pret = null;
		//Si el string no es nulo
		if(s!=null) {
			//Recorremos el treeMap
			for(Map.Entry<String, TreeSet<PLoc>> paises : tm.entrySet()) {
				//Cogemos cada treeSet de PLoc
				TreeSet<PLoc> pel = tm.get(paises.getKey());
				//Recorremos buscando el ploc adecuado
				for(PLoc p : pel) {
					//Si es el primero lo agregamos
					if(pret == null) {
						pret = p;
					}else {
						//Sino, comparamos buscando el mas lejano
						
					}
				}
			}
		}
		return null;
	}
	
	//Devuelve true si el pais ha sido quitado del treeMap
	public boolean borraPais(String p) {
		//Condicion
		boolean borrado = false;
		//Miramos que existe el string
		if(p!=null) {
			//Que existe el treeMap y no es vacio
			if(!esVacio()) {
				//Si el treeMap contiene dicho pais
				if(tm.containsKey(p)) {
					//Lo borramos y devolvemos true
					tm.remove(p);
					borrado = true;
				}
			}
		}
		return borrado;
	}
	
	//Devuelve los paises del TreeMap
	public Set<String> getPaises(){
		Set<String> keys = null;
		//Si el treeMap no es vacio
		if(!esVacio()) {
			//Devuelve el keyset de los paises
			keys = tm.keySet();
		}
		return keys;
	}
	
	//NEW:: Getter de TreeMap
	public TreeMap<String , TreeSet<PLoc>> getTreeMap(){
		return tm;
	}

	//NEW: busca el pais indicado
	public boolean buscarPais(String v) {
		boolean encontrada = false;
		if(v!=null) {
			if(!esVacio()) {
				if(tm.containsKey(v)){
					encontrada = true;
				}	
			}

		}
		return encontrada;
	}
}
