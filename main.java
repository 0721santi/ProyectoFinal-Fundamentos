import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.*; // se importan todas las libreria del paquete .io (input output)
public class main{
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
    // este metodo tiene como funcion llenar el arreglo con las palabras que habian en el archivo .txt
    // static void llenarClase(Palabra p[], ArrayList<String> lista){
    //     for(int i=0;i<lista.size();i++){
    //         p[i] = new Palabra(lista.get(i).length(), lista.get(i));
    //         System.out.println(lista.get(i));
    //     }
    // }
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.print("Ingrese nombre del archivo: ");
        String path = myScan.nextLine();
        // Se crea y se organiza el arrayList
        ArrayList<String> listaPalabras = new ArrayList<>();
        creaArrayPalabras(path, listaPalabras);
        organizaLista(listaPalabras);
        // Se instancia la clase "Palabra" y se crean los objetos.
        Palabra p[] = new Palabra[listaPalabras.size()];
        //llenarClase(p, listaPalabras);
        // for(int i = 0;i<tamañoSopa;i++){
        //     for(int j = 0;j<tamañoSopa;j++){
        //         sopa[i][j] = ' ';
        //     }
        // }
        int tamanoSopa = listaPalabras.get(0).length() * 2;
        char[][] sopa = MainSopaLetras.creaSopaDeLetras(listaPalabras, tamanoSopa);
        // int x, direccion;
        // int y = 0;
        // for(int i = 0;i<p[0].getLongitude();i++){
        //     if(sopa[0][i] == '\0'){
        //         sopa[0][i] = p[0].getName().charAt(i);
        //         System.out.print(sopa[0][i]);
        //     }
        // }
        // for(int i = 0;i<listaPalabras.size();i++){
        //     direccion = 1 + myRandDirection.nextInt(2);
        //     System.out.println(p[i].getName());
        //     System.out.println("Direccion "+direccion);
        //     int lon = p[i].getLongitude();
        //     switch(direccion){
        //         case 1:
        //             y = myRandPos.nextInt(tamañoSopa);
                    
        //             x = myRandPos.nextInt(tamañoSopa-lon);
        //             System.out.println("Long "+lon);
        //             for(int j=x;j<lon+x;j++){
        //                 System.out.println("y"+ y);
        //                 System.out.println("x "+x);
        //                 System.out.println("j "+j);
        //                 System.out.println("j-x "+(j-x));
        //                 if(sopa[y][j] == '\0' || sopa[y][j] == p[i].getName().charAt(j-x) || sopa[y][j] == ' '){
        //                     sopa[y][j] = p[i].getName().charAt(j-x);
        //                     System.out.println(sopa[y][j]);
        //                 }
        //                 else{
        //                     x = myRandPos.nextInt(tamañoSopa-lon);
        //                     y = myRandPos.nextInt(tamañoSopa);
        //                     j = x-1;
        //                 }
        //             }
        //             break;
        //         case 2:
        //             y = myRandPos.nextInt(tamañoSopa);
        //             x = lon + myRandPos.nextInt(tamañoSopa-lon);
        //             System.out.println("Long "+lon);
        //             for(int j = x;j>(x-lon);j--){
        //                 System.out.println("y "+ y);
        //                 System.out.println("x "+x);
        //                 System.out.println("j "+j);
        //                 System.out.println("x-j "+(x-j));
        //                 if(sopa[y][j] == '\0' || sopa[y][j] == p[i].getName().charAt(x-j) || sopa[y][j] == ' '){
        //                     sopa[y][j] = p[i].getName().charAt(x-j);
        //                     System.out.println(sopa[y][j]);
        //                 }
        //                 else{
        //                     x = lon + myRandPos.nextInt(tamañoSopa-lon);
        //                     y = myRandPos.nextInt(tamañoSopa);
        //                     j = x+1;
        //                 }
        //             }
        //             break;
        //         case 3:
        //             x = myRandPos.nextInt(tamañoSopa);
                    
        //             y = myRandPos.nextInt(tamañoSopa-lon);
        //             System.out.println("Long "+lon);
        //             for(int j=x;j<lon+x;j++){
        //                 System.out.println("y"+ y);
        //                 System.out.println("x "+x);
        //                 System.out.println("j "+j);
        //                 System.out.println("j-x "+(j-x));
        //                 if(sopa[j][y] == '\0' || sopa[j][y] == p[i].getName().charAt(j-x) || sopa[j][y] == ' '){
        //                     sopa[j][y] = p[i].getName().charAt(j-x);
        //                     System.out.println(sopa[y][j]);
        //                 }
        //                 else{
        //                     y = myRandPos.nextInt(tamañoSopa-lon);
        //                     x = myRandPos.nextInt(tamañoSopa);
        //                     j = x-1;
        //                 }
        //             }
        //             break;
        //         case 4:
        //             x = myRandPos.nextInt(tamañoSopa);
        //             y = lon + myRandPos.nextInt(tamañoSopa-lon);
        //             System.out.println("Long "+lon);
        //             for(int j = x;j>(x-lon);j--){
        //                 System.out.println("y "+ y);
        //                 System.out.println("x "+x);
        //                 System.out.println("j "+j);
        //                 System.out.println("x-j "+(x-j));
        //                 if(sopa[j][y] == '\0' || sopa[j][y] == p[i].getName().charAt(x-j) || sopa[j][y] == ' '){
        //                     sopa[j][y] = p[i].getName().charAt(x-j);
        //                     System.out.println(sopa[j][y]);
        //                 }
        //                 else{
        //                     y = lon + myRandPos.nextInt(tamañoSopa-lon);
        //                     x = myRandPos.nextInt(tamañoSopa);
        //                     j = x+1;
        //                 }
        //             }
        //             break;
        //         case 5:

        //         case 6:
                    
        //     }
        // }
        for(int i = 0;i<tamanoSopa;i++){
            for(int j = 0;j<tamanoSopa;j++){
                System.out.print(sopa[j][i] + " ");
            }
            System.out.println(" ");
        }
    }
}