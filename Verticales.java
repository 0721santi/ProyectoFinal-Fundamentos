import java.util.ArrayList;
public class Verticales extends Palabras implements Position{
    private String name;
    private int longitud, estado;
    private int[] usedX, usedY;
    private ArrayList<Verticales> listaVerticales = new ArrayList<Verticales>();
    public Verticales(int longitude, String name, int estado, int[] usedX, int[] usedY){
        super(longitude, name, estado);
        this.usedX = usedX;
        this.usedY = usedY;
    }
    public int[] getUsedX(){
        return this.usedX;
    }
    public int[] getUsedY(){
        return this.usedY;
    }
    public void llenaArrayList(int l, String name, int sts, int[] usedX, int[] usedY){
        this.listaVerticales.add(new Verticales(l, name, sts, usedX, usedY));
    }
    public ArrayList<Verticales> getVerticales(){
        return listaVerticales;
    }
}
