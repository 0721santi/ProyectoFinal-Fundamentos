import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
// Se importan todas las libreria del paquete .io (input output), para realizar la lectura y escritura de archivos.
import java.io.*; 
public class MainSopaLetras{
    static int getMenu(){
        /*
         * Este método nos da la posibilidad de mostrar el menú y recibir la opción que el usuario desea 
         * ejecutar.
         */
        Scanner myScan = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1. Crear sopa de letras a partir de archivo .txt");
        System.out.println("2. Resolver sopa de letras con datos creados en opción 1.");
        System.out.println("3. Resolver sopa de letras con datos externos.");
        System.out.println("4. Salir.");
        System.out.print("Ingrese una opción: ");
        int opc = myScan.nextInt();
        return opc;
    }
    static void lecturaArchivo(String path, ArrayList<String> lista){
        /* 
        * Se crea un metodo para buscar y leer el archivo .txt para llenar el ArrayList de palabras.
        * Se usa un try - catch para verificar la existencia del archivo y un correcto funcionamiento de
        * los métodos usados. Se tomó como base la estructura presentada en:
        * https://www.delftstack.com/es/howto/java/read-file-in-java/
        */
        try{
            File doc = new File(path+".txt");
            Scanner readDoc = new Scanner(doc);       
            while(readDoc.hasNextLine()){
                lista.add(readDoc.nextLine());
            }
        }catch (Exception e) {
            System.out.println("No existe un archivo con nombre "+path+".");
            System.out.println("Ejecute nuevamente el programa.");
            System.exit(0);
        }
    }
    static void guardaArchivo(char[][] sopa, int tamanoSopa){
        /*
         * Se crea método para guardar la sopa de letras en un txt usando FileWriter. Se usó información de:
         * https://www.geeksforgeeks.org/java-program-to-save-a-string-to-a-file/
         * https://stackoverflow.com/questions/17716192/insert-line-break-when-writing-to-file
         */
        Scanner myScan = new Scanner(System.in);
        System.out.println("¿Que nombre quiere poner a la sopa de letras? ");
        String name = myScan.nextLine();
        try{
            FileWriter crea = new FileWriter(name+".txt");
            for(int i=0;i<tamanoSopa;i++){
                for(int j=0;j<tamanoSopa;j++){
                    crea.write(sopa[i][j]);
                }
                crea.write("\n");
            }
            crea.close();
            System.out.println("La sopa de letras ha sido creada con éxito.\n");
        }
        catch(Exception e){
            System.out.println(name+" no es un nombre de archivo válido.");
        }
    }
    static void organizaLista(ArrayList<String> lista){
        /*
         * Este método busca que el arraylist esté ordenado descendentemente, para poder asegurar que las palabras
         * más largas tengan el suficiente espacio al momento de construir la sopa de letras.
         */
        int n = lista.size();
        for(int i = 0;i<n-1;i++){
            int maxIndex = i;
            for(int j = i;j<n;j++){
                if(lista.get(j).length() > lista.get(maxIndex).length()){
                    maxIndex = j;
                }
            }
            String temp = lista.get(maxIndex);
            lista.set(maxIndex, lista.get(i));
            lista.set(i, temp);
        }
    }
    static char[][] creaSopaDeLetras(ArrayList<String> lista, int tamanoSopa, Palabra[] p){
        /* 
        * Se crea el método principal para crear el tablero de la sopa de letras. En este se escoge la direccion que    * tendra cada palabra de manera aleatoria y con un switch, se redirecciona al método específico para 
        * verificar que sí haya el espacio disponible en dicha dirección (tanto invertido como sin invertir) y agregar
        * al tablero.
        * En los casos de las diagonales se implementa un condicional que se usa en caso de que si luego de
        * 3 intentos el programa no logra encontrar una posición idónea para la palabra, vuelva a buscar otra
        * dirección distinta.
        */
        Random myRand = new Random();
        char[][] sopa = new char[tamanoSopa][tamanoSopa];
        for(int i = 0;i<lista.size();i++){
            int direccion = myRand.nextInt(8);
            int lon = lista.get(i).length();
            switch(direccion){
                case 0:
                    creaVerticales(lista, sopa, tamanoSopa, lon, direccion, i, p);
                    break;
                case 1:
                    creaVerticales(lista, sopa, tamanoSopa, lon, direccion, i, p);
                    break;                    
                case 2:
                    creaHorizontales(lista, sopa, tamanoSopa, lon, direccion, i, p);
                    break;
                case 3:
                    creaHorizontales(lista, sopa, tamanoSopa, lon, direccion, i, p);
                    break;
                case 4:
                    if(!creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i, p)){
                        i--;
                    }
                    break;
                case 5:
                    if(!creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i, p)){
                        i--;
                    }
                    break;
                case 6:
                    if(!creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i, p)){
                        i--;
                    }
                    break;
                case 7:
                    if(!creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i, p)){
                        i--;
                    }
                    break;
            }
        }
        llenaVacios(sopa, tamanoSopa);
        return sopa;
    }
    static void creaVerticales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int dir, int i, Palabra[] p){
        /*
         * Este método permite definir si una palabra se crea en sentido invertido o no.
         * Además, llena el tablero con los chars de la palabra en cuestión de manera vertical.
         * El caso 0 es la palabra no invertida y el caso 1 es la palabra invertida
         */
        Random myRand = new Random();
        if(dir == 0){
            int y = myRand.nextInt(tamanoSopa);         
            int x = myRand.nextInt(tamanoSopa-lon);
            for(int j=x;j<lon+x;j++){
                if(sopa[y][j] != '\0' && sopa[y][j] != lista.get(i).charAt(j-x)){
                    x = myRand.nextInt(tamanoSopa-lon);
                    y = myRand.nextInt(tamanoSopa);
                    j = x-1;
                    /*
                     * Es x-1, porque cuando vuelva al control del for, j++, entonces tendría uno menos y no tendría el * valor que deseamos
                     */
                }
            }
            for(int j=x;j<lon+x;j++){
                sopa[y][j] = lista.get(i).charAt(j-x);
            }
            creaPalabra(lista, p, dir, i, x, y);
        }
        else{
            int x = lon + myRand.nextInt(tamanoSopa-lon);
            int y = myRand.nextInt(tamanoSopa);
            for(int j = x;j>(x-lon);j--){
                if(sopa[y][j] != '\0' && sopa[y][j] != lista.get(i).charAt(x-j)){
                    x = lon + myRand.nextInt(tamanoSopa-lon);
                    y = myRand.nextInt(tamanoSopa);
                    j = x+1;
                    /*
                     * Es x+1, porque cuando vuelva al control del for, j--, entonces tendría uno menos y no tendría el * valor que deseamos
                     */
                }
            }
            for(int j=x;j>(x-lon);j--){
                sopa[y][j] = lista.get(i).charAt(x-j);
            }
            creaPalabra(lista, p, dir, i, x, y);
        }
    }
    static void creaHorizontales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int dir, int i, Palabra[] p){
        /*
         * Este método permite decidir si una palabra se crea en sentido invertido o no.
         * Además, llena la matriz de chars con los chars de la palabra en cuestión de manera vertical.
         * El caso 2 es la palabra no invertida y el caso 3 es la palabra invertida
         */
        Random myRand = new Random();
        if(dir == 2){
            int y = myRand.nextInt(tamanoSopa-lon);         
            int x = myRand.nextInt(tamanoSopa);
            for(int j=y;j<lon+y;j++){
                if(sopa[j][x] != '\0' && sopa[j][x] != lista.get(i).charAt(j-y)){
                    x = myRand.nextInt(tamanoSopa);
                    y = myRand.nextInt(tamanoSopa-lon);
                    j = y-1;
                    /*
                     * Es y-1, porque cuando vuelva al control del for, j++, entonces tendría uno menos y no tendría el * valor que deseamos
                     */
                }
            }
            for(int j=y;j<lon+y;j++){
                sopa[j][x] = lista.get(i).charAt(j-y);
            }
            creaPalabra(lista, p, dir, i, x, y);
        }
        else{
            int x = myRand.nextInt(tamanoSopa);
            int y = lon + myRand.nextInt(tamanoSopa-lon);
            for(int j = y;j>(y-lon);j--){
                if(sopa[j][x] != '\0' && sopa[j][x] != lista.get(i).charAt(y-j)){
                    x = myRand.nextInt(tamanoSopa);
                    y = lon + myRand.nextInt(tamanoSopa-lon);
                    j = y+1;
                    /*
                     * Es y+1, porque cuando vuelva al control del for, j--, entonces tendría uno menos y no tendría el * valor que deseamos.
                     */
                }
            }
            for(int j = y;j>(y-lon);j--){
                sopa[j][x] = lista.get(i).charAt(y-j);
            }
            creaPalabra(lista, p, dir, i, x, y);
        }
    }
    static boolean creaDiagonales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int dir, int i, Palabra[] word){
        /*
         * Este método permite decidir si una palabra se crea en sentido invertido o no.
         * Además, llena la matriz de chars con los chars de la palabra en diagonal.
         * El caso 4 es la palabra en diagonal hacia abajo a la derecha y el caso 5 es la palabra en diagonal hacia 
         * abajo a la izquierda. 
         * El caso 6 es la palabra en diagonal hacia arriba a la izquierda y el caso 7 es la palabra en diagonal 
         * hacia arriba a la dercha.
         */
        Random myRand = new Random();
        int intentos = 0;
        switch(dir){
            /*
            * Se crea una variable intentos para verificar 3 veces si hay espacio para la palabra en cada dirección, de * lo contrario se cambia de direccion, para que no quede en un loop infinito buscando en las mismas 
            * cordenadas y encuentre en que direccion cabe la palabra.
            */
            case 4:
                int p = myRand.nextInt(tamanoSopa-lon);
                for(int j = p;j<lon+p;j++){
                    if(sopa[j][j] != '\0' && sopa[j][j] != lista.get(i).charAt(j-p)){
                        p = myRand.nextInt(tamanoSopa-lon);
                        j = p-1;
                        /*
                         * Es p-1, porque cuando vuelva al control del for, j++, entonces tendría uno más y no sería 
                         * realmente 0.
                         */
                        if(intentos<3){
                            intentos++;
                        }
                        else{
                            return false;
                        }
                    }
                }
                for(int j = p;j<lon+p;j++){
                    sopa[j][j] = lista.get(i).charAt(j-p);
                }
                creaPalabra(lista, word, dir, i, p, p);
                return true;
            case 5:
                int px = lon + myRand.nextInt(tamanoSopa-lon);
                int py = myRand.nextInt(tamanoSopa-lon);
                for(int j = px, k = py;j>(px-lon);j--, k++){
                    if(sopa[k][j] != '\0' && sopa[k][j] != lista.get(i).charAt(k-py)){
                        px = lon + myRand.nextInt(tamanoSopa-lon);
                        py = myRand.nextInt(tamanoSopa-lon);
                        j = px+1;
                        k = py-1;
                        /*
                         * Es px+1 y es py-1, porque cuando vuelva al control del for, j-- y k++, entonces tendría uno 
                         * menos en x y uno más en y, y no sería realmente 0.
                         */
                        if(intentos<3){
                            intentos++;
                        }
                        else{
                            return false;
                        }
                    }
                }
                for(int j = px, k = py;j>(px-lon);j--, k++){
                    sopa[k][j] = lista.get(i).charAt(k-py);
                }
                creaPalabra(lista, word, dir, i, px, py);
                return true;
            case 6:
                p = lon + myRand.nextInt(tamanoSopa-lon);
                for(int j = p;j>(p-lon);j--){
                    if(sopa[j][j] != '\0' && sopa[j][j] != lista.get(i).charAt(p-j)){
                        p = lon + myRand.nextInt(tamanoSopa-lon);
                        j = p+1;
                        /*
                         * Es p+1, porque cuando vuelva al control del for, j--, entonces tendría uno menos y no sería 
                         * realmente 0.
                         */
                        if(intentos<3){
                            intentos++;
                        }
                        else{
                            return false;
                        }
                    }
                }
                for(int j = p;j>(p-lon);j--){
                    sopa[j][j] = lista.get(i).charAt(p-j);
                }
                creaPalabra(lista, word, dir, i, p, p);
                return true;
            case 7:
                py = lon + myRand.nextInt(tamanoSopa-lon);
                px = myRand.nextInt(tamanoSopa-lon);
                for(int j = py, k = px;j>(py-lon);j--, k++){
                    if(sopa[j][k] != '\0' && sopa[j][k] != lista.get(i).charAt(k-px)){
                        py = lon + myRand.nextInt(tamanoSopa-lon);
                        px = myRand.nextInt(tamanoSopa-lon);
                        k = px-1;
                        j = py+1;
                        /*
                         * Es px-1 y es py+1, porque cuando vuelva al control del for, j-- y k++, entonces tendría uno 
                         * mas en x y uno menos en y, y no sería realmente 0.
                         */
                        if(intentos<3){
                            intentos++;
                        }
                        else{
                            return false;
                        }
                    }
                }
                for(int j = py, k = px;j>(py-lon);j--, k++){
                        sopa[j][k] = lista.get(i).charAt(k-px);
                }
                creaPalabra(lista, word, dir, i, px, py);
                return true;
            default:
                return false;
        }
    }
    private static void llenaVacios(char[][] sopa, int tamanoSopa){
        //Este método sirve para llenar los espacios restantes luego de poner todas las palabras en la sopa de letras.
        Random genChar = new Random();
        for (int i = 0; i < tamanoSopa; i++){
            for(int j = 0; j<tamanoSopa;j++){
                if(sopa[i][j] == '\0'){
                    sopa[i][j] = (char)(genChar.nextInt(26) + 'a');
                    /*
                     * Esta forma de generar chars fue tomada de stackoverflow:
                     * https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
                     */
                }
            }
        }
    }
    private static void creaPalabra(ArrayList<String> lista, Palabra[] p, int dir, int i, int x, int y){
        /* 
        * Este metodo sirve para crear objetos de la clase palabra, que nos servira para llevar un control de todas
        * las palabras que han sido agregadas dentro de la sopa de letras.
        * 
        */
        p[i] = new Palabra(lista.get(i), x, y, dir, false);
    }
}