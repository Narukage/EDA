
public class PLoc {
    private String continente;
    private String pais;
    private String ciudad;
    private Coordenada latitud;
    private Coordenada longitud;
    private double[] gps;

    public PLoc(){
        continente=null;
        pais=null;
        ciudad=null;
        latitud=null;
        longitud=null;
        gps=new double[2];
    }

    public PLoc(String c, String p, String l){ //acepta valores nulos
        continente=c;
        pais=p;
        ciudad=l;
        latitud=null;
        longitud=null;
        gps=new double[2];
    }

    public void setLatitud(Coordenada p) throws CoordenadaExcepcion{ //dice que nunca sera nulo pero no te creo Alicia
        if(p!=null){

        }
    }

    public void setLongitud(Coordenada p) throws CoordenadaExcepcion{

    }

    public String getContinente(){
        return continente;
    }

    public String getPais(){
        return pais;
    }

    public String getCiudad(){
        return ciudad;
    }

    public Coordenada getLatitud(){
        return latitud;
    }

    public Coordenada getLongitud(){
        return longitud;
    }

    public double[] getGps(){
        return gps;
    }

    public void escribeInfoGrados(){

    }
}
