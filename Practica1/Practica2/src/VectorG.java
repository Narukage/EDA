import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;

public class VectorG implements Lista{
	
	
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
		return (pr == null) ? true : false;
	}
	
	//Inserta al principio el ploc
	public void insertaCabeza(PLoc p)
	{
		
	}
	
	//Inserta al final el ploc
	public void insertaCola(PLoc v)
	{
		
	}
	
	//Inserta el array de ploc al final
	public void insertaArrayPLoc(PLoc[] v)
	{
		
	}
	
	//Devuelve true si ha borrado el primer elemento
	public boolean borraCabeza()
	{
		
	}
	
	//Devuelve true si se ha borrado el ultimo elemento
	public boolean borraCola()
	{
		
	}
	
	//Devuelve la posicion en la lista del ploc con una
	//ciudad con dicho nombre
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion
	{
		
	}
	
	//Devuelve true si ha borrado el ploc con la ciudad
	public boolean borraCiudad(String v)
	{
		
	}
	
	//Devuelve true si ha borrado el ploc con ese pais
	public boolean borraPais(String s)
	{
		
	}
	
	//Devuelve el ploc de dicha posicion
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException
	{
		
	}
	
	//Devuelve los PLocs que pertenecen a ese pais
	public PLoc[] Pais(String p)
	{
		
	}
	
	//Ordena el array de PLocs
	public void ordenaLista()
	{
		
	}
}
