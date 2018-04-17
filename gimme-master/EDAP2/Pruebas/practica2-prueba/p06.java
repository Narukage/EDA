
public class p06 {
	public static void main(String[] args) {
		if (args.length > 0) {
			Lista lista = new ListaG();
			lista.leeLista(args[0]);
			((ListaG) lista).escribeListaG();
			try {
				System.out.println(lista.ciudadEnLista("Melbourne"));
				System.out.println(lista.ciudadEnLista("juju"));
			} catch (CiudadNoEncontradaExcepcion e) {
				System.out.println(e);
			}
			try {
				System.out.println(lista.getPLoc(0).getCiudad());
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				lista.getPLoc(-1);
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				lista.getPLoc(19);
			} catch (Exception e) {
				System.out.println(e);
			}

			Lista vector = new VectorG();
			vector.leeLista(args[0]);
			((VectorG) vector).escribeVectorG();
			try {
				System.out.println(vector.ciudadEnLista("Melbourne"));
				System.out.println(vector.ciudadEnLista("juju"));
			} catch (CiudadNoEncontradaExcepcion e) {
				System.out.println(e);
			}
			try {
				System.out.println(vector.getPLoc(0).getCiudad());
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				vector.getPLoc(-1);
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				vector.getPLoc(19);
			} catch (Exception e) {
				System.out.println(e);
			}
		} else
			System.out.println("falta el nombre del fichero");
	}
}
