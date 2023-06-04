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
    /*
     * dx, dy son arrays de enteros que contienen que valores se deben de operar para recorrer la matriz en la dirección 
     * deseada:
     * Cuando el indice vale 0, representa los valores para recorrer la matriz horizontal-> izquierda a derecha
     * Cuando el indice vale 1, representa los valores para recorrer la matriz horizontal-> derecha a izquierda (invertida)
     * Cuando el indice vale 2, representa los valores para recorrer la matriz vertical-> arriba a abajo
     * Cuando el indice vale 3, representa los valores para recorrer la matriz vertical-> abajo a arriba (invertida)
     * Cuando el indice vale 4, representa los valores para recorrer la matriz diagonal-> abajo a la derecha.
     * Cuando el indice vale 5, representa los valores para recorrer la matriz diagonal-> abajo a la izquierda.
     * Cuando el indice vale 6, representa los valores para recorrer la matriz diagonal-> arriba a la derecha.
     * Cuando el indice vale 7, representa los valores para recorrer la matriz diagonal-> arriba a la izquierda.
     */
    public Solucion2(String pathSopa, String pathList){
        this.sopa = cargaSopaLetras(pathSopa);
        this.listaPalabras = lecturaArchivo(pathList);
    }
    public ArrayList<String> lecturaArchivo(String path){
        /* 
        * Se crea un metodo para buscar y leer el archivo .txt para llenar el ArrayList de palabras.
        * Se usa un try - catch para verificar la existencia del archivo y un correcto funcionamiento de
        * los métodos usados. Se tomó como base la estructura presentada en:
        * https://www.delftstack.com/es/howto/java/read-file-in-java/
        */
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
        // Se crea un método para cargar la sopa de letras usando el archivo que ya se ha cargado.
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
        //Se describe el funcionamiento en el archivo de interfaz.
        int[] pasos = {0};
        System.out.println("\nLista de palabras a encontrar:");
        System.out.println(listaPalabras+"\n");
        for(String palabra : listaPalabras){
            boolean[][] visited = new boolean[sopa.length][sopa[0].length];
            boolean found = false;
            int[] usedX = new int[2];
            int[] usedY = new int[2];
            for (int i = 0; i < sopa.length; i++) {
                for (int j = 0; j < sopa[i].length; j++) {
                    if(searchMethod(sopa, palabra, visited, i, j, 0, usedX, usedY, pasos)){
                        usedX[0] = i;
                        usedY[0] = j;
                        System.out.println("Se ha encontrado la palabra "+palabra+".");
                        System.out.println("X1, Y1: ("+usedX[0]+","+usedY[0]+"); X2, Y2: ("+usedX[1]+","+usedY[1]+").");
                        found = true;
                    }
                }
            }
            /*
             * Al no poder asegurar que todas las palabras dentro de la lista que ingresó el usuario están en la sopa,
             * se debe agregar este condicional para que, en tal caso de que no se haya encontrado, devuelva el
             * respectivo mensaje.
             */
            if(!found){
                System.out.println("\nNo se ha encontrado la palabra: "+palabra+".\n");
            }
        }
        return pasos;
    }

    private static boolean searchMethod(char[][] sopa, String palabra, boolean[][] visited, int x, int y, int index, int[] usedX, int[] usedY, int[] pasos){
        /*
        * Se crea un método para buscar las palabras en la sopa de letras, se usó la herramienta de chatGPT para         
        * encontrar un método que, agregando las modificaciones requeridas para esta solución, pudiese brindar la       
        * busqueda de manera más eficiente. Dicha herramienta nos brindó distintas opciones, pero optamos por la que se  
        * llama: "Depth-First Search (DFS)" (búsqueda en profundidad), pues fue la que en nuestro concepto tiene una 
        * mayor y eficiente capacidad de búsqueda.
        */
        if(index == palabra.length()-1){
            usedX[1] = x;
            usedY[1] = y;
            return true;
        }
        if(x < 0 || x >= sopa.length || y < 0 || y >= sopa[x].length || visited[x][y] || sopa[x][y] != palabra.charAt(index)){
            return false;
        }
        visited[x][y] = true;
        for(int i = 0; i < 8; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            pasos[0]++;
            if ((newX >= 0 && newX < sopa.length) && (newY >= 0 && newY < sopa.length) && sopa[newX][newY] == palabra.charAt(index+1)){
                if (searchMethod(sopa, palabra, visited, x + dx[i], y + dy[i], index + 1, usedX, usedY, pasos)){
                    return true;
                }
            }
        }
        visited[x][y] = false;
        return false;
    }
    public void imprimeSopa(){
        /*
         * Este método imprime por pantalla el tablero de la sopa de letras.
         */
        for(int i = 0;i<sopa.length;i++){
            for(int j=0;j<sopa.length;j++){
                System.out.print(sopa[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}
