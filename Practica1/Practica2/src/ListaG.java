import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ListaG implements Lista{
	//Variables privadas
	private NodoLG pr; //Nodo de la cabeza
	private NodoLG fn; //NEW Nodo de la cola
	
	//Constructor
	public ListaG()
	{
		pr = null;
		fn = null;
	}
	
	//Escribe los valores de la lista
	public void escribeListaG()
	{
		
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

                        //metemos el ploc en la lista
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
		//Guardian de nulo
		if(p == null)
			return;
		
		//PLoc a insertar
		NodoLG n = new NodoLG(p);
		
		//Guardian principio
		if(pr == null)
		{
			pr = n;
			return;
		}
		
		n.set_next(pr);
		pr = n;
	}
	
	//Inserta al final el ploc
	public void insertaCola(PLoc v)
	{
		//Guardian de nulo
		if(v == null)
			return;
		
		//PLoc a insertar
		NodoLG n = new NodoLG(v);
		
		//Guardian de principio
		if(pr == null)
		{
			pr = n;
			return;
		}
		
		//Recorremos hasta llegar al final
		NodoLG aux = pr;
		while(aux.get_next()!=null)
			aux = aux.get_next();
		
		//Insertamos al final
		aux.set_next(n);
	}
	
	//Inserta el array de ploc al final
	public void insertaArrayPLoc(PLoc[] v)
	{
		//Guardian de nulo y tamanyo
		if(v == null && v.length > 0)
			return;
		
		//Primer elemento es nulo
		if(pr == null)
		{
			//Recorremos desde el final para ponerlos en orden
			for(int i = v.length-1; i > -1; i--)
			{
				//Siempre que el elemento exista
				if(v[i] != null)
				{
					//Si aun no hemos metido elemento, lo insertamos
					if(pr == null)
					{
						
						pr = new NodoLG(v[i]);

					//Para el resto de iteraciones, coger el nodo actual y moverlo una
					//posicion al final en lista, y poner al inicio el nuevo
					}else{
						
						NodoLG n = new NodoLG(v[i]);
						n.set_next(pr);
						pr = n;
						
					}
				}
			}
			
			return;
		}
		
		//Recorremos hasta llegar al final
		NodoLG aux = pr;
		while(aux.get_next() != null)
			aux = aux.get_next();
		
		//Recorremos la lista insertandolos en orden
		for(int i = 0; i < v.length; i++)
		{
			if(v[i] != null)
			{
				NodoLG n = new NodoLG(v[i]);
				aux.set_next(n);
				aux = n;
			}
		}
	}
	
	//Devuelve true si ha borrado el primer elemento
	public boolean borraCabeza()
	{
		//Guardian de vacio
		if(pr == null)
			return false;
		
		//Borramos la cabeza
		pr = pr.get_next();
		return true;
	}
	
	//Devuelve true si se ha borrado el ultimo elemento
	public boolean borraCola()
	{
		//Guardian de vacio
		if(pr == null)
			return false;
		
		//Guardian elemento unico
		if(pr.get_next() == null)
		{
			pr = null;
			return true;		
		}

		//Recorremos hasta el penultimo nodo
		NodoLG aux = pr;
		while(aux.get_next().get_next() != null)
			aux = aux.get_next();
		
		//Lo ponemos a nulo, borrandolo de la lista
		aux.set_next(null);
		return true;
		
	}
	
	//Devuelve la posicion en la lista del ploc con una
	//ciudad con dicho nombre
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion
	{
		//Guardian de nulo y texto valido
		if(v == null || v.equals(""))
			throw new CiudadNoEncontradaExcepcion(v);
		
		//Recorremos la lista entera
		NodoLG aux = pr; //primera posicion
		int indice = 0;  //Contador de posiciones
		while(aux != null)
		{
			//vemos si el nombre coincide
			//?? la ciudad del ploc en la lista y el string pasado por parametro tienen que coincidir con equals, equalsIgnoreCase o tienen que ocupar la misma posicion de memoria los strings?
			if(aux.get_ploc().getCiudad() != null && aux.get_ploc().getCiudad().equalsIgnoreCase(v))
				return indice;
			
			//Punto siguiente en la lista
			aux = aux.get_next();
			indice++;
		}
		
		throw new CiudadNoEncontradaExcepcion(v);
	}
	
	//Devuelve true si ha borrado el ploc con la ciudad
	public boolean borraCiudad(String v)
	{
		//Guardian de nulo y texto valido
		if(v == null || v.equals(""))
			return false;
		
		//guardian de lista vacia
		if(pr == null)
		{
			return false;	
		}else{
			//Vemos si es el primer elemento el correcto
			if(pr.get_ploc().getCiudad() != null && pr.get_ploc().getCiudad().equalsIgnoreCase(v))
			{
				pr = pr.get_next();
				return true;
			}
		}
		
		//Recorremos la lista
		NodoLG aux = pr;
		while(aux.get_next() != null)
		{
			//aux -> next -> next.next ==> si next es el ploc correcto ==> aux --> next.next
			if(aux.get_next().get_ploc().getCiudad() != null 
		    && aux.get_next().get_ploc().getCiudad().equalsIgnoreCase(v))
			{
				aux.set_next(aux.get_next().get_next());
				return true;
			}
			
			//Siguiente elemento
			aux = aux.get_next();
		}
		
		//Llegados aqui todo ha fallado
		return false;
	}
	
	//Devuelve true si ha borrado el ploc con ese pais
	public boolean borraPais(String s)
	{
		//Guardian de nulo y texto valido
		if(s == null || s.equals(""))
			return false;
		
		//guardian de lista vacia
		if(pr == null)
			return false;	
		
		//Recorremos la lista
		NodoLG aux = pr;
		int ocurrencias = 0;
		while(aux.get_next() != null)
		{
			//aux -> next -> next.next ==> si next es el ploc correcto ==> aux --> next.next
			if(aux.get_next().get_ploc().getPais() != null 
		    && aux.get_next().get_ploc().getPais().equalsIgnoreCase(s))
			{
				aux.set_next(aux.get_next().get_next());
				ocurrencias++;
			}
			else{
				//Siguiente elemento
				aux = aux.get_next();
			}
		}
		
		//Comprobamos el primer elemento que no lo hemos hecho
		if(pr.get_ploc().getPais() != null && pr.get_ploc().getPais().equalsIgnoreCase(s))
		{
			pr = pr.get_next();
			return true;
		}
		
		
		//Final del camino, si ha habido alguna ocurrencia se devuelve true, sino false
		return (ocurrencias != 0) ? true : false;
	}
	
	//Devuelve el ploc de dicha posicion
	//?? Si la lista es vacia se devuelve null o se lanza la excepcion?
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException
	{
		//Guardian de vacio
		if(pos < 0)
			throw new IndexOutOfBoundsException(""+pos);
		
		//Recorremos la lista buscando el elementos
		NodoLG aux = pr;
		int contador = 0;
		while(aux != null)
		{
			if(contador == pos)
				return aux.get_ploc();
			
			//salto de elemento
			aux = aux.get_next();
			pos++;
		}
		
		//Si llega aqui y la lista es vacia, se devuelve null
		if(pr == null)
			return null;
		
		//Si llega aqui y la lista no era vacia, la posicion es mayor al tamanyo de la lista
		throw new IndexOutOfBoundsException(""+pos);
	}
	
	//Devuelve los PLocs que pertenecen a ese pais
	public PLoc[] Pais(String p)
	{
		//Guardian de nulo y texto valido
		if(p == null || p.equals(""))
			return null;
		
		
	}
	
	//Ordena el array de PLocs
	public void ordenaLista()
	{
		
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//Clase privada NodoLG
	private class NodoLG
	{
		//Variables privadas del nodo
		private PLoc pd;     //PLoc almacenado aqui
		private NodoLG next; //Nodo siguiente
		private NodoLG prev; //Nodo previo
		
		//Constructor
		public NodoLG()
		{
			pd   = null;
			next = null;
			prev = null;
		}
		
		//Constructor con parametro
		public NodoLG(PLoc p)
		{
			pd   = p;
			next = null;
			prev = null;
		}
		
		//Set y Get
		public void set_next(NodoLG n){ next = n;    } //NEW cambia el nodo siguiente
		public NodoLG get_next()      { return next; } //NEW devuelve el nodo siguiente
		public void set_prev(NodoLG n){ prev = n;    } //NEW cambia el nodo previo
		public NodoLG get_prev()      { return prev; } //NEW devuelve el nodo previo
		public PLoc get_ploc()        { return pd;   } //NEW devuelve el ploc almacenado en este nodo
	}
}
