/**
* @author Alicia Garrido Alenda
* Se crea un ArbolS. Se invoca esVacio, leeArbol y getPaises, mostrando
* por pantalla la informacion devuelta en cada caso.
*/
import java.util.*;
public class p01{

  public static void main(String[] args){
   if(args.length>0){
     ArbolS tree=new ArbolS();
     boolean buit=tree.esVacio();
     System.out.println("Inicialmente el arbol esta vacio? -> "+buit);
     tree.leeArbol(args[0]);
     buit=tree.esVacio();
     System.out.println("Despues de leer el arbol esta vacio? -> "+buit);
     Set<String> paises=tree.getPaises();
     System.out.println("Se han encontrado los paises:");
     for(String pais : paises)
       System.out.println(pais);
   }
   else System.out.println("falta el nombre del fichero");
  }
}
