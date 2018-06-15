//DNI 77400533J, MIGUEL HERMIDA CORES
import java.io.*;

public class Atlas {
	//Variables privadas
    private PLoc[][] local;

    //Constructor
    public Atlas(){
        local = new PLoc [181][361];
    }

    //Coloca localidad segun el ploc
    boolean setLocalidad(PLoc p){
        //Guardianes
        if(p==null || p.getLatitud()==null || p.getLongitud()==null)
        	return false;
        	
        //Asignamos posicion
        int x = (p.getLatitud().getPos() == 'N')  ? p.getLatitud().getGrados()+90   : -p.getLatitud().getGrados()+90;
        int y = (p.getLongitud().getPos() == 'E') ? p.getLongitud().getGrados()+180 : -p.getLongitud().getGrados()+180;

        //Guardianes de posicion: comprobamos q esta dentro de la matriz y que la posicion esta vacia
        if(x < 0 || x > local.length || y < 0 || y > local[0].length)
        	return false;
        
        if(local[x][y] != null)
        	return false;
        
        //Asignacion
        local[x][y] = p;
        
        return true;
    }

    public void leeAtlas(String f){
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
                    
	                Coordenada latitud_coordenada  = new Coordenada(Integer.parseInt(latitud[0]),  Integer.parseInt(latitud[1]),  latitud[2].charAt(0));
	                Coordenada longitud_coordenada = new Coordenada(Integer.parseInt(longitud[0]), Integer.parseInt(longitud[1]), longitud[2].charAt(0));
                    try {
                    	//Creamos el ploc a insertar
                    	PLoc dentro = new PLoc(datos[0], datos[1], datos[2]);
                    	
                    	//y le pasamos las coordenadas
                        dentro.setLatitud(latitud_coordenada);
                        dentro.setLongitud(longitud_coordenada);

                        //metemos el ploc en la matriz
                        this.setLocalidad(dentro);

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

    public String consultaAtlas(PLoc f){
    	//Un ploc valido
    	if(f == null)
    		return null;
    	
    	//Con coordenadas
    	if(f.getLongitud() == null || f.getLatitud() == null) 
    		return null;

        //Cogemos la posicion de la matriz
        int x = (f.getLatitud().getPos() == 'N')  ? f.getLatitud().getGrados()+90   : -f.getLatitud().getGrados()+90;
        int y = (f.getLongitud().getPos() == 'E') ? f.getLongitud().getGrados()+180 : -f.getLongitud().getGrados()+180;

        //Guardianes de posicion: comprobamos q esta dentro de la matriz y que la posicion esta vacia
        if(x < 0 || x >= local.length || y < 0 || y >= local[0].length)
        	return null;
        
        //Si no hay nada, devuelve nulo
        if(local[x][y] == null)
        	return null;
    	
        return local[x][y].getCiudad();
    }

    public void muestraAtlasParcial(PLoc f, int n){
    	//Un ploc valido con n positivo
    	if(f == null || f.getLongitud() == null || f.getLatitud() == null || n < 0)
    	{
    		System.out.println("NO ES DE ESTE PLANETA");
    		return;
    	}

    	//Cogemos la posicion de la matriz
        int x = (f.getLatitud().getPos() == 'N')  ? f.getLatitud().getGrados()+90   : -f.getLatitud().getGrados()+90;
        int y = (f.getLongitud().getPos() == 'E') ? f.getLongitud().getGrados()+180 : -f.getLongitud().getGrados()+180;

        //Guardianes de posicion: comprobamos q esta dentro de la matriz y que la posicion esta vacia
        if(x < 0 || x >= local.length || y < 0 || y >= local[0].length)
        {
        	System.out.println("NO ES DE ESTE PLANETA");
    		return;
        }
        	
        //Recorremos el subarea
        for(int i = -(n)+x; i < n+1+x; i++)
        {
        	for(int j = -(n)+y; j < n+1+y; j++)
        	{
        		//Si esta fuera del area, solo se imprimen los puntos
        		if(i < 0 || j > local.length || j < 0 || j >= local[0].length)
        		{
        			System.out.print(".");
        		}
        		else 
        		{
        			//Si no hay PLoc, punto
        			if(local[x][y]==null)
        			{
        				System.out.print(".");
        			}
        			else
        			{
        				//Si la ciudad es nulo o vacio, punto
        				if(local[x][y].getCiudad() == null || local[x][y].getCiudad().equals(""))
        				{
        					System.out.print(".");
        				}
        				else
        				{
        					//Y finalmente el valor autentico
        					if(local[i][j]!=null) {
        						System.out.print(local[i][j].getCiudad().charAt(0));
        					}else {
        						System.out.print(".");
        					}
        				}
        			}
        		}
        	}
        	
        	//Retorno de carro
        	System.out.println("");
        }
    }
    
    //NEW metodo usado por la clase Continente: devuelve el PLoc de la posicion indicada
    public PLoc devuelvePLoc(int x, int y)
    {
    	return local[x][y];
    }
    
}
