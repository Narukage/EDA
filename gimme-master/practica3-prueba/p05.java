/**
* @author Alicia Garrido Alenda
* Se invoca la aplicacion BuscaLocalizacion2 pasandole dos parametros, de manera que
* busca un pais que se encuentra en el arbol.
*/
import java.util.*;
public class p05{

  public static void main(String[] args){
   if(args.length>0){
     String[] newargs=new String[2];
     newargs[0]=args[0];
     newargs[1]=new String("Uganda");
     BuscaLocalizacion2.main(newargs);
   }
   else System.out.println("falta el nombre del fichero");
  }
}
