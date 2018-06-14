import java.util.*;

public abstract class Arbol {
	
	public abstract void leeArbol(String f);
	public abstract boolean esVacio();
	public abstract boolean inserta (PLoc p);
	public abstract boolean ciudadEnArbol(String v);
	public abstract TreeSet<String> getCiudades(PLoc p);
	public abstract PLoc busquedaLejana(String s);
}