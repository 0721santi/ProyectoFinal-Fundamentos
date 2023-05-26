import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*; // se importan todas las libreria del paquete .io (input output)
public class MainSopaLetras {
    // se crea un metodo para buscar el archivo .txt para llenar el arreglo
    // se busco en: (url) para lograr realizar la busqueda de un archivo
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
    static char[][] creaSopaDeLetras(ArrayList<String> lista){
        
        Random myRandDirection = new Random();
        int direccion;
        int tamanoSopa = lista.get(0).length() * 2;
        char[][] sopa = new char[tamanoSopa][tamanoSopa];
        for(int i = 0;i<lista.size();i++){
            direccion = 1 + myRandDirection.nextInt(2);
            System.out.println(lista);
            System.out.println("Direccion "+direccion);
            int lon = lista.get(i).length();
            switch(direccion){
                case 0:
                    creaVerticales(lista, sopa, tamanoSopa, lon, i);
                    break;
                case 1:
                    creaHorizontales(lista, sopa, tamanoSopa, lon, direccion);
                    break;
                // case 2:
                //     x = myRandPos.nextInt(tamanoSopa);
                    
                //     y = myRandPos.nextInt(tamanoSopa-lon);
                //     System.out.println("Long "+lon);
                //     for(int j=x;j<lon+x;j++){
                //         System.out.println("y"+ y);
                //         System.out.println("x "+x);
                //         System.out.println("j "+j);
                //         System.out.println("j-x "+(j-x));
                //         if(sopa[j][y] == '\0' || sopa[j][y] == p[i].getName().charAt(j-x) || sopa[j][y] == ' '){
                //             sopa[j][y] = p[i].getName().charAt(j-x);
                //             System.out.println(sopa[y][j]);
                //         }
                //         else{
                //             y = myRandPos.nextInt(tamanoSopa-lon);
                //             x = myRandPos.nextInt(tamanoSopa);
                //             j = x-1;
                //         }
                //     }
                //     break;
                // case 4:
                //     x = myRandPos.nextInt(tamanoSopa);
                //     y = lon + myRandPos.nextInt(tamanoSopa-lon);
                //     System.out.println("Long "+lon);
                //     for(int j = x;j>(x-lon);j--){
                //         System.out.println("y "+ y);
                //         System.out.println("x "+x);
                //         System.out.println("j "+j);
                //         System.out.println("x-j "+(x-j));
                //         if(sopa[j][y] == '\0' || sopa[j][y] == p[i].getName().charAt(x-j) || sopa[j][y] == ' '){
                //             sopa[j][y] = p[i].getName().charAt(x-j);
                //             System.out.println(sopa[j][y]);
                //         }
                //         else{
                //             y = lon + myRandPos.nextInt(tamanoSopa-lon);
                //             x = myRandPos.nextInt(tamanoSopa);
                //             j = x+1;
                //         }
                //     }
                //     break;
                // case 5:

                // case 6:
                    
            }
        }
        return sopa;
    }
    static void creaVerticales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int i){
        /*
         * Esta función permite decidir si una palabra se crea en sentido invertido o no.
         * Además, llena la matriz de chars con los chars de la palabra en cuestión de manera vertical.
         */
        Random myRandPos = new Random();
        Random myRandSts = new Random();
        int x, y;
        int sts = myRandSts.nextInt(2);
        int[] usedX, usedY;
        y = myRandPos.nextInt(tamanoSopa);         
        x = myRandPos.nextInt(tamanoSopa-lon);
        System.out.println("Long "+lon);
        switch(sts){
            case 0:
                usedX = new int[lon];
                usedY = new int[lon];
                for(int j=x;j<lon+x;j++){
                    usedY[j-x] = y;
                    System.out.println("y"+ y);
                    usedX[j-x] = x;
                    System.out.println("x "+x);
                    System.out.println("j "+j);
                    System.out.println("j-x "+(j-x));
                    if(sopa[y][j] == '\0' || sopa[y][j] == lista.get(i).charAt(j-x)){
                        sopa[y][j] = lista.get(i).charAt(j-x);
                        System.out.println(sopa[y][j]);
                    }
                    else{
                        x = myRandPos.nextInt(tamanoSopa-lon);
                        y = myRandPos.nextInt(tamanoSopa);
                        j = x-1;
                    }
                    //Verticales.llenaArrayList(lon, lista.get(i), 0, usedX, usedY);
                }
            case 1:
                usedX = new int[lon];
                usedY = new int[lon];
                for(int j = x;j>(x-lon);j--){
                    usedY[x-j] = y;
                    System.out.println("y"+ y);
                    usedX[x-j] = x;
                    System.out.println("x "+x);
                    System.out.println("j "+j);
                    System.out.println("x-j "+(x-j));
                    if(sopa[y][j] == '\0' || sopa[y][j] == lista.get(i).charAt(x-j)){
                        sopa[y][j] = lista.get(i).charAt(x-j);
                        System.out.println(sopa[y][j]);
                    }
                    else{
                        x = lon + myRandPos.nextInt(tamanoSopa-lon);
                        y = myRandPos.nextInt(tamanoSopa);
                        j = x+1;
                    }
                }
                //Verticales.llenaArrayList(lon, lista.get(i), 1, usedX, usedY);
        }

    }
    static void creaHorizontales(ArrayList<String> lista, char[][] sopa, int tamanoSopa, int lon, int i){
        /*
         * Esta función permite decidir si una palabra se crea en sentido invertido o no.
         * Además, llena la matriz de chars con los chars de la palabra en cuestión de manera vertical.
         */
        Random myRandPos = new Random();
        Random myRandSts = new Random();
        int x, y;
        int sts = myRandSts.nextInt(2);
        y = myRandPos.nextInt(tamanoSopa);         
        x = myRandPos.nextInt(tamanoSopa-lon);
        System.out.println("Long "+lon);
    }
}
