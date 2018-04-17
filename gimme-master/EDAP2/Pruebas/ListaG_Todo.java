/**
 * Prueba exaustiva para ListaG
 * 
 * @author yupipi93 *
 */
public class ListaG_Todo {
	public static void main(String[] args) throws InterruptedException {
		Coordenada cLat = new Coordenada(20, 29, 'N');
		Coordenada cLon = new Coordenada(4, 38, 'O');
		PLoc p1 = new PLoc("W", "Y", "Z");
		PLoc p2 = new PLoc("A", "B", "C");

		try {
			p1.setLatitud(cLat);
			p1.setLongitud(cLon);
			p2.setLatitud(cLat);
			p2.setLongitud(cLon);
		} catch (CoordenadaExcepcion e) {
			System.out.println(e);
		}

		ListaG lG1 = new ListaG();
		PLoc[] asd = { p1, p2};

		// OK! --- leeLista(String f);
		// OK! --- esVacia();
		// OK! --- insertaCabeza(PLoc p);
		// OK! --- insertaCola(PLoc v);
		// OK! --- insertaArrayPLoc(PLoc[] v);
		// OK! --- borraCabeza();
		// OK! --- borraCola();
		// OK! --- ciudadEnLista(String v) throws CiudadNoEncontradaExcepcion;
		// OK! --- borraCiudad(String v);
		// OK! --- borraPais(String s);
		// OK! --- getPLoc(int pos) throws IndexOutOfBoundsException;
		// OK! --- Pais(String p);
		// OK! --- ordenaLista();
		// OK! --- escribeVectorG();

		printR("PRUEBA LISTAG", 2);
		printR("LEYENDO POBLACIONES:\n", 1);

		lG1.leeLista("pruebaloTodoJodido.dat");

		printR("LECTURA 	-->		OK!", 0);

		p_esVacia(lG1);
		p_insertaCabeza_ciudadEnLista(lG1, p1);		
		p_insertaCola_getPLoc(lG1, p2);
		p_borraCabeza(lG1, p1);
		p_borraCola(lG1, p2);
		p_insertaArrayPLoc(lG1, asd);
		p_borraCiudad(lG1, p1);
		p_borraPais(lG1, p2);
		p_getPLoc(lG1, p1);
		p_Pais(lG1);
		p_ordenaLista(lG1);
		p_escribeListaG(lG1);

	}

	private static void p_esVacia(ListaG lG1) throws InterruptedException {
		if (!lG1.esVacia())
			printR("LISTA 		-->		OK!", 0);
		else
			printR("ERROR, LISTA VACIA!! O MALA IMPLEMENTACION EN <esVacia()>", 0);
	}

	private static void p_insertaCabeza_ciudadEnLista(ListaG lG1, PLoc p1) throws InterruptedException {
		lG1.insertaCabeza(p1);
		boolean ret = false;
		try {
			int pos = lG1.ciudadEnLista("Z");
			if (pos == 0) {
				ret = true;
				System.out.print("E. Controlada --> ");
				pos = lG1.ciudadEnLista(null);
			} else {
				printR("ERROR EN <insertaCabeza()> O/Y EN <ciudadEnLista()>", 0);
			}

		} catch (IndexOutOfBoundsException | CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
			if (ret) {
				printR("INSERTAR CABEZA -->		OK!", 0);
				printR("CIUDAD EN lISTA -->		OK!", 0);
			} else {
				printR("ERROR EN <insertaCabeza()> O/Y EN <ciudadEnLista()>", 0);
			}
		}

	}

	private static void p_insertaCola_getPLoc(ListaG lG1, PLoc p2) throws InterruptedException {
		lG1.insertaCola(p2);
		boolean ret = false;
		try {	
			int pos = lG1.ciudadEnLista("C");// NO FUNCIONA
			PLoc p0 = lG1.getPLoc(pos);
			if (p0.equals(p2)) {
				ret = true;
				System.out.print("E. Controlada --> ");
				pos = lG1.ciudadEnLista(null);
				p0 = lG1.getPLoc(-1);//Le peta a alicia
			}
		} catch (IndexOutOfBoundsException | CiudadNoEncontradaExcepcion e) {
			System.out.println(e);

		} finally {
			if (ret) {
				printR("INSERTAR COLA 	-->		OK!", 0);
				printR("GET PLOC 	-->		OK!", 0);				
			} else {
				printR("ERROR EN <insertaCola()> O/Y EN <getPLoc()>", 0);
			}
		}

	}

	private static void p_borraCabeza(ListaG lG1, PLoc p1) throws InterruptedException {
		boolean ret = false;
		if (lG1.borraCabeza()) {
			PLoc p0 = lG1.getPLoc(0);
			if (!p0.equals(p1)) {
				printR("BORRA CABEZA 	-->		OK!", 0);
				ret = true;
			}
		}
		if (!ret) {
			printR("ERROR EN <borraCabeza()>", 0);
		}
	}

