//DNI 77400533J, MIGUEL HERMIDA CORES
public class Coordenada {
	//Variables privadas
    private int grados;
    private int minutos;
    private char pos;

    //Constructor
    public Coordenada(int g, int m, char p){
        grados  = g;
        minutos = m;
        pos     = p;
    }

    //Getters
    public int getGrados() { return grados;  }
    public int getMinutos(){ return minutos; }
    public char getPos()   { return pos;     }
}
