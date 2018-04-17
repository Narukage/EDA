/**
*	@author Carlos De La Fuente Torres
*	En esta prueba se revisa el correcto funcionamiento del algoritmo de busqueda por pares, el cual pertenece 
*	a la aplicacion BuscaLocalizacion. El rango que se le pasa como ultimo argumento al main() va 
*	variando entre valores menores que 0, 0 y valores mayores que 0. Tambien se le pasaran valores sin 
*	decimales para ver si la aplicacion sigue haciendo la conversion al tipo double correctamente, comprobando 
*	que se muestren los mensajes y las ciudades correspondientes a cada valor.
*	Colaboración : Francisco Javier Garcia Fernandez
**/


public class p0rango {
	
	public static void main(String[] args){
		String[] args2=new String[4];
	    if(args.length>0){
	      args2[0]=args[0];
	      args2[1]=new String("P");
	      System.out.println("Continente: NA, Lmax: 15.75");
	      System.out.println();
	      args2[2]=new String("NA");
	      args2[3]=new String("15.75");
	      BuscaLocalizacion.main(args2);
	      System.out.println();
	      System.out.println("Continente: AS, Lmax: 28.26");
	      args2[2]=new String("AF");
	      args2[3]=new String("13.25");
	      BuscaLocalizacion.main(args2);
	      System.out.println();
	      System.out.println("Continente: AS, Lmax: 5.86");
	      args2[2]=new String("AS");
	      args2[3]=new String("5.86");
	      BuscaLocalizacion.main(args2);
	      System.out.println();
	      System.out.println("Continente: EU, Lmax: 35.25");
	      args2[2]=new String("EU");
	      args2[3]=new String("3.25");
	      BuscaLocalizacion.main(args2);
	      System.out.println();
	      System.out.println("Continente: AS, Lmax: 10.42");
	      args2[2]=new String("AS");
	      args2[3]=new String("10.42");
	      BuscaLocalizacion.main(args2);
	      System.out.println();
	      System.out.println("Continente: NA, Lmax: 23.65");
	      args2[2]=new String("NA");
	      args2[3]=new String("23.65");
	      BuscaLocalizacion.main(args2);
	      System.out.println();
	      System.out.println("Continente: EU, Lmax: 8.78");
	      args2[2]=new String("EU");
	      args2[3]=new String("8.78");
	      BuscaLocalizacion.main(args2);
	    }
	    else{
	    	System.out.println("falta el nombre del fichero");
	    }
	}
	
}
