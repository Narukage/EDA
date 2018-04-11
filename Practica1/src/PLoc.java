import java.util.*;
import java.text.*;

public class PLoc {
    private String continente;
    private String pais;
    private String ciudad;
    private Coordenada latitud;
    private Coordenada longitud;
    private double[] gps;
    //Variable auxiliar para saber si inicio con continente o no
    private boolean teniaContinente;

    public PLoc(){
        continente=null;
        pais=null;
        ciudad=null;
        latitud=null;
        longitud=null;
        gps=null;
    }

    public PLoc(String c, String p, String l){ //acepta valores nulos
        continente = c;
        pais = p;
        ciudad=l;
        latitud=null;
        longitud=null;
        gps=null;
    }

    public void setLatitud(Coordenada p) throws CoordenadaExcepcion{ //dice que nunca sera nulo pero no te creo Alicia
        if(p!=null){

            boolean auxGrado=false;
            boolean auxMinutos=false;
            boolean auxPos=false;

            if(p.getPos() != 'N' && p.getPos() != 'S'){
                auxPos=true;
            }

            if(p.getMinutos()<0 || p.getMinutos()>59 ){
                auxMinutos=true;
            }

            if(p.getGrados()<0 || p.getGrados()>90 ){
                auxGrado=true;
            }

            if(auxGrado == true){
                throw new CoordenadaExcepcion("Latitud grados "+p.getGrados());
            }else{
                if(auxMinutos == true){
                    throw new CoordenadaExcepcion("Latitud minutos "+p.getMinutos());
                }else{
                    if(auxPos == true){
                        throw new CoordenadaExcepcion("Latitud posicion "+p.getPos());
                    }
                }
            }
            latitud=p;
        }
    }

    public void setLongitud(Coordenada p) throws CoordenadaExcepcion{
        if(p!=null){
            boolean auxGrado=false;
            boolean auxMinutos=false;
            boolean auxPos=false;

            if(p.getGrados()<0 || p.getGrados()>180 ){
                auxGrado=true;
            }
            if(p.getMinutos()<0 || p.getMinutos()>59 ){
                auxMinutos=true;
            }
            if(p.getPos() != 'E' && p.getPos() != 'O'){
                auxPos=true;
            }
            if(auxGrado == true){
                throw new CoordenadaExcepcion("Longitud grados "+p.getGrados());
            }else{
                if(auxMinutos == true){
                    throw new CoordenadaExcepcion("Longitud minutos "+p.getMinutos());
                }else{
                    if(auxPos == true){
                        throw new CoordenadaExcepcion("Longitud posicion "+p.getPos());
                    }
                }
            }
            longitud=p;
        }
    }

    public String getContinente(){
        return continente;
    }

    public String getPais(){
        return pais;
    }

    public String getCiudad(){ return ciudad; }

    public void setContinente(String c){
        if(c!=null){
            continente=c;
        }
    }

    public void setPais(String p){
        if(p!=null){
            pais=p;
        }
    }

    public void setCiudad(String c){
        if(c!=null){
            ciudad=c;
        }
    }

    public Coordenada getLatitud(){
        return latitud;
    }

    public Coordenada getLongitud(){
        return longitud;
    }

    public double[] getGps(){
        gps = new double [2];

        gps[0] = latitud.getGrados() + latitud.getMinutos() / 60.0;
        if(latitud.getPos() == 'S'){
            gps[0] = -gps[0];
        }
        gps[1] = longitud.getGrados() + longitud.getMinutos() / 60.0;
        if(longitud.getPos() == 'O'){
            gps[1] = -gps[1];
        }
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
        System.out.println(latitud.getGrados() + " " +
                latitud.getMinutos() + " " + latitud.getPos() + " - " +
                longitud.getGrados() + " " + longitud.getMinutos() + " " + longitud.getPos());
    }

    public void escribeInfoGps(){

        String f = "";

        if(continente!=null){
            f+=continente+" - ";
        }else{
            f+="x - ";
        }

        if(pais!=null){
            f+=pais+" - ";
        }else{
            f+="x - ";
        }

        if(ciudad!=null){
            f+=ciudad+" - ";
        }else{
            f+="x - ";
        }

        gps = new double [2];

        gps[0] = latitud.getGrados() + latitud.getMinutos() / 60.0;
        if(latitud.getPos() == 'S'){
            gps[0] = -gps[0];
        }
        gps[1] = longitud.getGrados() + longitud.getMinutos() / 60.0;
        if(longitud.getPos() == 'O'){
            gps[1] = -gps[1];
        }

        f+=mrf(gps[0])+" - "+mrf(gps[1]);
        System.out.println(f);
    }
}
