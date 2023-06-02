import java.util.Scanner;
import java.util.ArrayList;
import java.io.*; // se importan todas las libreria del paquete .io (input output).
public class Solucion2 implements MetodosSolucion{
    private ArrayList<String> listaPalabras;
    private char[][] sopa;
    private static final int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
    private static final int[] dy = {0, 0, 1, -1, -1, -1, 1, 1};
    public Solucion2(String pathSopa, String pathList){
        this.sopa = cargaSopaLetras(pathSopa);
        this.listaPalabras = lecturaArchivo(pathList);
    }
    public ArrayList<String> lecturaArchivo(String path){
        ArrayList<String> lista = new ArrayList<>();
        try {
            File doc = new File(path+".txt");
            Scanner readDoc = new Scanner(doc);       
            while(readDoc.hasNextLine()){
                lista.add(readDoc.nextLine());
            }
        } catch (Exception e) {
            System.out.println("No existe un archivo con nombre "+path+".");
            System.out.println("Ejecute nuevamente el programa.");
            System.exit(0);
        }
        return lista;
    }
    public char[][] cargaSopaLetras(String path){
        ArrayList<String> sopaString = lecturaArchivo(path);
        sopa = new char[sopaString.size()][sopaString.size()];
        for(int i = 0;i<sopaString.size();i++){
            for(int j = 0;j<sopaString.size();j++){
                sopa[i][j] = sopaString.get(i).charAt(j);
            }
        }
        return sopa;
    }

    public int[] solucionSopa(){
        int[] pasos = {0};
        for(String palabra : listaPalabras){
            boolean[][] visited = new boolean[sopa.length][sopa.length];
            int[] usedX = new int[palabra.length()];
            int[] usedY = new int[palabra.length()];
            for (int i = 0; i < sopa.length; i++) {
                for (int j = 0; j < sopa.length; j++) {
                    searchMethod(sopa, palabra, visited, i, j, 0, pasos, usedX, usedY);
                }
            }
        }
        return pasos;
    }
    private static boolean searchMethod(char[][] sopa, String palabra, boolean[][] visited, int x, int y, int index, int[] pasos, int[] usedX, int[] usedY){
        /*
        * Se crea un método para buscar las palabras en la sopa de letras, se usó la herramienta de chatGPT para realizar la busqueda de manera más eficiente,
        * chatGPT luego de preguntarle por diferentes metodos para resolver una sopa de letras arrojo varias estrategias y finalmente la que mejor se acoplo a nuestro proyecto
        * fue el DFS (búsqueda en profundidad) ya que era un codigo corto, fácil de comprender y eficiente.
        */
        if(index == palabra.length()-1){
            System.out.println("Se ha encontrado la palabra "+palabra+".");
            System.out.println("X1, Y1: ("+usedX[0]+","+usedY[0]+"); X2, Y2: ("+usedX[palabra.length()-1]+","+usedY[palabra.length()-1]+").");
            return true;
        }
        if ((x < 0 || x >= sopa.length) || (y < 0 || y >= sopa.length) || visited[x][y] || (sopa[x][y] != palabra.charAt(index))){
            return false;
        }
        visited[x][y] = true;
        for (int i = 0; i < 8; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            pasos[0]++;
            if ((newX >= 0 && newX < sopa.length) && (newY >= 0 && newY < sopa.length) && sopa[newX][newY] == palabra.charAt(index)){
                if (searchMethod(sopa, palabra, visited, newX, newY, index + 1, pasos, usedX, usedY)){
                    usedX[index] = x;
                    usedY[index] = y;
                    return true;
                }
            }
        }
        visited[x][y] = false;
        return false;
    }
    public void imprimeSopa(){
        for(int i = 0;i<sopa.length;i++){
            for(int j=0;j<sopa.length;j++){
                System.out.print(sopa[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}
