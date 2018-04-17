
public class p09 {
	public static void main(String[] args) {
		String[] args2 = new String[4];
		if (args.length > 0) {
			args2[0] = new String("p09.dat");
			args2[1] = new String("P");
			args2[2] = new String("EU");
			args2[3] = new String("0.0");
			BuscaLocalizacion.main(args2);
			args2[3] = new String("-1.0");
			BuscaLocalizacion.main(args2);
			
			
			
			args2[3] = new String("15");
			BuscaLocalizacion.main(args2);
		} else
			System.out.println("falta el nombre del fichero");
	}
}
