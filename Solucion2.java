import java.util.Scanner;
import java.util.ArrayList;
// se importan todas las libreria del paquete .io (input output).
import java.io.*;
public class Solucion2 implements MetodosSolucion{
    // implementa la interfaz para utilizar los métodos solucionSopa e imprimeSopa.
    private ArrayList<String> listaPalabras;
    private char[][] sopa;
    private static final int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
    private static final int[] dy = {0, 0, 1, -1, -1, -1, 1, 1};
    public Solucion2(String pathSopa, String pathList){
        this.sopa = cargaSopaLetras(pathSopa);
        this.listaPalabras = lecturaArchivo(pathList);
    }
    public ArrayList<String> lecturaArchivo(String path){
        // Se crea un método para leer el archivo usando un try - catch.
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
        // Se crea un método para cargar la sopa de letras usando el archivo que se ingreso por pantalla.
        ArrayList<String> sopaString = lecturaArchivo(path);
        sopa = new char[sopaString.size()][sopaString.size()];
        for(int i = 0;i<sopaString.size();i++){
            for(int j = 0;j<sopaString.size();j++){
                sopa[i][j] = sopaString.get(i).charAt(j);
            }
        }
        return sopa;
    }
    public int[] solucionSopa() {
        int[] pasos = {0};
        System.out.println(listaPalabras);
        for(String palabra : listaPalabras){
            System.out.println(palabra);
            boolean[][] visited = new boolean[sopa.length][sopa[0].length];
            int[] usedX = new int[2];
            int[] usedY = new int[2];
            for (int i = 0; i < sopa.length; i++) {
                for (int j = 0; j < sopa[i].length; j++) {
                    if(searchMethod(sopa, palabra, visited, i, j, 0, usedX, usedY, pasos)){
                        usedX[0] = i;
                        usedY[0] = j;
                        System.out.println("Se ha encontrado la palabra "+palabra+".");
                        System.out.println("X1, Y1: ("+usedX[0]+","+usedY[0]+"); X2, Y2: ("+usedX[1]+","+usedY[1]+").");
                    }
                }
            }
        }
        return pasos;
    }

    private static boolean searchMethod(char[][] grid, String palabra, boolean[][] visited, int x, int y, int index, int[] usedX, int[] usedY, int[] pasos){
        /*
        * Se crea un método para buscar las palabras en la sopa de letras, se usó la herramienta de chatGPT para realizar la busqueda de manera más eficiente,
        * chatGPT luego de preguntarle por diferentes metodos para resolver una sopa de letras arrojo varias estrategias y finalmente la que mejor se acoplo a nuestro proyecto
        * fue el DFS (búsqueda en profundidad) ya que era un codigo corto, fácil de comprender y eficiente.
        */
        if(index == palabra.length()-1){
            usedX[1] = x;
            usedY[1] = y;
            return true;
        }
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || visited[x][y] || grid[x][y] != palabra.charAt(index)){
            return false;
        }
        visited[x][y] = true;
        for(int i = 0; i < 8; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            pasos[0]++;
            if ((newX >= 0 && newX < grid.length) && (newY >= 0 && newY < grid.length) && grid[newX][newY] == palabra.charAt(index+1)){
                if (searchMethod(grid, palabra, visited, x + dx[i], y + dy[i], index + 1, usedX, usedY, pasos)){
                    return true;
                }
            }
        }
        visited[x][y] = false;
        return false;
    }
    public void imprimeSopa(){
        // Se crea el método de la interfaz.
        for(int i = 0;i<sopa.length;i++){
            for(int j=0;j<sopa.length;j++){
                System.out.print(sopa[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}
