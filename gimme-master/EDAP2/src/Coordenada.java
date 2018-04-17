//DNI 48620792B BARBA ROBLES, ALBERTO
public class Coordenada {
	public int grado;
	public int minutos;
	public char pos;

	public Coordenada(int g, int m, char p){
		grado=g;
		minutos=m;
		pos=p;
	}

	public int getGrados(){
		return grado;
	}

	public int getMinutos(){
		return minutos;
	}

	public char getPos(){
		return pos;
	}
}
