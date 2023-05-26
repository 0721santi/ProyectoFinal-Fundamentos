public class Palabras {
    // atributos
    private int longitude;
    private String name;
    private int estado;
    // constructor
    Palabras(int longitude, String name, int estado){
        this.name = name;
        this.longitude = longitude;
        this.estado = estado;
    }
    // getters
    public String getName(){
        return this.name;
    }
    public int getLongitude(){
        return this.longitude;
    }
    public int getestado(){
        return this.estado;
    }
    // setters
    public void setLongitude(int longitude){
        this.longitude = longitude;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setestado(int estado){
        this.estado = estado;
    }
}
