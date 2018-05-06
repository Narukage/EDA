import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ListaG.NodoLG;

import java.lang.*;

public class VectorG implements Lista{
	//Variables privadas
	private ArrayList<PLoc> vector;
	
	//Constructor
	VectorG()
	{
		vector = new ArrayList<PLoc>();
	}
	
	//Escribe los valores del vector
	public void escribeListaG()
	{
		//Recorremos el vector entero
		int indice = 0;       //indice del nodo actual
		for( ; indice < vector.size();indice++)
		{
			//Escribimos la informacion
			System.out.print("nodo "+indice+": ");
			vector.get(indice).escribeInfoGps();
		}
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//Metodos de la interfaz
	
	//lee el documento pasado por parametro y genera
	//la lista de plocs
	public void leeLista(String f)
	{
		//Guardian principal, debe de haber documento que leer
        if(f == null)
        	return;
        
        //Variables iniciales
        FileReader     file_reader     = null; //Lector de fichero
        BufferedReader buffered_reader = null; //Memoria en buffer para leer linea por linea
        String 		   line            = null; //Cada linea que se lee del fichero
        
        //IOException cazador
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
	                
	                //Tiene que tener 5 datos
	                if(datos.length !=5)
	                	break;
	                
	                //?? Un dato vacio al leer el documento, es decir, un string "", se mete como null o como ""?
	                //Saneamos los datos en variables manejables
	                //Comprobamos datos vacios
	                if(datos[0].equals(""))
	                	datos[0] = null;
	                
	                if(datos[1].equals(""))
	                	datos[1] = null;
	                
	                if(datos[2].equals(""))
	                	datos[2] = null;
	  
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

                        //metemos el ploc en el vector
                        insertaCabeza(dentro);

                        //Imprimir algun error por coordenada erronea
                    }catch(CoordenadaExcepcion e){
                        System.out.println(e);
                    }
	                
	                //Leemos la linea para continuar la lectura
	                line=buffered_reader.readLine();
	            }while(line != null);
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
	
	//indica si la lista esta vacia
	public boolean esVacia()
	{
		return vector.isEmpty();
	}
	
	//Inserta al principio el ploc
	public void insertaCabeza(PLoc p)
	{
		//Guardian de vacio
		if(p == null)
			return;
		
		//Anyadir elemento
		vector.add(p, 0);
	}
	
	//Inserta al final el ploc
	public void insertaCola(PLoc v)
	{
		//Guardian de vacio
		if(v == null)
			return;
		
		//Anyadir elemento
		vector.add(v);
	}
	
	//Inserta el array de ploc al final
	public void insertaArrayPLoc(PLoc[] v)
	{
		//Guardian de vacio
		if(v == null)
			return;
		
		//Guardian de sin elementos
		if(v.length == 0)
			return;
		
		//Recorremos el array metiendo los elementos no vacios
		for(int i = 0; i < v.length ; i++)
		{
			if(v[i] != null)
				vector.add(v[i]);
		}
	}
	
	//Devuelve true si ha borrado el primer elemento
	public boolean borraCabeza()
	{
		//Guardian de tamanyo
		if(esVacia())
			return false;
		
		//Quitar elemento
		vector.remove(0);
	}
	
	//Devuelve true si se ha borrado el ultimo elemento
	public boolean borraCola()
	{
		//Guardian de tamanyo
		if(esVacia())
			return false;
		
		//Quitar ultimo elemento
		vector.remove(vector.size()-1);
	}
	
	//Devuelve la posicion en la lista del ploc con una
	//ciudad con dicho nombre
	//?? Para que sean iguales se debe de comprobar con equals o equalsIgnoreCase. Ademas, si se pasa null o "", si son iguales a esos valores tambien cuenta?
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion
	{
		//Guardian de vacio
		if(v == null || v.equals("") || esVacia())
			throw new CiudadNoEncontradaExcepcion(Integer.toString(v));
		
		//Buscamos y devolvemos el elemento en el vector
		for(int i = 0; i < vector.size(); i++)
		{
			//Comprobaciones de vacios e igualdad
			if(vector.get(i).getCiudad()!=null && !vector.get(i).getCiudad().equals("") && vector.get(i).getCiudad().equalsIgnoreCase(v))
				return i;
		}
		
		throw new CiudadNoEncontradaExcepcion(Integer.toString(v));
	}
	
	//Devuelve true si ha borrado el ploc con la ciudad
	public boolean borraCiudad(String v)
	{
		//Guardian de vacio
		if(v == null || v.equals("") || esVacia())
			return false;
		
		//Buscamos y devolvemos el elemento en el vector
		for(int i = 0; i < vector.size(); i++)
		{
			//Comprobaciones de vacios e igualdad
			if(vector.get(i).getCiudad()!=null && !vector.get(i).getCiudad().equals("") && vector.get(i).getCiudad().equalsIgnoreCase(v))
			{
				//Quitar elemento
				vector.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	//Devuelve true si ha borrado el ploc con ese pais
	public boolean borraPais(String s)
	{
		//Guardian de vacio
		if(s == null || s.equals("") || esVacia())
			return false;
		
		//Booleano comprobador de eliminacion
		boolean almenosuno = false;
		
		//Buscamos y devolvemos el elemento en el vector
		for(int i = 0; i < vector.size(); i++)
		{
			//Comprobaciones de vacios e igualdad
			if(vector.get(i).getCiudad()!=null && !vector.get(i).getCiudad().equals("") && vector.get(i).getPais().equalsIgnoreCase(s))
			{
				//Quitar elemento
				vector.remove(i);
				almenosuno = true;
			}
		}
		
		return almenosuno;
	}
	
	//Devuelve el ploc de dicha posicion
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException
	{
		//Guardian de index out of bounds
		if(pos < 0 || pos >= vector.size() || esVacia())
			throw new IndexOutOfBoundsException(pos);
		
		//Devolvemos el PLoc de dicha posicion
		return vector.get(pos);
	}
	
	//Devuelve los PLocs que pertenecen a ese pais
	public PLoc[] Pais(String p)
	{
		//Guardian de vacio
		if(p == null || p.equals("") || esVacia())
			return null;
		
		//Metemos los elementos en un array auxiliar y lo devolvemos
		ArrayList<PLoc> auxiliar = new ArrayList<PLoc>();
		
		//Buscamos y devolvemos el elemento en el vector
		for(int i = 0; i < vector.size(); i++)
		{
			//Comprobaciones de vacios e igualdad
			if(vector.get(i).getCiudad()!=null && !vector.get(i).getCiudad().equals("") && vector.get(i).getPais().equalsIgnoreCase(p))
			{
				//Anyadimos el elemento a esta lista
				auxiliar.add(vector.get(i));
			}
		}
		
		//Guardian de encontrar algo
		if(auxiliar.isEmpty())
			return null;
		
		//Devolvemos el array
		PLoc[] devolver;
		return auxiliar.toArray(devolver);
	}
	
	//Ordena el array de PLocs
	public void ordenaLista()
	{
		//Algoritmo de la burbuja para ordenar
		for(int i = 0; i < vector.size(); i++)
		{
			for(int j = i+1; j < vector.size(); j++)
			{
				//Comparador e intercambio
				if(vector.get(i).compareTo(vector.get(j)) < 0)
				{
					PLoc auxiliar = vector.get(j);
					vector.remove(j);
					vector.add(auxiliar, i);
				}
			}
		}
	}
	
	//NEW, metodo getter del vector
	public ArrayList<PLoc> get_vector(){ return vector; }
}
