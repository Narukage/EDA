/**
* @author Alicia Garrido Alenda
* Se crea un ArbolG. Se invoca leeArbol y busquedaLejana en direccion NE, invocando
* escribeInfoGps de la localidad devuelta.
*/
import java.util.*;
public class p04{

  public static void main(String[] args){
   if(args.length>0){
     ArbolG tree=new ArbolG();
     tree.leeArbol(args[0]);
     PLoc far=tree.busquedaLejana(new String("NE"));
     if(far!=null)
       far.escribeInfoGps();
   }
   else System.out.println("falta el nombre del fichero");
  }
}
