
public class Coordenada {
    private int grados;
    private int minutos;
    private char pos;

    public Coordenada(int g, int m, char p){
        grados=g;
        minutos=m;
        if(p=='N' || p=='S' || p=='E' || p=='O') {
            pos = p;
        }
    }

    public int getGrados(){
        return grados;
    }

    public int getMinutos(){
        return minutos;
    }

    public char getPos(){
        return pos;
    }
}
