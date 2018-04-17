/**
* @author Alicia Garrido Alenda
* Se invoca la aplicacion BuscaLocalizacion para que realice una busqueda
* por rango, una vez con rango 0 y longitud positiva, y otra con rango negativo.
*/

public class p05{
  public static void main(String[] args){
    String[] args2=new String[4];
    if(args.length>0){
      args2[0]=args[0];
      args2[1]=new String("R");
      args2[2]=new String("12.0");
      args2[3]=new String("0.0");
      BuscaLocalizacion.main(args2);
      args2[3]=new String("-1.0");
      BuscaLocalizacion.main(args2);
    }
    else System.out.println("falta el nombre del fichero");
  }
}

