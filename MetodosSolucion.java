import java.util.ArrayList;
public interface MetodosSolucion{
    // se crea una interfaz para implementrar los métodos solucionSopa e imiprimeSopa para todos los casos de solución 1 y 2
    public ArrayList<String> lecturaArchivo(String path);
    public int[] solucionSopa();
    public void imprimeSopa();
}