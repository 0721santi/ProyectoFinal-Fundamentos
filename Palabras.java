public class Palabras {
    // atributos
    private int longitude;
    private String name;
    // constructor
    Palabras(int longitude, String name){
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
    // setters
    public void setLongitude(int longitude){
        this.longitude = longitude;
    }
    public void setName(String name){
        this.name = name;
    }
}
