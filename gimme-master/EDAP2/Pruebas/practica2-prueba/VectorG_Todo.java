
/**
 * Prueba exaustiva para VectorG
 * 
 * @author yupipi93 *
 */
public class VectorG_Todo {
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

		VectorG vG1 = new VectorG();
		PLoc[] asd = { p1, p2 };

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

		printR("PRUEBA VECTORG", 2);
		printR("LEYENDO POBLACIONES:\n", 1);
		vG1.leeLista(args[0]);
		printR("LECTURA 	-->		OK!", 0);

		p_esVacia(vG1);
		p_insertaCabeza_ciudadEnLista(vG1, p1);
		p_insertaCola_getPLoc(vG1, p2);
		p_borraCabeza(vG1, p1);
		p_borraCola(vG1, p2);
		p_insertaArrayPLoc(vG1, asd);
		p_borraCiudad(vG1, p1);
		p_borraPais(vG1, p2);
		p_getPLoc(vG1, p1);
		p_Pais(vG1);
		p_ordenaLista(vG1);
		p_escribeVectorG(vG1);

	}

	private static void p_esVacia(VectorG vG1) throws InterruptedException {
		if (!vG1.esVacia())
			printR("LISTA 		-->		OK!", 0);
		else
			printR("ERROR, LISTA VACIA!! O MALA IMPLEMENTACION EN <esVacia()>", 0);
	}

	private static void p_insertaCabeza_ciudadEnLista(VectorG vG1, PLoc p1) throws InterruptedException {
		vG1.insertaCabeza(p1);
		boolean ret = false;
		try {
			int pos = vG1.ciudadEnLista("Z");
			if (pos == 0) {
				ret = true;
				System.out.print("E. Controlada --> ");
				pos = vG1.ciudadEnLista(null);
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

	private static void p_insertaCola_getPLoc(VectorG vG1, PLoc p2) throws InterruptedException {
		vG1.insertaCola(p2);
		boolean ret = false;
		try {
			int pos = vG1.ciudadEnLista("C");// NO FUNCIONA
			PLoc p0 = vG1.getPLoc(pos);
			if (p0.equals(p2)) {
				ret = true;
				System.out.print("E. Controlada --> ");
				pos = vG1.ciudadEnLista(null);
				p0 = vG1.getPLoc(-1);// Le peta a alicia
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

	private static void p_borraCabeza(VectorG vG1, PLoc p1) throws InterruptedException {
		boolean ret = false;
		if (vG1.borraCabeza()) {
			PLoc p0 = vG1.getPLoc(0);
			if (!p0.equals(p1)) {
				printR("BORRA CABEZA 	-->		OK!", 0);
				ret = true;
			}
		}
		if (!ret) {
			printR("ERROR EN <borraCabeza()>", 0);
		}
	}

	private static void p_borraCola(VectorG vG1, PLoc p2) throws InterruptedException {
		boolean ret = false;
		int pos = 0;
		try {
			pos = vG1.ciudadEnLista(p2.getCiudad());
		} catch (CiudadNoEncontradaExcepcion e) {
			System.out.println(e);
		}
		if (vG1.borraCola()) {
			try {
				if (vG1.getPLoc(pos).equals(p2)) {
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

	private static void p_insertaArrayPLoc(VectorG vG1, PLoc[] asd) throws InterruptedException {
		boolean ret = false;
		if (!vG1.borraCiudad(asd[0].getCiudad())) {
			if (!vG1.borraCiudad(asd[1].getCiudad())) {
				vG1.insertaArrayPLoc(asd);
				if (vG1.borraCiudad(asd[0].getCiudad())) {
					if (vG1.borraCiudad(asd[1].getCiudad())) {
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

	private static void p_borraCiudad(VectorG vG1, PLoc p1) throws InterruptedException {
		boolean ret = false;
		if (!vG1.borraCiudad(p1.getCiudad())) {
			vG1.insertaCola(p1);
			if (vG1.borraCiudad(p1.getCiudad())) {
				vG1.borraCiudad(null);
				printR("BORRA CIUDAD 	-->		OK!", 0);
				ret = true;
			}
		}
		if (!ret) {
			printR("ERROR POSIBLE EN <borraCiudad()>", 0);
		}

	}

	private static void p_borraPais(VectorG vG1, PLoc p2) throws InterruptedException {
		boolean ret = false;
		if (!vG1.borraPais(p2.getPais())) {
			vG1.insertaCola(p2);
			vG1.insertaCabeza(p2);
			vG1.insertaCola(p2);
			if (vG1.borraPais(p2.getPais())) {
				if (vG1.borraPais(null)) {
					ret = true;
				}
			}
		}
		if (ret) {
			printR("BORRA CIUDAD 	-->		OK!", 0);
		} else {
			printR("ERROR POSIBLE EN <borraPais()>", 0);
		}
	}

	private static void p_getPLoc(VectorG vG1, PLoc p1) throws InterruptedException {
		boolean ret = false;
		vG1.insertaCabeza(p1);
		try {
			if (p1.equals(vG1.getPLoc(0))) {
				ret = true;
				System.out.print("E. Controlada --> ");
				vG1.getPLoc(-1);
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

	private static void p_Pais(VectorG vG1) throws InterruptedException {
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
		vG1.insertaArrayPLoc(pais);
		vG1.insertaCola(p5);
		PLoc[] temp = vG1.Pais("PAIS");
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

	private static void p_ordenaLista(VectorG vG1) throws InterruptedException {
		vG1.ordenaLista();
		printR("ORDENA LISTA	-->		OK!", 0);
	}

	private static void p_escribeVectorG(VectorG vG1) throws InterruptedException {
		vG1.escribeVectorG();
		printR("ESCRIBE VECTOR G	-->		OK!", 0);
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
