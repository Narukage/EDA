/**
* @author Alicia Garrido Alenda
* Se crean coordenadas y localidades. Se asigna latitud y longitud a todas las 
* localidades. Se declaran variables de tipo Lista en las que se crean objetos
* VectorG y ListaG y se invoca insertaCola de estos objetos con todas las localidades.
* Se invoca escribeVectorG y escribeListaG con los objetos correspondientes.
*/

public class p01{
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
      String[] ciudad={"Vokov","Rodinov","Ctibor","Vesela","Mezna","Polesi","Castrov","Lhota","Libkoda"};
      Coordenada[] lat=p01.creaCoordenadas(49,20,'N',n);
      Coordenada[] lon=p01.creaCoordenadas(15,10,'E',n);
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
    if(una==null)
      System.out.println("Algo falla");
    else if(una instanceof VectorG)
      ((VectorG)una).escribeVectorG();
    else
      ((ListaG)una).escribeListaG();
  }

  public static void main(String[] args){
     PLoc[] llocs=p01.creaLocalidades(9,"EU","Czech Republic");
     if(llocs!=null){
       Lista[] l=new Lista[2];
       l[0]=new VectorG();
       l[1]=new ListaG();
       for(int i=0;i<l.length;i++)
         for(int j=0;j<llocs.length;j++)
            l[i].insertaCola(llocs[j]);
       for(int i=0;i<l.length;i++)
             p01.escribe(l[i]);
     }
  }
}
