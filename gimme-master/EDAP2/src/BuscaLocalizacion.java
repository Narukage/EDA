//DNI 48620792B BARBA ROBLES, ALBERTO

public class BuscaLocalizacion {

	public static void main(String[] args) {
		// Tiene que tener 4 parametros si o si
		if(args.length==4){
			//El segundo debe de ser un unico char
			//0(n*log(n))
			if(args[1].length()==1){
				//Si el char es R, realizo la busqueda por rango
				if(args[1].charAt(0)=='R'){					
					//Creo el vector
					VectorG vector = new VectorG();								
					//Leo el fichero
					vector.leeLista(args[0]);					
					//ordeno el vector
					vector.ordenaLista();					
					//si el rango es negativo, imprimo el mensaje
					if(Double.parseDouble(args[3])<0){
						System.out.println("EL VALOR DEL RANGO ES INCORRECTO");
					}else{
						//Si es 0, busco el mas cercano a la longitud
						if(Double.parseDouble(args[3])==0){							
							//metodo auxiliar que invoca escribeInfoGPS() sobre el PLoc mas cercano a la longitud
							//pasada por parametro
							vector.imprimeCercano(Double.parseDouble(args[2]));							
						//y sino, significa que es positivo y muestro todas las ciudades		
						}else{
							//metodo auxiliar que invoca escribeInfoGPS() sobre el PLoc todos los PLocs cercanos a L
							//dentro del rango, y por orden.
							vector.imprimeCercanos(Double.parseDouble(args[2]), Double.parseDouble(args[3]));							
						}							
					}
				}				
				//Si es P, realizo la busqueda por pares
				//0(n*n)
				if(args[1].charAt(0)=='P'){					
					//Creo la lista
					ListaG lista = new ListaG();
					//Si el rango es positivo, realizo todo el meollo
					if(Double.parseDouble(args[3])>0){
					//Leo el fichero
					lista.leeLista(args[0]);					
					//ordeno la lista
					lista.ordenaLista();										
						//Utilizo el metodo auxiliar para imprimirlo todo
						lista.imprimeFronteras(args[2], Double.parseDouble(args[3]));						
					}else{
						//si LMax es negativo o 0 imprimo el mensaje
						System.out.println("EL VALOR DE LMAX ES INCORRECTO");
					}
				}
			}
		}
	}
}
