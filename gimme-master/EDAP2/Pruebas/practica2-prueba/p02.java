/**
* @author Alicia Garrido Alenda
* Se declaran variables de tipo Lista en las que se crean objetos
* VectorG y ListaG, se invoca leeLista de estos objetos y escribeVectorG 
* y escribeListaG con los objetos correspondientes.
* Se crean coordenadas y localidades. Se asigna latitud y longitud a todas las 
* localidades. Se invoca insertaArrayPLoc de los objetos de tipo Lista con las localidades.
* Se invoca escribeVectorG y escribeListaG con los objetos correspondientes.
*/

public class p02{

  private static Coordenada[] creaCoordenadas(int g,int m,char p,int n){
    Coordenada[] creadas=null;
    if(n>0){
      creadas=new Coordenada[n];
      for(int i=0;i<n;i++)
        creadas[i]=new Coordenada(g+i,m+i,p);
    }
    return creadas;
  }
  
  private static PLoc[] creaLocalidades(int n,String conti,String pais){
    PLoc[] creadas=null;
    if(n>0){
      creadas=new PLoc[n];
      String[] ciudad={"Monserrat","Guadalaviar","Fuentegelmes","Villarmero"};
      Coordenada[] lat=p02.creaCoordenadas(39,20,'N',n);
      Coordenada[] lon=p02.creaCoordenadas(0,40,'O',n);
      for(int i=0;i<ciudad.length&&i<n;i++)
        try{
          creadas[i]=new PLoc(conti,pais,ciudad[i]);
          creadas[i].setLatitud(lat[i]);
          creadas[i].setLongitud(lon[i]);
        }
        catch(CoordenadaExcepcion e){
          System.out.println("Ciudad -> "+ciudad[i]+" "+e);
        }
    }
    return creadas;
  }
  
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
     for(int i=0;i<l.length;i++)
       p02.escribe(l[i]);
     PLoc[] llocs=p02.creaLocalidades(4,"EU","Spain");
     for(int i=0;i<l.length;i++)
       l[i].insertaArrayPLoc(llocs);
     for(int i=0;i<l.length;i++)
       p02.escribe(l[i]);
       
   }
   else System.out.println("falta el nombre del fichero");
  }
}
