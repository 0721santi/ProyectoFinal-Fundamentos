import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*; // se importan todas las libreria del paquete .io (input output)
//matriz[y][x]
// utilizar herencia imprimendo una clase ImprimirSopa sin resolver y utiulizar extends en otra clase ImprimirSopaResuelta que imprima la sopa la imprima resuelta (IDEA PARA HERENCIA)
public class MainSopaLetras {
    // se crea un metodo para buscar el archivo .txt para llenar el arreglo
    static void creaArrayPalabras(String path, ArrayList<String> lista){
        //se usa un try - catch para evitar que salga un error tipo java exception como out bound 
        try {
            File doc = new File(path+".txt");
            Scanner readDoc = new Scanner(doc);       
            while(readDoc.hasNextLine()){
                lista.add(readDoc.nextLine());
            }
        } catch (Exception e) {
            System.out.println("No existe un archivo con nombre "+path+".");
        }
    }
    //se usa otro metodo para organizar el arreglo de mayor a menor
    static void organizaLista(ArrayList<String> lista){
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
    static char[][] creaSopaDeLetras(ArrayList<String> lista, int tamanoSopa){
        Random myRand = new Random();
        char[][] sopa = new char[tamanoSopa][tamanoSopa];
        for(int i = 0;i<lista.size();i++){
            int direccion = myRand.nextInt(8);
            int lon = lista.get(i).length();
            switch(direccion){
                case 0:
                    creaVerticales(lista, sopa, tamanoSopa, lon, direccion, i);
                    break;
                case 1:
                    creaVerticales(lista, sopa, tamanoSopa, lon, direccion, i);
                    break;                    
                case 2:
                    creaHorizontales(lista, sopa, tamanoSopa, lon, direccion, i);
                    break;
                case 3:
                    creaHorizontales(lista, sopa, tamanoSopa, lon, direccion, i);
                    break;
                case 4:
                    if(!creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i)){
                        i--;
                    }
                    break;
                case 5:
                    if(!creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i)){
                        i--;
                    }
                    break;
                case 6:
                    if(!creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i)){
                        i--;
                    }
                    break;
                case 7:
                    if(!creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i)){
                        i--;
                    }
                    break;
            }
        }
        return sopa;
    }
    static void creaVerticales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int dir, int i){
        /*
         * Este método permite decidir si una palabra se crea en sentido invertido o no.
         * Además, llena la matriz de chars con los chars de la palabra en cuestión de manera vertical.
         * El caso 0 es la palabra invertida y el caso 1 es la palabra no invertida
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
                    * Es x-1, porque cuando vuelva al control del for, j++, entonces tendría uno más y no sería realmente 0.
                    */
                }
            }
            for(int j=x;j<lon+x;j++){
                sopa[y][j] = lista.get(i).charAt(j-x);
            }
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
                    * Es x+1, porque cuando vuelve al control del for, j--, entonces tendria uno menos y no seria 
                    realmente 0.
                    */
                }
            }
            for(int j=x;j>(x-lon);j--){
                sopa[y][j] = lista.get(i).charAt(x-j);
            }
        }
    }
    static void creaHorizontales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int dir, int i){
        /*
         * Este método permite decidir si una palabra se crea en sentido invertido o no.
         * Además, llena la matriz de chars con los chars de la palabra en cuestión de manera vertical.
         * El caso 2 es la palabra invertida y el caso 3 es la palabra no invertida
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
                    * Es y-1, porque cuando vuelva al control del for, j++, entonces tendría uno más y no sería 
                    * realmente 0.
                    */
                }
            }
            for(int j=y;j<lon+y;j++){
                sopa[j][x] = lista.get(i).charAt(j-y);
            }
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
                    * Es y+1, porque cuando vuelva al control del for, j--, entonces tendría uno menos y no sería realmente 0.
                    */
                }
            }
            for(int j = y;j>(y-lon);j--){
                sopa[j][x] = lista.get(i).charAt(y-j);
            }
        }
    }
    static boolean creaDiagonales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int dir, int i){
        /*
         * Este método permite decidir si una palabra se crea en sentido invertido o no.
         * Además, llena la matriz de chars con los chars de la palabra en diagonal.
         * El caso 4 es la palabra en diagonal hacia abajo a la derecha y el caso 5 es la palabra en diagonal hacia 
         * arriba a la izquierda (la invertida del 4). 
         * El caso 6 es la palabra en diagonal hacia abajo a la izquierda y el caso 7 es la palabra en diagonal 
         * hacia arriba a la dercha (la invertida del 7).
         */
        Random myRand = new Random();
        int intentos = 0;
        switch(dir){
            case 4:
                int p = myRand.nextInt(tamanoSopa-lon);
                for(int j = p;j<lon+p;j++){
                    if(sopa[j][j] != '\0' && sopa[j][j] != lista.get(i).charAt(j-p)){
                        p = myRand.nextInt(tamanoSopa-lon);
                        j = p-1;
                        /*
                         * Es p-1, porque cuando vuelva al control del for, j++, entonces tendría uno más y nosería 
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
                /* se crea una variable intentos para verificar 3 veces si hay espacio para la palabra, de locontrario se cambia de direccion,
                *  para que no quede en un loop infinito buscando en las mismas cordenadas.
                */
                for(int j = p;j<lon+p;j++){
                    sopa[j][j] = lista.get(i).charAt(j-p);
                }
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
                         * Es p-1, porque cuando vuelva al control del for, j++, entonces tendría uno más ynosería 
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
                for(int j = px, k = py;j>(px-lon);j--, k++){
                    sopa[k][j] = lista.get(i).charAt(k-py);
                }
                return true;
            case 6:
                p = lon + myRand.nextInt(tamanoSopa-lon);
                for(int j = p;j>(p-lon);j--){
                    //cuando finalizado el trabajo, corregir el if: if(sopa[y][j] != '\0'
                    if(sopa[j][j] != '\0' && sopa[j][j] != lista.get(i).charAt(p-j)){
                        p = lon + myRand.nextInt(tamanoSopa-lon);
                        j = p+1;
                        /*
                        * Es p+1, porque cuando vuelva al control del for, j--, entonces tendría uno menos y nosería realmente 0.
                        */
                        if(intentos<3){
                            intentos++;
                        }
                        else{
                            return false;
                        }
                    }
                }
                /* se crea una variable intentos para verificar 3 veces si hay espacio para la palabra, de lo 
                contrario se cambia de direccion,
                *  para que no quede en un loop infinito buscando en las mismas cordenadas.
                */
                for(int j = p;j>(p-lon);j--){
                    sopa[j][j] = lista.get(i).charAt(p-j);
                }
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
                         * Es p-1, porque cuando vuelva al control del for, j++, entonces tendría uno más yno sería 
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
                /* se crea una variable intentos para verificar 3 veces si hay espacio para la palabra, de lo  contrario se cambia de direccion,
                *  para que no quede en un loop infinito buscando en las mismas cordenadas.
                */
                for(int j = py, k = px;j>(py-lon);j--, k++){
                        sopa[j][k] = lista.get(i).charAt(k-px);
                }
                return true;
            default:
                return false;
        }
    }
    static void llenaVacios(char[][] sopa, int tamanoSopa){
        Random genChar = new Random();
        for (int i = 0; i < tamanoSopa; i++){
            for(int j = 0; j<tamanoSopa;j++){
                if(sopa[i][j] == '\0'){
                    sopa[i][j] = (char)(genChar.nextInt(26) + 'A');
                    /*
                     * Esta forma de generar chars fue tomada de stackoverflow:
                     * https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
                     */
                }
            }
        }
    }
}