	private static void p_borraCola(ListaG lG1, PLoc p2) throws InterruptedException {
		boolean ret = false;
		int pos = -1;
		try {
			pos = lG1.ciudadEnLista(p2.getCiudad());
		} catch (CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
		}
		if (lG1.borraCola()) {
			try {
				if (lG1.getPLoc(pos).equals(p2)) {
					ret = false;// Esto tiene que dar Exception
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.print("E. Controlada --> ");
				System.out.println(e);
				ret = true;
			}
		}

		if (ret) {
			printR("BORRA COLA 	-->		OK!", 0);
		} else {
			printR("ERROR EN <borraCola()>", 0);
		}
	}

	private static void p_insertaArrayPLoc(ListaG lG1, PLoc[] asd) throws InterruptedException {
		boolean ret = false;

		if (!lG1.borraCiudad(asd[0].getCiudad())) {
			if (!lG1.borraCiudad(asd[1].getCiudad())) {
				lG1.insertaArrayPLoc(asd);
				if (lG1.borraCiudad(asd[0].getCiudad())) {
					if (lG1.borraCiudad(asd[1].getCiudad())) {						
						ret = true;
					}
				}
			}
		}
		if (ret) {
			printR("INSERTA ARRAY 	-->		OK!", 0);
		} else {
			printR("ERROR POSIBLE EN <insertaArrayPLoc()>", 0);
		}
	}

	private static void p_borraCiudad(ListaG lG1, PLoc p1) throws InterruptedException {
		boolean ret = false;
		if (!lG1.borraCiudad(p1.getCiudad())) {
			lG1.insertaCola(p1);
			if (lG1.borraCiudad(p1.getCiudad())) {
				lG1.borraCiudad(null);
				printR("BORRA CIUDAD 	-->		OK!", 0);
				ret = true;
			}
		}
		if (!ret) {
			printR("ERROR POSIBLE EN <borraCiudad()>", 0);
		}

	}

	private static void p_borraPais(ListaG lG1, PLoc p2) throws InterruptedException {
		boolean ret = false;
		//lG1.escribeListaG();
		if (!lG1.borraPais(p2.getPais())) {
			lG1.insertaCola(p2);
			lG1.insertaCabeza(p2);
			lG1.insertaCola(p2);
			if (lG1.borraPais(p2.getPais())) {
				if (lG1.borraPais(null)) {
					ret = true;
				}
			}
		}
		if (ret) {
			printR("BORRA CIUDAD 	-->		OK!", 0);
		}else {
			printR("ERROR POSIBLE EN <borraPais()>", 0);
		}
	}

	private static void p_getPLoc(ListaG lG1, PLoc p1) throws InterruptedException {
		boolean ret = false;
		lG1.insertaCabeza(p1);
		try {
			if (p1.equals(lG1.getPLoc(0))) {
				ret = true;
				System.out.print("E. Controlada --> ");
				lG1.getPLoc(-1);
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		} finally {
			if (ret) {
				printR("GET PLOC	-->		OK!", 0);
			} else {
				printR("ERROR POSIBLE EN <getPLoc()>", 0);
			}
		}
	}

	private static void p_Pais(ListaG lG1) throws InterruptedException {
		boolean ret = false;
		Coordenada cLat = new Coordenada(20, 29, 'N');
		Coordenada cLon = new Coordenada(4, 38, 'O');
		PLoc p1 = new PLoc("CONT", "PAIS", "1");
		PLoc p2 = new PLoc("CONT", "PAIS", "2");
		PLoc p3 = new PLoc("CONT", "PAIS", "3");
		PLoc p4 = new PLoc("CONT", "PAIS", "4");
		PLoc p5 = new PLoc("CONT", "PENE", "5");
		try {
			p1.setLatitud(cLat);
			p1.setLongitud(cLon);
			p2.setLatitud(cLat);
			p2.setLongitud(cLon);
			p3.setLatitud(cLat);
			p3.setLongitud(cLon);
			p4.setLatitud(cLat);
			p4.setLongitud(cLon);
			p5.setLatitud(cLat);
			p5.setLongitud(cLon);
		} catch (CoordenadaExcepcion e) {
			System.out.println(e);
		}

		PLoc[] pais = { p1, p2, p3, p4 };
		lG1.insertaArrayPLoc(pais);
		lG1.insertaCola(p5);
		PLoc[] temp = lG1.Pais("PAIS");
		if (temp != null) {
			if (temp.length == pais.length) {
				for (int i = 0; i < temp.length; i++) {
					if (pais[i].equals(temp[i])) {
						ret = true;
					}
				}

			}
		}

		if (ret) {
			printR("PAIS		-->		OK!", 0);
		} else {
			printR("ERROR POSIBLEMENTE EN <Pais()>", 0);
		}

	}

	private static void p_ordenaLista(ListaG lG1) throws InterruptedException {
		//lG1.escribeListaG();
		//printR("AAAAAAAAAAAAAAAAAAAAAAAAAAa", 0);
		lG1.ordenaLista();
		printR("ORDENA LISTA	-->		OK!", 0);
	}

	private static void p_escribeListaG(ListaG lG1) throws InterruptedException {
		lG1.escribeListaG();
		printR("ESCRIBE LISTA G	-->		OK!", 0);
	}

	/**
	 * Imprime en color Rojo el String del primer parametro, con el segundo
	 * parametro se elije el formato de salida.<br>
	 * 0 = Normal<br>
	 * 1 = Loading <br>
	 * 2 = Titulo <br>
	 */
	private static void printR(String s, int load) throws InterruptedException {

		if (load == 0) {
			System.out.print(s);
		}
		if (load == 1) {
			System.out.print(s + "");
			for (int i = 0; i <= 20; i++) {
				System.out.print("#");
				Thread.sleep(100);
			}
		}
		if (load == 2) {
			System.out.print("|||||||||||--- " + s + " ---|||||||||||\n");
		}
		System.out.println("");

	}

}
