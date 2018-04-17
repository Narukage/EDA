
public class p08 {
	public static void main(String[] args) {
		String[] args2 = new String[4];
		if (args.length > 0) {
			args2[0] = new String("p08.dat");
			args2[1] = new String("R");
			args2[2] = new String("-12.0");
			args2[3] = new String("0.3");
			BuscaLocalizacion.main(args2);
			args2[3] = new String("5.13");
			BuscaLocalizacion.main(args2);
		} else
			System.out.println("falta el nombre del fichero");
	}
}
