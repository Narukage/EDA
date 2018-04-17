import java.util.*;
import java.text.*;

public class PLoc {
	//Variables privadas
    private String continente;
    private String pais;
    private String ciudad;
    private Coordenada latitud;
    private Coordenada longitud;
    private double[] gps;
    //Variable auxiliar para saber si tenia previamente continente
    private boolean no_tenia_continente;
    
    //Constructor
    public PLoc(){
        continente = null;
        pais	   = null;
        ciudad	   = null;
        latitud	   = null;
        longitud   = null;
        gps		   = null;
    }

    //Constructor con parametros
    public PLoc(String c, String p, String l){
        continente = c;
        pais       = p;
        ciudad     = l;
        latitud    = null;
        longitud   = null;
        gps        = null;
    }

    public void setLatitud(Coordenada p) throws CoordenadaExcepcion{
    	//Guardian de nulo
        if(p == null)
        	return;
  
        //Si los grados no estan en el rango, se tira excepcion
        if(p.getGrados()<0 || p.getGrados()>90 )
            throw new CoordenadaExcepcion("Latitud grados "+p.getGrados());

        //Si los minutos no estan en el rango, se tira excepcion
        if(p.getMinutos()<0 || p.getMinutos()>59 )
            throw new CoordenadaExcepcion("Latitud minutos "+p.getMinutos());
        
        //Si la posicion no es norte o sur, se tira excepcion
        if(p.getPos() != 'N' && p.getPos() != 'S')
            throw new CoordenadaExcepcion("Latitud posicion "+p.getPos());

        //Si pasa los guardianes, se guarda
        latitud=p;
    }

    public void setLongitud(Coordenada p) throws CoordenadaExcepcion{
        if(p == null)
        	return;
  
        if(p.getGrados()<0 || p.getGrados()>180 )
            throw new CoordenadaExcepcion("Longitud grados "+p.getGrados());

        if(p.getMinutos()<0 || p.getMinutos()>59 )
            throw new CoordenadaExcepcion("Longitud minutos "+p.getMinutos());
        
        if(p.getPos() != 'E' && p.getPos() != 'O')
            throw new CoordenadaExcepcion("Longitud posicion "+p.getPos());

        //Si pasa los guardianes, se guarda
        longitud=p;
        
    }
    
    
    //Getters
    public String getContinente()  { return continente; }
    public String getPais()        { return pais;       }
    public String getCiudad()      { return ciudad;     }
    public Coordenada getLatitud() { return latitud;    }
    public Coordenada getLongitud(){ return longitud;   }
    public double[] getGps(){
    	//Siempre se genera porque es null en principio
    	if(latitud != null || longitud != null)
    		gps = new double [2];

        //El valor depende de si son lado positivo o negativo
        if(latitud  != null)
        	gps[0] = (latitud.getPos() == 'N')  ? latitud.getGrados() + latitud.getMinutos() / 60.0    : - (latitud.getGrados() + latitud.getMinutos() / 60.0);
        	
        if(longitud != null)
        	gps[1] = (longitud.getPos() == 'E') ? longitud.getGrados() + longitud.getMinutos() / 60.0  : - (longitud.getGrados() + longitud.getMinutos() / 60.0);
        
        return gps;
    }
    
    public void escribeInfoGrados(){
        if(continente == null){
            System.out.print("x - ");
        }
        else{
            System.out.print(continente + " - ");
        }
        if(pais == null){
            System.out.print("x - ");
        }
        else{
            System.out.print(pais + " - ");
        }
        if(ciudad == null){
            System.out.print("x - ");
        }
        else{
            System.out.print(ciudad + " - ");
        }
        if(latitud == null || longitud == null)
        {
        	System.out.println("x - x");
        }
        else {
            System.out.println(latitud.getGrados() + " " +
                    latitud.getMinutos() + " " + latitud.getPos() + " - " +
                    longitud.getGrados() + " " + longitud.getMinutos() + " " + longitud.getPos());        	
        }
    }

    //Metodo que escribe la informacion del gps cogiendo los datos de la variables gps
    public void escribeInfoGps(){
    	//Antes de imprimir. cogemos los valores del gps con el metodo getGps() que ya nos devuelve los double formateados
        gps = getGps();
        
        //Imprimimos secuencialmente al igual que en escribeInfoGrados. Si alguna variable es null, se imprime "x" en su lugar
        if(continente == null){
            System.out.print("x - ");
        }
        else{
            System.out.print(continente + " - ");
        }
        if(pais == null){
            System.out.print("x - ");
        }
        else{
            System.out.print(pais + " - ");
        }
        if(ciudad == null){
            System.out.print("x - ");
        }
        else{
            System.out.print(ciudad + " - ");
        }
        
        //Imprimimos aqui un string formateado por el metodo decimas, que formatea el double a un string con solamente 2 valores despues de la coma
        if(gps != null)
        {
        	System.out.println(decimas(gps[0]) + " - " + decimas(gps[1]));
        }else{
        	System.out.println("x - x");
        }
    }
    
    //NEW metodo para dejar los valores double con 2 decimas
    public String decimas(double g)
    {
    	//Variables necesarias
		Locale l;
		DecimalFormatSymbols c;
		DecimalFormat f;
		
		//Formato con dos valores despues de la coma
		l = new Locale("en");
		c = new DecimalFormatSymbols(l);
		f = new DecimalFormat("0.00", c);
		
		return f.format(g);
    }

   //NEW, metodo setter para hacer la aplicacion
   public void set_continente(String c){ continente=c; }

   //NEW, metodo getter para saber si tenia continente previamente
   public void set_no_tenia_continente(boolean t) { no_tenia_continente = t;    }
   
   //NEW, metodo setter para la variable que dicta si tenia continente previamente a que se le insertara
   public boolean get_no_tenia_continente()       { return no_tenia_continente; }
   
   
}
