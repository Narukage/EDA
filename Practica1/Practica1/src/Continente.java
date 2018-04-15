import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Continente {
	//NEW metodo para leer el fichero de nuevo y coger los 6 primeros representantes
	public static void elige_representantes(String f, PLoc[] representantes)
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
        	if( line != null && !line.equals("")) {
	            do{
	            	//Dividimos la linea leida en funcion de sus #
	                String[] datos = line.split("#");
	                
	                //Tiene que tener 5 datos
	                if(datos.length !=5)
	                	break;
	                
	                //?? Un dato vacio al leer el documento, es decir, un string "", se mete como null o como ""?
	                //Saneamos los datos en variables manejables
	                //Comprobamos datos vacios
	                if(datos[0].equals("") || datos[0] == null)
	                	break;
	                
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

                        //Intentamos insertarlo en la lista en el lugar que le corresponde
                        //Orden: AF - NA - SA - AS - EU - OC
                    	String continente = dentro.getContinente();
                    	switch(continente)
                    	{
                        	case "AF":
                        		representantes[0] = dentro;
                        		break;
                        	case "NA":
                        		representantes[1] = dentro;
                        		break;
                        	case "SA":
                        		representantes[2] = dentro;
                        		break;
                        	case "AS":
                        		representantes[3] = dentro;
                        		break;
                        	case "EU":
                        		representantes[4] = dentro;
                        		break;
                        	case "OC":
                        		representantes[5] = dentro;
                        		break;
                    	}

                        //Imprimir algun error por coordenada erronea
                    }catch(CoordenadaExcepcion e){
                        System.out.println(e);
                    }
	                
	                //Leemos la linea para continuar la lectura
	                line=buffered_reader.readLine();
	            }while(line != null && !line.equals(""));
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
	
	//NEW metodo para mostrar los representantes
	public static void mostrar_representantes(PLoc[] representantes)
	{
		//Primer mensaje
		System.out.print("REPRESENTANTES: ");
		if(representantes[0] != null)
		{
			//Si no tiene ciudad, una X
			if(representantes[0].getCiudad() == null || representantes[0].getCiudad().equals(""))
			{
				System.out.print("x");
			}
			else
			{
				System.out.print(representantes[0].getCiudad());
			}
		}
		else
		{
			//Si no existe el representante, un X
			System.out.print("x");
		}
		
		//iteramos entre el resto de representantes
		for(int i = 1; i <representantes.length; i++)
		{
			if(representantes[i] != null)
			{
				if(representantes[i].getCiudad() == null || representantes[i].getCiudad().equals(""))
				{
					System.out.print(" - x");
				}
				else
				{
					System.out.print(" - "+representantes[i].getCiudad());
				}
			}
			else
			{
				System.out.print(" - x");
			}
		}
		//Retorno de carro
		System.out.println("");
	}
	
	//NEW metodo que asigna el continente del representante mas cercano a los PLocs que previamente no tenian uno.
	public static void asigna_representantes_primero(Atlas a, PLoc[] r)
	{
		//Recorremos la matriz de plocs de atlas (sabemos su tamanyo)
		for(int x = 0; x < 181; x++)
		{
			for(int y = 0; y < 361; y++)
			{
				//Verificamos si en dicha posicion hay un PLoc y si no tiene continente
				PLoc candidato = a.devuelvePLoc(x, y);
				if(candidato!= null && (candidato.getContinente()==null 
						|| candidato.getContinente().equals("")))
				{
					//No tiene continente asi que le asignamos esta variable
					candidato.set_no_tenia_continente(true);
					
					//Recorremos los representantes buscando el candidato mas cercano
					double distancia = Double.MAX_VALUE;
					int indice = -1;
					
					for(int i = 0; i < r.length; i++)
					{
						if(r[i]!=null)
						{
							//calculo de distancia
							double calculo = Math.sqrt(
										     Math.pow(candidato.getGps()[0] - r[i].getGps()[0] , 2) 
									       + Math.pow(candidato.getGps()[1] - r[i].getGps()[1] , 2)
									         );
							
							if(distancia > calculo) {
								distancia = calculo;
								indice = i;
							}
						}
					}
					
					//Asignacion de continente
					if(indice != -1)
					{
						candidato.set_continente(r[indice].getContinente());
					}
				}
			}
		}
	}
	
	//NEW metodo que asigna el continente del representante mas cercano a los PLocs que previamente no tenian uno
	//y que no se encuentran en la lista actual de representantes.
	public static void asigna_representantes_segundo(Atlas a, PLoc[] r)
	{
		//Recorremos la matriz de plocs de atlas (sabemos su tamanyo)
		for(int x = 0; x < 181; x++)
		{
			for(int y = 0; y < 361; y++)
			{
				//Verificamos si en dicha posicion hay un PLoc y si no tiene continente
				PLoc candidato = a.devuelvePLoc(x, y);
				if(candidato!= null && candidato.get_no_tenia_continente())
				{
					//Recorremos los representantes buscando el candidato mas cercano
					double distancia = Double.MAX_VALUE;
					int indice = -1;
					
					//Verificamos que no esta en la lista de representantes
					boolean verificacion = true;
					for(int i = 0; i < r.length; i++)
					{
						if(r[i] != null && r[i] == candidato)
						{
							verificacion = false;
						}
					}
					
					if(verificacion)
					{
						for(int i = 0; i < r.length; i++)
						{
							if(r[i] != null)
							{
								//calculo de distancia
								double calculo = Math.sqrt(
											     Math.pow(candidato.getGps()[0] - r[i].getGps()[0] , 2) 
										       + Math.pow(candidato.getGps()[1] - r[i].getGps()[1] , 2)
										         );
								//comprobacion de que la distancia resultante es menor a la que habia con el
								//representante anterior
								if(distancia > calculo) {
									distancia = calculo;
									indice = i;
								}
							}
						}
						
						//Asignacion de continente
						if(indice != -1)
						{
							candidato.set_continente(r[indice].getContinente());
						}
					}
				}
			}
		}
	}
	
	//NEW metodo que calcula la distancia de un PLoc a todos los demas del mismo continente, siendo el que 
	//tenga menor distancia a todos el elegido como nuevo representante
	public static void calculo_k_medianas(Atlas a, PLoc[] r)
	{
		//Array de distancias q mantiene la distancia a todos los demas continentes (inicialmente es maxima)
		double[] distancias = new double[6];
		for(int i = 0; i < 6; i++)
		{
			distancias[i] = Double.MAX_VALUE;
		}
		
		//Recorremos todo el array de atlas cogiendo cada ploc para compararlo con el resto
		for(int x = 0; x < 181; x++)
		{
			for(int y = 0; y < 361; y++)
			{
				//Cogemos cada ploc y comprobamos que existe
				PLoc ploc_actual = a.devuelvePLoc(x, y);
				if(ploc_actual != null && ploc_actual.getContinente()!=null && !ploc_actual.getContinente().equals(""))
				{
					//Distancia total con el resto de PLocs del mismo continente
					int distancia_total = 0;
					
					for(int x2 = 0; x2 < 181; x2++)
					{
						for(int y2 = 0; y2 < 361; y2++)
						{
							//El ploc comparado tiene que existir, no ser iguales, tener continente y que sea el mismo
							PLoc ploc_comparado = a.devuelvePLoc(x, y);
							if(ploc_comparado != null 
								&& ploc_comparado != ploc_actual 
								&& ploc_comparado.getContinente()!=null 
								&& ploc_comparado.getContinente().equals(ploc_actual.getContinente()))
							{
								distancia_total+= Math.sqrt(
									              Math.pow(ploc_actual.getGps()[0] - ploc_comparado.getGps()[0] , 2) 
									            + Math.pow(ploc_actual.getGps()[1] - ploc_comparado.getGps()[1] , 2)
									         );
							}
						}
					}
					
					//Lo asignamos como representante si su distancia total a todos es menor que el actual
					String continente = ploc_actual.getContinente();
                	switch(continente)
                	{
                    	case "AF":
                    		if(distancia_total < distancias[0])
                    		{
                    			distancias[0] = distancia_total;
                    			r[0] = ploc_actual;
                    		}
                    		break;
                    	case "NA":
                    		if(distancia_total < distancias[1])
                    		{
                    			distancias[1] = distancia_total;
                    			r[1] = ploc_actual;
                    		}
                    		break;
                    	case "SA":
                    		if(distancia_total < distancias[2])
                    		{
                    			distancias[2] = distancia_total;
                    			r[2] = ploc_actual;
                    		}
                    		break;
                    	case "AS":
                    		if(distancia_total < distancias[3])
                    		{
                    			distancias[3] = distancia_total;
                    			r[3] = ploc_actual;
                    		}
                    		break;
                    	case "EU":
                    		if(distancia_total < distancias[4])
                    		{
                    			distancias[4] = distancia_total;
                    			r[4] = ploc_actual;
                    		}
                    		break;
                    	case "OC":
                    		if(distancia_total < distancias[5])
                    		{
                    			distancias[5] = distancia_total;
                    			r[5] = ploc_actual;
                    		}
                    		break;
                	}
					
				}
			}
		}
	}
	
	//NEW metodo que ordena todos los PLocs de atlas en arrays por sus continentes, y ordenados
	//segun el orden especificado por sus coordenadas gps
	public static void muestra_ordenado_final(Atlas a)
	{
		//Contamos cuantas ciudades hay de cada tipo
		int[] cantidades = new int[6];
		for(int x = 0; x < 181; x++) { for(int y = 0; y < 361; y++) {
			PLoc contado = a.devuelvePLoc(x, y);
			
			//Miramos que el ploc existe, tiene continente, y no tenia previamente uno
			if(contado != null 
					&& contado.getContinente() != null 
					&& !contado.getContinente().equals("")
					&& contado.get_no_tenia_continente()) {
				
				//Sumamos su aparicion en el lugar del array cantidades que le corresponda
				String continente = contado.getContinente();
            	switch(continente)
            	{
                	case "AF":
                		cantidades[0]++;
                		break;
                	case "NA":
                		cantidades[1]++;
                		break;
                	case "SA":
                		cantidades[2]++;
                		break;
                	case "AS":
                		cantidades[3]++;
                		break;
                	case "EU":
                		cantidades[4]++;
                		break;
                	case "OC":
                		cantidades[5]++;
                		break;
            	}
			}
		}}
		
		//Las insertamos dentro de su array correspondiente
		PLoc[] AF_array = new PLoc[cantidades[0]];
		PLoc[] NA_array = new PLoc[cantidades[1]];
		PLoc[] SA_array = new PLoc[cantidades[2]];
		PLoc[] AS_array = new PLoc[cantidades[3]];
		PLoc[] EU_array = new PLoc[cantidades[4]];
		PLoc[] OC_array = new PLoc[cantidades[5]];
		
		//Recorremos en todos los plocs de atlas
		for(int x = 0; x < 181; x++) { for(int y = 0; y < 361; y++) {
			PLoc metido = a.devuelvePLoc(x, y);
			//Comprobamos que cada ploc existe, tiene continente y no tenia previamente uno
			if(metido != null 
					&& metido.getContinente() != null 
					&& !metido.getContinente().equals("")
					&& metido.get_no_tenia_continente()) {
				
				String continente = metido.getContinente();
				
            	//Lo metemos en el primer hueco libre de cada array
				switch(continente)
            	{
                	case "AF":
                		inserta(metido, AF_array);
                		break;
                	case "NA":
                		inserta(metido, NA_array);
                		break;
                	case "SA":
                		inserta(metido, SA_array);
                		break;
                	case "AS":
                		inserta(metido, AS_array);
                		break;
                	case "EU":
                		inserta(metido, EU_array);
                		break;
                	case "OC":
                		inserta(metido, OC_array);
                		break;
            	}
			}
		}}
		
		//Las ordenamos segun el criterio de grados/minutos/cardinalidad con bubble sort
		ordena_array(AF_array);
		ordena_array(NA_array);
		ordena_array(SA_array);
		ordena_array(AS_array);
		ordena_array(EU_array);
		ordena_array(OC_array);
		
		//Imprimimos secuencialmente
		imprime_array(AF_array);
		imprime_array(NA_array);
		imprime_array(SA_array);
		imprime_array(AS_array);
		imprime_array(EU_array);
		imprime_array(OC_array);
		
	}
	
	//NEW metodo auxiliar para insertar objetos en la primera posicion libre de un array de PLocs
	public static void inserta(PLoc insertar, PLoc[] aqui)
	{
		boolean guardian = true;
		for(int i = 0; i < aqui.length && guardian; i++) {
			if(aqui[i] == null)
			{
				aqui[i] = insertar;
				guardian = false;
			}
		}
	}
	

	//NEW metodo auxiliar para ordenar los arrays en funcion de la su ordinalidad por grados/minutos/posicion
	public static void ordena_array(PLoc[] array)
	{
		//Variable auxiliar para intercambio
		PLoc auxiliar = null;
		
		//Realizamos un bubble sort
		for(int i = 0; i < array.length; i++)
		{
			for(int j = i+1; j < array.length; j++)
			{
				//Funcion comparadora de plocs 2 a 2
				if(!compara_plocs(array[i], array[j])) {
					auxiliar = array[i];
					array[i] = array[j];
					array[j] = auxiliar;
				}
			}
		}
	}
	
	//NEW metodo auxiliar para comparar dos PLocs segun grados/minutos/cardinalidad, devolviendo true si el primero es anterior al segundo
	public static boolean compara_plocs(PLoc primero, PLoc segundo)
	{
		//Facil manejo de variables
		Coordenada primero_latitud  = primero.getLatitud();
		Coordenada primero_longitud = primero.getLongitud();
		Coordenada segundo_latitud  = segundo.getLatitud();
		Coordenada segundo_longitud = segundo.getLongitud();
		
		//Primero en grados es mayor /lat/ = cambiar
		if(primero_latitud.getGrados() > segundo_latitud.getGrados())
			return false;
		
		//Mismos grados /lat/
		if(primero_latitud.getGrados() == segundo_latitud.getGrados())
		{

			//Primero en minutos es mayor /lat/ = cambiar
			if(primero_latitud.getMinutos() > segundo_latitud.getMinutos())
				return false;
			
			//Mismos minutos /lat/
			if(primero_latitud.getMinutos() == segundo_latitud.getMinutos())
			{

				//Primero en posicion /lat/ es N = no cambiar
				if(primero_latitud.getPos() == 'N' && segundo_latitud.getPos() == 'S')
					return true;
				
				//Primero en posicion /lat/ es N = no cambiar
				if(primero_latitud.getPos() == 'S' && segundo_latitud.getPos() == 'N')
					return true;
				
				//Misma posicion /lat/
				if(primero_latitud.getPos() == segundo_latitud.getPos())
				{

					//Primero en grados es mayor /long/ = cambiar
					if(primero_longitud.getGrados() > segundo_longitud.getGrados())
						return false;
					
					//Mismos grados /lon/
					if(primero_longitud.getGrados() == segundo_longitud.getGrados())
					{
						//Primero en minutos es mayor /long/ = cambiar
						if(primero_longitud.getMinutos() > segundo_longitud.getMinutos())
							return false;
						
						//Mismos minutos /lon/
						if(primero_longitud.getMinutos() == segundo_longitud.getMinutos())
						{
							//Misma posicion /long/ = no cambiar
							if(primero_longitud.getPos() == segundo_longitud.getPos())
								return true;
							
							//Primera posicion es O /long/ = no cambiar
							if(primero_longitud.getPos()=='O')
								return true;
							
							//Primera posicion es E /long/ = cambiar
							return false;
						}
					}
				}
			}
		}
		
		//All right
		return true;
	}
	
	//NEW metodo auxiliar para imprimir todos los plocs dentro de un array para el paso final
	public static void imprime_array(PLoc[] array)
	{
		//Imprimimos que continente es
		System.out.println(array[0].getContinente()+": ");
		
		//iteramos entre el resto de representantes
		for(int i = 0; i <array.length; i++)
		{
			if(array[i].getCiudad() != null && !array[i].getCiudad().equals(""))
			{
				System.out.print(array[i].getCiudad()+" ");
			}
			else
			{
				System.out.print("x ");
			}
		}
		//Retorno de carro
		System.out.println("");
	}
		
		
	//MAIN
	public static void main(String[] args) 
	{
		//Atlas
		Atlas atlas = new Atlas();
		
		//Si se ha pasado como parametro un fichero
		if(args.length > 0 && args[0]!=null)
			return;
		
		//leer los datos en atlas
		atlas.leeAtlas(args[0]);
		
		//-----------------PASO 1--------------------
		//Con un metodo auxiliar, leemos de nuevo el fichero y cogemos a los 6 primeros representantes (primeras 6 apariciones distintas de los continentes). Luego lo mostramos con el metodo auxiliar.
		PLoc[] representantes = new PLoc[6];
		elige_representantes(args[0], representantes);
		mostrar_representantes(representantes);
		
		//-----------------PASO 2--------------------
		//Con un metodo auxiliar en atlas, verificamos que plocs tenian continente y les colocamos su representate
		asigna_representantes_primero(atlas, representantes);
		
		//-----------------PASO 3--------------------
		//Se busca el nuevo representante entre los PLocs del mismo continente con un metodo externo
		calculo_k_medianas(atlas, representantes);
		mostrar_representantes(representantes);
		
		//-----------------PASO 4--------------------
		//Reutilizamos el metodo para hacer el mismo paso que el 2 pero verificando que no se calcula con
		//los representantes ya elegidos
		asigna_representantes_segundo(atlas, representantes);
		
		//-----------------PASO 5--------------------
		//Reutilizamos la funcion del calculo de KMedianas anterior para repetir el mismo paso 3
		//Y mostramos los representantes con la misma funcion anterior
		calculo_k_medianas(atlas, representantes);
		mostrar_representantes(representantes);
		
		//-----------------PASO FINAL--------------------
		//Usamos un metodo auxiliar que ordene todos los plocs en el orden especificado para imprimirlos por continente
		muestra_ordenado_final(atlas);
	}	
}
