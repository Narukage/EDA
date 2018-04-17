/**
* @author Alicia Garrido Alenda
* Se crea un ArbolG. Se invoca esVacio, leeArbol y recorridoInorden, mostrando
* por pantalla la informacion en cada caso.
*/
import java.util.*;
public class p02{

  public static void main(String[] args){
   if(args.length>0){
     ArbolG tree=new ArbolG();
     boolean buit=tree.esVacio();
     System.out.println("Inicialmente el arbol esta vacio? -> "+buit);
     tree.leeArbol(args[0]);
     buit=tree.esVacio();
     System.out.println("Despues de leer el arbol esta vacio? -> "+buit);
     tree.recorridoInorden();
   }
   else System.out.println("falta el nombre del fichero");
  }
}
