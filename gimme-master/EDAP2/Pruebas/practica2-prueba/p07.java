
public class p07 {
	public static void main(String[] args) {
		if (args.length > 0) {
			PLoc ciudadABorrar;

			Lista lista = new ListaG();
			lista.leeLista(args[0]);
			((ListaG) lista).escribeListaG();
			ciudadABorrar = lista.getPLoc(0);
			System.out.println(lista.borraCiudad(ciudadABorrar.getCiudad()));
			System.out.println(lista.borraCiudad(ciudadABorrar.getCiudad()));
			((ListaG) lista).escribeListaG();

			Lista vector = new VectorG();
			vector.leeLista(args[0]);
			((VectorG) vector).escribeVectorG();
			ciudadABorrar = vector.getPLoc(0);
			System.out.println(vector.borraCiudad(ciudadABorrar.getCiudad()));
			System.out.println(vector.borraCiudad(ciudadABorrar.getCiudad()));
			((VectorG) vector).escribeVectorG();
		} else
			System.out.println("falta el nombre del fichero");
	}
}
