/**
* @author Alicia Garrido Alenda
* Se declaran variables de tipo Lista en las que se crean objetos
* VectorG y ListaG, se invoca leeLista de estos objetos y escribeVectorG 
* y escribeListaG con los objetos correspondientes.
* Se invoca ciudadEnLista, getPLoc con la posicion que devuelve y Pais con
* el pais de la localidad devuelta de los objetos de tipo Lista. 
* Se invoca escribeInfoGps de las localidades devueltas.
*/

public class p04{

  private static void escribe(Lista una){
    if(una instanceof VectorG)
      ((VectorG)una).escribeVectorG();
    else
      ((ListaG)una).escribeListaG();
  }
  
  private static void listado(PLoc[] v){
    if(v!=null)
     for(int i=0;i<v.length;i++)
       if(v[i]!=null)
         v[i].escribeInfoGps();
  }

  public static void main(String[] args){
   if(args.length>0){
     Lista[] l=new Lista[2];
     String[] ciudad={new String("Buffalo"),new String("Albion")};
     l[0]=new VectorG();
     l[1]=new ListaG();
     for(int i=0;i<l.length;i++)
       l[i].leeLista(args[0]);
     for(int i=0;i<l.length;i++){
       System.out.println("TIPO --------> "+l[i].getClass().getName());
       p04.escribe(l[i]);
     }
     for(int i=0;i<l.length;i++){
      System.out.println("TIPO --------> "+l[i].getClass().getName());
      for(int j=0;j<ciudad.length;j++){
       try{
         int pos=l[i].ciudadEnLista(ciudad[j]);
         System.out.println(ciudad[j]+" en posicion "+pos);
         PLoc trobat=l[i].getPLoc(pos);
         if(trobat!=null){
           PLoc[] relacio=l[i].Pais(trobat.getPais());
           p04.listado(relacio);
         }
       }
       catch(Exception e){
         System.out.println(e);
       }
      }
     }
   }
   else System.out.println("falta el nombre del fichero");
  }
}
