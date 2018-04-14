import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Continente {
	//NEW metodo para leer el fichero de nuevo y coger los 6 primeros representantes
	public static void eligeRepresentantes(String f, PLoc[] representantes)
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
	                if(datos[0].equals(""))
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

                        //Intentamos insertarlo en la lista
                        boolean insertado = false;
                        for(int i = 0; i < representantes.length && !insertado; i++)
                        {
                        	if(representantes[i] == null)
                        	{
                        		representantes[i] = dentro;
                        		insertado = true;
                        	}
                        	else
                        	{
                        		
                        	}
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
	public static void mostrarRepresentantes(PLoc[] representantes)
	{
		//Primer mensaje
		System.out.print("REPRESENTANTES: ");
		if(representantes[0] != null)
		{
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
			System.out.print("x");
		}
		
		for(int i = 1; i <representantes.length; i++)
		{
			if(representantes[i] != null)
			{
				if(representantes[i].getCiudad() == null || representantes[i].getCiudad().equals(""))
				{
					System.out.print("x");
				}
				else
				{
					System.out.print(representantes[i].getCiudad());
				}
			}
			else
			{
				System.out.print("x");
			}
		}
		//Retorno de carro
		System.out.println("");
	}
	
	//MAIN
	public static void main(String[] args) {
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
		eligeRepresentantes(args[0], representantes);
		mostrarRepresentantes(representantes);
	}

	
}
