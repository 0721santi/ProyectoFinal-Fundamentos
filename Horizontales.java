public class Horizontales extends Palabras implements Position{
    String name;
    int longitud, estado;
    int[] usedX, usedY;
    public Horizontales(int longitude, String name, int estado){
        super(longitude, name, estado);
        usedX = new int[longitude];
        usedY = new int[longitude];
    }
    public int[] getUsedX(){
        return this.usedX;
    }
    public int[] getUsedY(){
        return this.usedY;
    }
}
