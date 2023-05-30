import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*; // se importan todas las libreria del paquete .io (input output)
//matriz[y][x]
public class MainSopaLetras {
    // se crea un metodo para buscar el archivo .txt para llenar el arreglo
    static void creaArrayPalabras(String path, ArrayList<String> lista){
        // se usa un try - catch para evitar que salga un error tipo java exception como out bound 
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
    // se usa otro metodo para organizar el arreglo de mayor a menor
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
    // borrar este método cuando el trabajo esté listo.
    static void vaciaSopa(char[][] sopa, int tamanoSopa){
        for(int i =0;i<tamanoSopa;i++){
            for(int j=0;j<tamanoSopa;j++){
                sopa[j][i] = ' ';
            }
        }
    }
    static char[][] creaSopaDeLetras(ArrayList<String> lista, int tamanoSopa){
        Random myRandDirection = new Random();
        int direccion = 4;
        char[][] sopa = new char[tamanoSopa][tamanoSopa];
        vaciaSopa(sopa, tamanoSopa); //borrar cuando el trabajo esté listo.
        for(int i = 0;i<lista.size();i++){
            //direccion = myRandDirection.nextInt(5);
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
                    creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i);
                    break;
                case 5:
                    creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i);
                    break;
                case 6:
                    creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i);
                    break;
                case 7:
                    creaDiagonales(lista, sopa, tamanoSopa, lon, direccion, i);
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
        Random myRandPos = new Random();
        if(dir == 0){
            int y = myRandPos.nextInt(tamanoSopa);         
            int x = myRandPos.nextInt(tamanoSopa-lon);
            for(int j=x;j<lon+x;j++){
                //cuando finalizado el trabajo, corregir el if: if(sopa[y][j] != '\0'
                if(sopa[y][j] != ' ' && sopa[y][j] != lista.get(i).charAt(j-x)){
                    x = myRandPos.nextInt(tamanoSopa-lon);
                    y = myRandPos.nextInt(tamanoSopa);
                    j = x-1;
                    /*
                    * Es x-1, porque cuando vuelva al control del for, j++, entonces tendría uno más y no sería 
                    * realmente 0.
                    */
                }
            }
            for(int j=x;j<lon+x;j++){
                sopa[y][j] = lista.get(i).charAt(j-x);
            }
        }
        else{
            int x = lon + myRandPos.nextInt(tamanoSopa-lon);
            int y = myRandPos.nextInt(tamanoSopa);
            for(int j = x;j>(x-lon);j--){
                //cuando finalizado el trabajo, corregir el if: if(sopa[y][j] != '\0'
                if(sopa[y][j] != ' ' && sopa[y][j] != lista.get(i).charAt(x-j)){
                    x = lon + myRandPos.nextInt(tamanoSopa-lon);
                    y = myRandPos.nextInt(tamanoSopa);
                    j = x+1;
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
        Random myRandPos = new Random();
        if(dir == 2){
            int y = myRandPos.nextInt(tamanoSopa-lon);         
            int x = myRandPos.nextInt(tamanoSopa);
            for(int j=y;j<lon+y;j++){
                //cuando finalizado el trabajo, corregir el if: if(sopa[y][j] != '\0'
                if(sopa[j][x] != ' ' && sopa[j][x] != lista.get(i).charAt(j-y)){
                    x = myRandPos.nextInt(tamanoSopa);
                    y = myRandPos.nextInt(tamanoSopa-lon);
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
            int x = myRandPos.nextInt(tamanoSopa);
            int y = lon + myRandPos.nextInt(tamanoSopa-lon);
            for(int j = y;j>(y-lon);j--){
                //cuando finalizado el trabajo, corregir el if: if(sopa[y][j] != '\0'
                if(sopa[j][x] != ' ' && sopa[j][x] != lista.get(i).charAt(y-j)){
                    x = myRandPos.nextInt(tamanoSopa);
                    y = lon + myRandPos.nextInt(tamanoSopa-lon);
                    j = y+1;
                }
            }
            for(int j = y;j>(y-lon);j--){
                sopa[j][x] = lista.get(i).charAt(y-j);
            }
        }
    }
    static void creaDiagonales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int dir, int i){
        /*
         * Este método permite decidir si una palabra se crea en sentido invertido o no.
         * Además, llena la matriz de chars con los chars de la palabra en diagonal.
         * El caso 4 es la palabra en diagonal hacia abajo invertida y el caso 5 es la palabra en diagonal hacia 
         * abajo no invertida. 
         * el caso 6 es la palabra en diagonal hacia arriba invertida y el caso 7 es la palabra en diagonal hacia 
         * arriba no invertida.
         */
        // no funcional.
        Random myRandPos = new Random();
        switch(dir){
            case 4:
                int p = myRandPos.nextInt(tamanoSopa-lon);
                for(int j = p;j<lon;j++){
                    //cuando finalizado el trabajo, corregir el if: if(sopa[y][j] != '\0'
                    if(sopa[j][j] != ' ' && sopa[j][j] != lista.get(i).charAt(j-p)){
                        p = myRandPos.nextInt(tamanoSopa-lon);
                        j = p-1;
                        /*
                         * Es p-1, porque cuando vuelva al control del for, j++, entonces tendría uno más y no sería 
                         * realmente 0.
                         */
                    }
                }
                for(int j = p;j<lon;j++){
                    sopa[j][j] = lista.get(i).charAt(j-p);
                }
        }
    }
}