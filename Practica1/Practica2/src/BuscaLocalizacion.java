import java.util.ArrayList;

public class BuscaLocalizacion {

	public static void main(String[] args) {
		//Guardian de nulo
		if(args == null)
			return;
		
		//Guardian de tamanyo (4 elementos por consola)
		if(args.length != 4)
			return;
		
		//Guardian ruta
		if(args[0].lenght() <= 0)
			return;
		
		//Guardian de concrecion. El segundo elemento es un char.
		if(args[1].lenght() != 1)
			return;
		
		//Prueba de rango
		if(args[1].charAt(0) == 'R')
		{
			//Guardian de valor correcto
			if(Double.parseDouble(args[3]) < 0){
				System.out.println("EL VALOR DEL RANGO ES INCORRECTO");
				return;
			}
			
			//Leemos el documento sobre el vector
			VectorG v = new VectorG();
			v.leeLista(args[0]);
			
			//Guardian de vacio
			if(v.esVacia())
				return;
			
			//Ordenamos los PLocs por su longitud con el metodo ordenaLista
			v.ordenaLista();
			
			//Variables de uso posterior
			double longitud = Double.parseDouble(args[2]);
			double rango = Double.parseDouble(args[3]);
			
			//Operacion de rango 0
			if(rango == 0)
			{
				
				//Cogemos el vector de PLocs ordenado por longitud
				ArrayList<PLoc> vector = v.get_vector();
				
				//Realizamos una busqueda binaria
				int izquierda = 0;
				int derecha = vector.size()-1;
				int actual = vector.size()/2;
				
				//?? En la busqueda por rango
				while(izquierda != derecha)
				{
					if(longitud > vector.get(actual).getGPS()[1])
					{
						izquierda = actual;
						actual = (derecha + izquierda) / 2;
					}
					
					if(longitud < vector.get(actual).getGPS()[1])
					{
						derecha = actual;
						actual = (derecha + izquierda) / 2;
					}
					
					if(longitud == vector.get(actual).getGPS()[1])
					{
						izquierda = derecha;
					}
				}
				
				//Imprimimos el valor de la posicion actual, que sera la mas cercana
				vector.get(actual).escribeInfoGPS();
				
			}
			
			//Operacion de clasificacion de rango normal
			if(rango != 0)
			{
				
				//Array auxiliar para almacenar los PLocs dentro del rango
				ArrayList<PLoc> auxiliar = new  ArrayList<PLoc>();
				
				//Recorremos la lista de PLocs
				for(int i = 0; i < vector.size(); i++)
				{
					//Verificamos que esten dentro del rango
					if(Math.abs(vector.get(i).getGPS()[1] - longitud) <= rango)
					{
						//Si esta vacio se inserta sin mas
						if(auxiliar.isEmpty())
						{
							auxiliar.add(vector.get(i));
						}
						
						//Si no esta vacio lo colocamos ordenado
						boolean ploc_colocado = false;
						for(int j = 0; j < auxiliar.size() && !ploc_colocado; j++)
						{
							//Si es menor que el elemento actual, lo anyadimos en este sitio, empujando el resto hacia atras
							if(Math.abs(vector.get(i).getGPS()[1] - longitud) < Math.abs(auxiliar.get(j).getGPS()[1] - longitud))
							{
								auxiliar.add(vector.get(i), j);
								ploc_colocado = true;
							}
						}
						
						//Llegados aqui no ha sido colocado, y se anyade al final
						if(!ploc_colocado)
							auxiliar.add(vector.get(i));
					}
				}
				
				//Guardian de vacio (no hay ninguna ciudad en el rango
				if(auxiliar.isEmpty())
					System.out.println("NO HAY CIUDADES EN ESE RANGO");
				
				//Imprimimos las ciudades en orden
				for(int i = 0; i < auxiliar.size(); i++)
				{
					System.out.println(auxiliar.get(i).getCiudad());
				}
			}
		}
		
		//Prueba de pares
		if(args[1].charAt(0) == 'P')
		{
			//Variables de uso posterior
			double LMAX = Double.parseDouble(args[3]);
			
			//Guardian de LMAX
			if(LMAX <= 0)
			{
				System.out.println("EL VALOR DE LMAX ES INCORRECTO");
				return;
			}
			
			//Leemos la informacion en una listaG
			ListaG lista = new ListaG();
			lista.leeLista(args[0]);
			
			//Guardian de vacio
			if(lista.esVacia())
				return;
			
			//Recoger array con todos los PLocs del continente
			PLoc[] mismo_continente = lista.del_continente(args[2]);
			
			//Verificador de mas de un pais en el continente
			if(mismo_continente.length <= 1)
			{
				System.out.println("NO HAY FRONTERAS INTERCONTINENTALES");
				return;
			}
			
			//Mensaje obligatorio
			System.out.println("CIUDADES FRONTERIZAS");
			
			//Recorremos las ciudades del mismo continente asignadoles en una variable interna a su companyero mas cercano de otro pais
			for(int i = 0; i < mismo_continente.length ; i++)
			{
				for(int j = 0; j < mismo_continente.length; j++)
				{
					if(mismo_continente[i].get_ploc_cercano() == null)
				}
			}
			
			//?? Un ejemplo exacto de la salida, se debe de mostrar el pais entre parentesis como pone en el ejemplo?
			//?? Se mostrara lo de ciudades fronterizas siempre incluso si solo hay un PLoc en el continente? Y si es asi, que mensaje va primero, el de no hay fronteras intercontinentales
			//o el de ciudades fronterizas
			//?? Si hay mas de una PLoc en el continente pero son del mismo pais se imprime el mensaje de no hay fronteras intercontinentales?
			//??Si la pareja es mutua el orden alfabetico del orden se tienen en cuenta las mayusculas?
			//??Los paises deben de ser distintos con equals o con equalsIgnoreCase
			//??Y si no tienen pais se compara de la misma manera? Si el pais es nulo o "", se imprime "null" como pais o solamente ""?
			//??Las ciudades fronterizas mutuas se imprimen solamente en esta seccion o tambien en las fronterizas normales? Y si se ponen en las fronterizas normales
			//tienen que salir ambas, y si es asi en que orden salen las parejas? Y si solo sale una vez en ciudades fronterizas cual va primero, se aplica el orden alfabetico?
			
		}
		
	}
}
