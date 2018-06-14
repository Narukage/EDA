//DNI MIGUEL HERMIDA CORES
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ArbolS extends Arbol{
	//TreeMap organizado por paises, con un treeset por pais
	private TreeMap<String , TreeSet<PLoc>> tm;
	
	public ArbolS() {
		 tm = new TreeMap<String , TreeSet<PLoc>>();
	 }
	
	public void leeArbol(String f) {
		if(f == null)
			return;
		
		//Variables iniciales
        FileReader     file_reader     = null; //Lector de fichero
        BufferedReader buffered_reader = null; //Memoria en buffer para leer linea por linea
        String 		   line            = null; //Cada linea que se lee del fichero
	
        try {
        	//Lee el fichero
        	file_reader     = new FileReader(f);
        	buffered_reader = new BufferedReader(file_reader);
        	
        	//Leemos y comprobamos la primera linea y luego continuamos con do-while
        	line = buffered_reader.readLine();
        	
        	if( line != null) {
	            do{
	            	//Dividimos la linea leida en funcion de sus #
	                String[] datos = line.split("#");
	                boolean continente = false;
	                
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
	                
	                //Si se le ha asgiando el continente, ponemos la variable auxiliar a true
					if(continente){
						a.set_no_tenia_continente(false);
					}
					
					//Tratamos las coordenadas
                    String[] latitud=datos[3].split(" ");
                    String[] longitud=datos[4].split(" ");
                    
                    Coordenada latitud_coordenada  = new Coordenada(Integer.parseInt(latitud[0]),  Integer.parseInt(latitud[1]),  latitud[2].charAt(0));
	                Coordenada longitud_coordenada = new Coordenada(Integer.parseInt(longitud[0]), Integer.parseInt(longitud[1]), longitud[2].charAt(0));
	                
	                //Creamos el ploc a insertar
                	PLoc dentro = new PLoc(datos[0], datos[1], datos[2]);
                	
	                //Imprimir algun error por coordenada erronea
	                try{                    	
                    	//y le pasamos las coordenadas
                        dentro.setLatitud(latitud_coordenada);
                        dentro.setLongitud(longitud_coordenada);
                        //metemos el ploc en la lista
                        inserta(dentro);
                        
	                }catch(CoordenadaExcepcion e){
                        System.out.println(e);
                    }
	                
	                //Leemos la linea para continuar la lectura
	                line=buffered_reader.readLine();
	                
	            }while(line!=null);
        	}
	            
        }catch(IOException o){
				o.printStackTrace();
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
	
	@Override
	public boolean esVacio() {
		if(tm == null || tm.isEmpty())
			return true;
		
		return false;
	}
	
	@Override //Inserta el ploc dentro del treeMap usando su pais
	public boolean inserta(PLoc p) {
		//Comprobamos que el objeto que nos han pasado no es nulo
		if(p == null)
			return false;
				
		if(p.getPais() == null)
			return false;
		
		//Cogemos el treeset asociado al pais
    	TreeSet<PLoc> t = tm.get(p.getPais());
		
    	//Si no existe, creamos el treeset y lo anyadimos con su pais
    	if(t == null) {
    		t = new TreeSet<PLoc>();
			t.add(p);
			tm.put(p.getPais(), t);
			return true;
	    //Si existe, lo anyadimos ahi
    	}else {
    		t.add(p);
			tm.put(p.getPais(), t);
			return true;
    	}
	}
	
	@Override //Busca la ciudad en el treemap
	public boolean ciudadEnArbol(String v) {
		if(v == null)
			return false;
		
		if(esVacio())
			return false;
		
		//Recorremos  el TreeMap entero
		for(Map.Entry<String, TreeSet<PLoc>> paises : tm.entrySet()) {
			//Ploc auxiliar
			PLoc p = new PLoc(null, paises.getKey(), null);
			//Recogemos todas las ciudades con ese pais
			TreeSet<String> ciudades = getCiudades(p);
			//Si el TreeSet contiene la ciudad, devolvemos true
			if(ciudades.contains(v)) {
				return true;
			}
		}
		return false;
	}
	
	//Devuelve las ciudades de un pais
	public TreeSet<String> getCiudades(PLoc p) {
		if(p == null)
			return null;
		
		if(esVacio())
			return null;
		
		//Generamos el treeSet auxiliar
		TreeSet<String> val = new TreeSet<String>();
		
		//Buscamos dentro del TreeMap el treeset con el pais del Ploc
		if(tm.containsKey(p.getPais())) {
			//Si existe, lo generamos
			TreeSet<PLoc> pel = tm.get(p.getPais());
			//Copiamos de cada PLoc su ciudad en el TreeSet de tipo string
			for(PLoc ciudad : pel) {
				if(ciudad.getCiudad() == null)
					val.add("x");
				else
					val.add(ciudad.getCiudad());
			}
		}
		return val;
	}
	
	@Override //Busca el PLoc mas alejado en la direccion dada
	public PLoc busquedaLejana(String s) {
		if(s == null)
			return null;
		
		PLoc pret = null;
		
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
					pret = Busqueda(pret,p,s);
				}
			}
		}
		return pret;
	}
	
	public PLoc Busqueda(PLoc resultado, PLoc localidades, String s){
	
		double doble1 	= 0;
		double doble2 	= 0;
		PLoc nueva 		= null;
		double[] Coord	= null;
		
		if(resultado!=null){
			
			Coord	= Distancias(resultado, localidades, s);
			doble1	= Coord[0];
			doble2	= Coord[1];
			
			if(doble1 > doble2) 
				nueva = localidades;
			else
				nueva = resultado;
			
		}else{
			nueva = localidades;
		}
	return nueva;	
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
	
	public double[] Distancias(PLoc auxiliar,PLoc bucle, String s){
		
		double Dist1 = 0.0;
		double Dist2 = 0.0;
		double[] aux = new double[2];
		
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
	
	public boolean borraPais(String p) {
		if(p == null)
			return false;
		
		if(esVacio())
			return false;
		
		//Si el treeMap contiene dicho pais
		if(tm.containsKey(p)) {
			//Lo borramos y devolvemos true
			if(tm.remove(p)!=null)
				return true;
		}
		return false;
	}
	
	//Devuelve la coleccion de los paises
	public Set<String> getPaises(){
		if(esVacio())
			return null;
		else
			return tm.keySet();
	}
	
	//NEW, busca un pais con el nombre pasado por parametro
	public boolean buscarPais(String v) {
		if(v == null)
			return false;
		
		if(esVacio())
			return false;
		
		if(tm.containsKey(v)){
			return true;
		}	
	return false;
	}
	
	public TreeMap<String , TreeSet<PLoc>> getTreeMap(){
		return tm;
	}
}
