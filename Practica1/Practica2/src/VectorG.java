import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;

public class VectorG implements Lista{
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	//Metodos de la interfaz
	
	//lee el documento pasado por parametro y genera
	//la lista de plocs
	public void leeLista(String f)
	{
		
	}
	
	//indica si la lista esta vacia
	public boolean esVacia()
	{
		return (pr == null) ? true : false;
	}
	
	//Inserta al principio el ploc
	public void insertaCabeza(PLoc p)
	{
		
	}
	
	//Inserta al final el ploc
	public void insertaCola(PLoc v)
	{
		
	}
	
	//Devuelve true si ha borrado el primer elemento
	public boolean borraCabeza()
	{
		
	}
	
	//Devuelve true si se ha borrado el ultimo elemento
	public boolean borraCola()
	{
		
	}
	
	//Devuelve la posicion en la lista del ploc con una
	//ciudad con dicho nombre
	public int ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion
	{
		
	}
	
	//Devuelve true si ha borrado el ploc con la ciudad
	public boolean borraCiudad(String v)
	{
		
	}
	
	//Devuelve true si ha borrado el ploc con ese pais
	public boolean borraPais(String s)
	{
		
	}
	
	//Devuelve el ploc de dicha posicion
	public PLoc getPLoc(int pos) throws IndexOutOfBoundsException
	{
		
	}
	
	//Devuelve los PLocs que pertenecen a ese pais
	public PLoc[] Pais(String p)
	{
		
	}
	
	//Ordena el array de PLocs
	public void ordenaLista()
	{
		
	}
}
