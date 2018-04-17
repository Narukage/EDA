//DNI 48620792B BARBA ROBLES, ALBERTO
import java.text.*;
import java.util.*;

public class PLoc implements Comparable<PLoc>{
	private String continente;
	private String pais;
	private String ciudad; 
	private Coordenada latitud; //coordenadas latitud en grados 
	private Coordenada longitud; //coordenadas longitud en grados 
	private double[] gps; //coordenadas gps en decimales
	//Variable auxiliar para saber si inicio con continente o no
	private boolean teniaContinente;
	
	public PLoc(){

		continente = null;
		pais = null;
		ciudad= null;
		latitud = null;
		longitud = null;
		gps = null;

	}
	public PLoc(String c, String p, String l){
		if(c == null){
			continente = null;
		}else{
			continente=c;
		}

		if(p == null){
			pais = null;
		}else{
			pais=p;
		}

		if(l == null){
			ciudad = null;
		}else{
			ciudad=l;
		}
		latitud = null;
		longitud = null;
		gps = null;

	}
	public void setLatitud(Coordenada p) throws CoordenadaExcepcion{
		//p es de tipo Coordenada por lo tanto en el constructor de Coordenada tenemos
		//grado minutos y pos, por lo tanto creo 3 booleanos auxiliares para 
		//utilizarlos en la excepcion CoordenadaException
		boolean auxGrado=false;
		boolean auxMinutos=false;
		boolean auxPos=false;
		//Compruebo la validez de la Coordenada
		if(p.getGrados()<0 || p.getGrados()>90 ){
			auxGrado=true;
		}
		if(p.getMinutos()<0 || p.getMinutos()>59 ){
			auxMinutos=true;
		}
		if(p.getPos() != 'N' && p.getPos() != 'S'){
			auxPos=true;
		}
		//en caso de ser verdadero se crea la excepcion
		if(auxGrado == true){								//de tipo COORDENADA
			throw new CoordenadaExcepcion("Latitud grados "+p.getGrados());
			//lanzo la excepcion con el mensaje
		}else{
			//if anidado para mostrar SOLO UN MENSAJE DE ERROR
			if(auxMinutos == true){
				//lanzo la excepcion con el mensaje
				throw new CoordenadaExcepcion("Latitud minutos "+p.getMinutos());
			}else{
				if(auxPos == true){
					//lanzo la excepcion con el mensaje
					throw new CoordenadaExcepcion("Latitud posicion "+p.getPos());
				}	
			}
		}
		//Todo correcto con los datos a la latitud y como nunca sera nulo puedo asignarlo
		//directamente
		latitud=p;
	}
	public void setLongitud(Coordenada p) throws CoordenadaExcepcion{
		//p es de tipo Coordenada por lo tanto en el constructor de Coordenada tenemos
		//grado minutos y pos, por lo tanto creo 3 booleanos auxiliares para 
		//poder utilizarlos de cara a la excepcion CoordenadaException
		boolean auxGrado=false;
		boolean auxMinutos=false;
		boolean auxPos=false;
		//Comprueblo la validez de las coordenadas
		if(p.getGrados()<0 || p.getGrados()>180 ){
			auxGrado=true;
		}
		if(p.getMinutos()<0 || p.getMinutos()>59 ){
			auxMinutos=true;
		}
		if(p.getPos() != 'E' && p.getPos() != 'O'){
			auxPos=true;
		}
		//si es true muestro la excepcion
		if(auxGrado == true){								//p.getGrados porque es de tipo Coordenada
			throw new CoordenadaExcepcion("Longitud grados "+p.getGrados());
			//lanzo la excepcion con el mensaje
		}else{
			//anido para mostrar solo un mensaje de error
			if(auxMinutos == true){
				//lanzo la excepcion con el mensaje
				throw new CoordenadaExcepcion("Longitud minutos "+p.getMinutos());
			}else{
				if(auxPos == true){
					//lanzo la excepcion con el mensaje
					throw new CoordenadaExcepcion("Longitud posicion "+p.getPos());
				}	
			}
		}
		//como nunca sera nulo puedo asignarlo directamente
		longitud=p;
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
		rellenaGps();
		return gps;
	}

	public void escribeInfoGrados(){
		//creo el String para poder mostrar por pantalla los datos
		String f = "";

		if(continente!=null){
			f+=continente+" - ";
		}else{
			//en caso de no cumplirse muestro la x
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
		//termino de completar la informacion para el String f y muestro por pantalla la informacion
		if(latitud!=null){
			f+=latitud.getGrados()+" "+latitud.getMinutos()+" "+latitud.getPos()+" - ";
		}else{
			f+="x - ";
		}
		
		if(longitud!=null){
			f+=longitud.getGrados()+" "+longitud.getMinutos()+" "+longitud.getPos();
		}else{
			f+="x";
		}
		System.out.println(f);
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

		rellenaGps();
		//almaceno los valores en f y lo muestro
		f+=mrf(gps[0])+" - "+mrf(gps[1]);
		System.out.println(f);
	}
	//NEW metodo auxiliar para poder cambiar el continente
	public void setContinente(String a){
		continente=a;
	}
	//NEW metodo auxiliar para poder rellenar el gps
	public void rellenaGps(){
		double num1, num2;
		//variables double para poder almacenar la ifnormacion de los grados tanto para latitud como para longitud
		if(latitud!=null && longitud!=null){
			num1=latitud.getGrados()+(latitud.getMinutos()/60.0);
			num2=longitud.getGrados()+(longitud.getMinutos()/60.0);
			//guardar en memoria latitud y longitud en la variable gps
			gps=new double[2];
			//compruebo si esa posicion esta en S y le asigno el valor negativo
			if(latitud.getPos() == 'S'){
				num1=-num1;
			}		
			//compruebo si esa posicion esta en O y le asigno el valor negativo
			if(longitud.getPos() == 'O'){
				num2=-num2;
			}
			//almaceno la informacion en las distinas posiciones de gps
			gps[0]=num1;
			gps[1]=num2;
		}
	}	
	//NEW METODO AUXILIAR de comprobacion	
	public void changeState(){
		teniaContinente= true;
	}
	//NEW METODO AUXILIAR de comprobacion	
	public boolean getPrueba(){
		return teniaContinente;
	}	
	//NEW METODO AUXILIAR que traduce un double al formato de 2 decimas
	//utilizado en la prueba tres de la practica dos de programacion dos
	public String mrf(double d){
		String convertido= null;
		
		Locale l = new Locale("en");
		DecimalFormatSymbols c = new DecimalFormatSymbols(l);
		DecimalFormat formato = new DecimalFormat("0.00", c);
		
		convertido = formato.format(d);
		
		return convertido;
	}
	//metodo que realiza la ordenacion que se llevara a cabo segun la longitud en orden creciente y alfabetico
	public int compareTo(PLoc p){
		int res=0;
		if(p != null){
			if(p.getGps() != null && gps != null){
				//si la longitud es menor
				if(gps[1] < p.getGps()[1]){					
					res=-1;
				}
				//si la longitud es mayor
				if(gps[1] > p.getGps()[1]){
					res=1;
				}
				//si la longitud son iguales
				if(gps[1] == p.getGps()[1]){
					if(ciudad != null && p.getCiudad() != null){						
						if(ciudad.compareToIgnoreCase(p.getCiudad()) < 0){
							res=-1;
						}
						if(ciudad.compareToIgnoreCase(p.getCiudad()) > 0){
							res=1;
						}
						if(ciudad.compareToIgnoreCase(p.getCiudad()) == 0){
							res=0;
						}
					}
				}
			}
		}
		return res;
	}	
}

