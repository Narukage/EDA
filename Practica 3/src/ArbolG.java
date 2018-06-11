
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
	
	public boolean esVacio() {
		
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
