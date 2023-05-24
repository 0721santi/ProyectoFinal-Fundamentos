public class Palabra {
    // atributos
    private int x, y, longitude, direccion;
    private String name;
    // constructor
    Palabra(int longitude, String name){
        this.name = name;
        this.longitude = longitude;
    }
    // getters
    public String getName(){
        return this.name;
    }
    public int getLongitude(){
        return this.longitude;
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
    public void setLongitude(int longitude){
        this.longitude = longitude;
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
