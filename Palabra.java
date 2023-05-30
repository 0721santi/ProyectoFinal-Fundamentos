public class Palabra /*implements Metodos*/{
    // atributos
    private String name;
    private int x1, y1, x2, y2, direccion;
    private boolean estado;
    // constructor
    Palabra(String name, int x1, int y1, int direccion, boolean estado){
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.direccion = direccion;
        this.estado = estado;
    }
    // getters
    public String getName(){
        return this.name;
    }
    public int getX1(){
        return this.x1;
    }
    public int getY1(){
        return this.y1;
    }
    public int getX2(){
        return this.x2;
    }
    public int getY2(){
        return this.y2;
    }
    public int getDireccion(){
        return this.direccion;
    }
    public boolean getEstado(){
        return this.estado;
    }
    public int getWordLongitude(){
        return this.name.length();
    }
    // setters
    public void setName(String name){
        this.name = name;
    }
    public void setX1(int x1){
        this.x1 = x1;
    }
    public void setY1(int y1){
        this.y1 = y1;
    }
    public void setX2(int x2){
        this.x2 = x2;
    }
    public void setDireccion(int direccion){
        this.direccion = direccion;
    }
    public void setEstado(boolean estado){
        this.estado = estado;
    }
}