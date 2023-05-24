public class Palabra {
    // atributos
    private int x, y, longigtude, direccion;
    private String name;
    // constructor
    Palabra(int longigtude, String name){
        this.name = name;
        this.longigtude = longigtude;
    }
    // getters
    public String getName(){
        return this.name;
    }
    public int getLongitude(){
        return this.longigtude;
    }
    public int getXpos(){
        return this.x;
    }
    public int getYpos(){
        return this.y;
    }
    public int getDireccion(){
        return this.direccion;
    }
    // setters
    public void setDireccion(int direccion){
        this.direccion = direccion;
    }
    public void setLongitude(int longigtude){
        this.longigtude = longigtude;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setXpos(int x){
        this.x = x;
    }
    public void setYpos(int y){
        this.y = y;
    }
}
