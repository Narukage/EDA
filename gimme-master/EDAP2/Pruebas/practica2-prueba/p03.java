/**
* @author Alicia Garrido Alenda
* Se declaran variables de tipo Lista en las que se crean objetos
* VectorG y ListaG, se invoca leeLista de estos objetos y escribeVectorG 
* y escribeListaG con los objetos correspondientes.
* Se invoca borraCola y borraCabeza de los objetos de tipo Lista mientras 
* la lista no este vacia, invocando escribeVectorG y escribeListaG con 
* los objetos correspondientes.
*/

public class p03{

  private static void escribe(Lista una){
    if(una instanceof VectorG)
      ((VectorG)una).escribeVectorG();
    else
      ((ListaG)una).escribeListaG();
  }

  public static void main(String[] args){
   if(args.length>0){
     Lista[] l=new Lista[2];
     l[0]=new VectorG();
     l[1]=new ListaG();
     for(int i=0;i<l.length;i++)
       l[i].leeLista(args[0]);
     for(int i=0;i<l.length;i++){
       System.out.println("TIPO --------> "+l[i].getClass().getName());
       p03.escribe(l[i]);
     }
     for(int i=0;i<l.length;i++){
       System.out.println("TIPO --------> "+l[i].getClass().getName());
       while(!l[i].esVacia()){
         l[i].borraCabeza();
         l[i].borraCola();
         p03.escribe(l[i]);
       }
     }
       
   }
   else System.out.println("falta el nombre del fichero");
  }
}
