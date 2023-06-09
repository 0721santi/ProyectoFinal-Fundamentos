public class Solucion1 implements MetodosSolucion{
    // implementa la interfaz para utilizar los métodos solucionSopa e imprimeSopa
    private Palabra[] palabras;
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
    public Solucion1(Palabra[] palabras, char[][] sopa){
        this.palabras = palabras;
        this.sopa = sopa;
    }
    public int[] solucionSopa(){
        //Se describe el funcionamiento en el archivo de interfaz.
        int[] pasos = {0};
        System.out.println("\nLista de palabras a encontrar:");
        for(int p = 0;p<palabras.length;p++){
            boolean[][] visited = new boolean[sopa.length][sopa.length];
            System.out.println(palabras[p].getName());
            for (int i = 0; i < sopa.length; i++) {
                for (int j = 0; j < sopa.length; j++) {
                    searchMethod(sopa, palabras, p, visited, i, j, 0, pasos);
                }
            }
        }
        return pasos;
    }
    private static boolean searchMethod(char[][] sopa, Palabra[] palabra, int p, boolean[][] visited, int x, int y, int index, int[] pasos){
        /*
        * Se crea un método para buscar las palabras en la sopa de letras, 
        * se usó la herramienta de chatGPT para realizar la busqueda de manera más eficiente,
        * chatGPT luego de preguntarle por diferentes metodos para resolver una sopa de letras arrojo varias estrategias y finalmente la que 
        * mejor se acoplo a nuestro proyecto
        * fue el DFS (búsqueda en profundidad) ya que era un codigo corto, fácil de comprender y eficiente.
        */
        if (index == palabra[p].getWordLongitude()-1){
            palabra[p].setEstado(true);
            palabra[p].setX2(y);
            palabra[p].setY2(x);
            return true;
        }
        if ((x < 0 || x >= sopa.length) || (y < 0 || y >= sopa.length) || visited[x][y] || (sopa[x][y] != palabra[p].getName().charAt(index))){
            return false;
        }
        visited[x][y] = true;
        for (int i = 0; i < 8; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            pasos[0]++;
            if ((newX >= 0 && newX < sopa.length) && (newY >= 0 && newY < sopa.length) && sopa[newX][newY] == palabra[p].getName().charAt   (index + 1)) {
                if (searchMethod(sopa, palabra, p, visited, newX, newY, index + 1, pasos)) {
                    return true;
                }
            }
        }
        visited[x][y] = false;
        return false;
    }
    public void imprimePosiciones(Palabra[] p){
        System.out.println("Las respectivas posiciones son: ");
        /* 
        * Este método imprime la información principal de una palabra: Su nombre, su posición inicial y final y, si fue encontrada o no.
        */
        for(int i=0;i<p.length;i++){
            System.out.println("Palabra: "+p[i].getName()+". -> X1, Y1: ("+p[i].getX1()+" "+p[i].getY1()+"), X2,Y2: ("+p[i].getX2()+" "+p[i].getY2()+").");
        }
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