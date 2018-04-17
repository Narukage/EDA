

public class p10 {
	public static void main(String[] args) {
		String[] args2 = new String[4];
			args2[0] = new String("pruebaloTodoJodido.dat");
			System.out.println("\tPares1\n");
			args2[1] = new String("P");
			args2[2] = new String("EU");
			args2[3] = new String("0.0");
			BuscaLocalizacion.main(args2);
			
			System.out.println("\n\tPares2\n");
			args2[3] = new String("-1.0");
			BuscaLocalizacion.main(args2);
			
			System.out.println("\n\tPares3\n");
			args2[3] = new String("10000");
			args2[2] = new String("");
			BuscaLocalizacion.main(args2);
			
			System.out.println("\n\tPares4\n");
			args2[3] = new String("15");
			args2[2] = new String("EU");
			BuscaLocalizacion.main(args2);
			
			System.out.println("\n\tPares5\n");
			args2[3] = new String("15");
			args2[2] = null;
			BuscaLocalizacion.main(args2);
			
			System.out.println("\n\tRangos1\n");
			args2[1] = new String("R");
			args2[2] = new String("0");
			args2[3] = new String("-1");
			BuscaLocalizacion.main(args2);
			
			System.out.println("\n\tRangos2\n");
			args2[3] = new String("0");
			BuscaLocalizacion.main(args2);

			System.out.println("\n\tRangos3\n");
			args2[3] = new String("15");
			BuscaLocalizacion.main(args2);
	}
}
