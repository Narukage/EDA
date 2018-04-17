//DNI 48620792B BARBA ROBLES, ALBERTO

public interface Lista {
	
	public abstract void leeLista(String f);
	public abstract boolean esVacia();
	public abstract void insertaCabeza(PLoc p);
	public abstract void insertaCola(PLoc v);
	public abstract void insertaArrayPLoc(PLoc[] v);
	public abstract boolean borraCabeza();
	public abstract boolean borraCola();
	public abstract int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion;
	public abstract boolean borraCiudad(String v);
	public abstract boolean borraPais(String s);
	public abstract PLoc getPLoc(int pos) throws IndexOutOfBoundsException;
	public abstract PLoc[] Pais(String p);
	public abstract void ordenaLista();	
	
}
