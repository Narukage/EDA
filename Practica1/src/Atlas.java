import java.io.*;

public class Atlas {
    private PLoc[][] local;

    public Atlas(){
        local = new PLoc [181][361];
    }

    boolean setLocalidad(PLoc p){
        int lat=0;
        int long=0;

        if(p!=null){
            if(p.getLatitud()!=null){
                if(p.getLongitud()!=null){

                    if(p.getLatitud().getPos() == 'N'){
                        lat=p.getLatitud().getGrados()+90;
                    }else{
                        lat=90 - p.getLatitud().getGrados();
                    }

                    if(p.getLongitud().getPos() == 'E'){
                        long = p.getLongitud().getGrados() + 180;
                    }
                    else{
                        long = 180 - p.getLongitud().getGrados();
                    }

                    if(lat >= 0 && lat < local.length && long >= 0 && long < local[0].length){
                        if(local[lat][long] == null){
                            local[lat][long] = p;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void leeAtlas(String f){
        if(f != null){
            FileReader fr=null;
            BufferedReader br=null;
            String linea=null;
            String[] info;
            PLoc nuevo;
            String[] lat;
            String[] long;
            boolean cont=false;
            Coordenada coord1;
            Coordenada coord2;

            try{
                fr=new FileReader(f);
                br=new BufferedReader(fr);
                linea=br.readLine();

                while(linea!=null){
                    info=linea.split("#");
                    if(info.length==5) {
                        if(info[0]!=null && !info[0].equals("")){
                            cont=true;
                        }else{
                            info[0] = null;
                        }
                        if (info[1].equals("")) {
                            info[1] = null;
                        }
                        if (info[2].equals("")) {
                            info[2] = null;
                        }

                        nuevo = new PLoc(info[0], info[1], info[2]);

                        if(cont) {
                            nuevo.setContinente(info[0]);
                        }

                        lat=info[3].split(" ");
                        long=info[4].split(" ");

                        int latiGrados=Integer.parseInt(lat[0]);
                        int latiMinutos=Integer.parseInt(lat[1]);
                        int longiGrados=Integer.parseInt(long[0]);
                        int longiMinutos=Integer.parseInt(long[1]);

                        char letralati=lat[2].charAt(0);
                        char letralongi=long[2].charAt(0);

                        coord1 = new Coordenada(latiGrados,latiMinutos,letralati);
                        coord2 = new Coordenada(longiGrados,longiMinutos,letralongi);

                        try{
                            //le paso las Coordenadas a mi PLOC
                            nuevo.setLatitud(coord1);
                            nuevo.setLongitud(coord2);

                            //para meter el PLoc dentro de la matriz
                            setLocalidad(nuevo);

                        }catch(CoordenadaExcepcion error){
                            //muestro el error
                            System.out.println(error);
                        }
                    }
                    linea=br.readLine();
                }
            }catch(IOException o){
                o.printStackTrace();
            }
            try{
                if(fr != null){
                    fr.close();

                }
                if(br != null){
                    br.close();
                }
            }catch(IOException c){
                c.printStackTrace();

            }
        }
    }

    public String consultaAtlas(PLoc f){

    }

    public void muestraAtlasParcial(PLoc f, int n){

    }
}
